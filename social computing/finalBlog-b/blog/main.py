#!/usr/bin/env python
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


from google.appengine.ext import ndb
from google.appengine.ext import db
from google.appengine.api import memcache


import logging
import webapp2
import jinja2
import os.path
import re
import hashlib
import hmac
import random
import string
import time
import json



template_dir = os.path.join(os.path.dirname(__file__),'templates')
jinja_env = jinja2.Environment(loader=jinja2.FileSystemLoader(template_dir))

JINJA_ENVIRONMENT = jinja2.Environment(
    loader=jinja2.FileSystemLoader(os.path.dirname(__file__)),
    extensions=['jinja2.ext.autoescape'],
    autoescape=True)
template_dir = os.path.join(os.path.dirname(__file__), "templates")
jinja_environment = jinja2.Environment(
    loader=jinja2.FileSystemLoader(template_dir),
    autoescape=True)

USER_RE = re.compile(r"^[a-zA-Z0-9_-]{3,20}$")
PASSWORD_RE = re.compile(r"^.{3,20}$")
EMAIL_RE = re.compile(r"^[\S]+@[\S]+\.[\S]+$")

secret = "nooneknows"

def make_secure_val(val):
    return "%s|%s" % (val, hmac.new(secret, val).hexdigest())


def check_secure_val(secure_val):
    val = secure_val.split('|')[0]
    return secure_val == make_secure_val(val)

# user hash stuff
def make_salt(length=10):
    return "".join(random.choice(string.letters) for x in xrange(length))

def make_pw_hash(pw, salt=None):
    if not salt:
        salt = make_salt()
    h = hashlib.sha256(pw + salt).hexdigest()
    return "%s#%s" % (h, salt)
                               
def valid_pw(pw, h):
    salt = h.split('#')[1]
    return h == make_pw_hash(pw, salt)

def set_cookie(handler, name, value):
    handler.response.headers.add_header("Set-Cookie", "%s=%s; Path=/" %
                                        (str(name), str(value)))

# user model

class User(db.Model):
    username = db.StringProperty(required=True)
    password = db.StringProperty(required=True)

def add_user(username, **kw):
    u = User(key_name=username, username=username, **kw)
    u.put()

def get_user(username):
    return User.get_by_key_name(username)
def render_str(template, **params):
    t = jinja_env.get_template(template)
    # self.response.out.write(t)
    return t.render(params)


class MainHandler(webapp2.RequestHandler):
  def render(self,template,**kw):
    self.response.out.write(render_str(template, **kw))

  def write(self, *a,**kw):
    self.response.out.write(*a,**kw)


# cookie hash stuff
def make_secure_val(val):
    return "%s|%s" % (val, hmac.new(secret, val).hexdigest())


def check_secure_val(secure_val):
   if secure_val is not None:
      val = secure_val.split('|')[0]
      return secure_val == make_secure_val(val)


# user hash stuff


def make_salt(length=10):
    return "".join(random.choice(string.letters) for x in xrange(length))

def make_pw_hash(pw, salt=None):
    if not salt:
        salt = make_salt()
    h = hashlib.sha256(pw + salt).hexdigest()
    return "%s#%s" % (h, salt)
                               
def valid_pw(pw, h):
    salt = h.split('#')[1]
    return h == make_pw_hash(pw, salt)

def set_cookie(handler, name, value):
    handler.response.headers.add_header("Set-Cookie", "%s=%s; Path=/" %
                                        (str(name), str(value)))

# user model
class User(db.Model):
    username = db.StringProperty(required=True)
    password = db.StringProperty(required=True)

class POSTS_DB(ndb.Model):
    username = ndb.StringProperty(required=True)
    created = ndb.DateTimeProperty(auto_now_add=True)
    question=ndb.StringProperty(required=True)
    # likes = ndb.IntegerProperty(default=0)
    # dislikes = ndb.IntegerProperty(default=0)
    dateT = ndb.DateTimeProperty(auto_now_add=True)

class Q_A_DB(ndb.Model):
    posted_by=ndb.StringProperty(required=True)
    question = ndb.StringProperty(required=True)
    answer=ndb.TextProperty(required=False)
    answered_by=ndb.StringProperty(required=False)


def add_user(username, **kw):
    u = User(key_name=username, username=username, **kw)
    u.put()

def get_user(username):
    return User.get_by_key_name(username)


# Register Handler
class Register(MainHandler):
    def get(self):
        self.render('registrationform.html')
    def post(self):
        username = self.request.get("username")
        password = self.request.get("password")
        verify = self.request.get("verify")
        email = self.request.get("email")

        errors = {}
        # verify username
        if not self.valid_username(username):
            errors["invalid_username"] = "That's not a valid username."
            errors["username"] = username
        # verify password
        if not self.valid_password(password):
            errors["invalid_password"] = "That wasn't a valid password."
        elif not self.valid_verify(password, verify):
            # if password is valid, check if password and verify match.
            errors["invalid_verify"] = "Your passwords didn't match."
        # verify  email
        if email and not self.valid_email(email):
            errors["invalid_email"] = "That's not a valid email."

        # check if a user with same username exists?
        if get_user(username):
            errors.clear()
            errors["invalid_username"] = "That user already exists"
            
        if errors:
            # template = JINJA_ENVIRONMENT.get_template('Register.html')
            self.response.out.write(errors)
            self.render('registrationform.html')
        else:
            add_user(username=username, password=make_pw_hash(password))
            set_cookie(self, "username", make_secure_val(username))
            self.redirect("/")
        
    def valid_username(self, username):
        return USER_RE.match(username)

    def valid_password(self, password):
        return PASSWORD_RE.match(password)

    def valid_verify(self, password, verify):
        """Check if password and verify same."""
        return password == verify

    def valid_email(self, email):
        return EMAIL_RE.match(email)

class Login(MainHandler):
    def get(self):
        self.render('loginform.html')
    
    def post(self):
        username = self.request.get("username")
        password = self.request.get("password")
        logging.info("logged in!!")
        errros = {}
        if(username!="" and password != ""):
            u = get_user(username)
        # logging.info(u.password + "---<<password")
            if u and valid_pw(password, u.password):
                set_cookie(self, "username", make_secure_val(username))
                self.redirect("/welcome")
            else:
                error = "Invalid login"
                template_values = {"error": error}
                template = jinja_environment.get_template("loginform.html")
                self.response.out.write(template.render(template_values))
        else:
            error = "Invalid login"
            template_values = {"error": error}
            template = jinja_environment.get_template("loginform.html")
            self.response.out.write(template.render(template_values))

class Logout(webapp2.RequestHandler):
    def get(self):
        set_cookie(self, "username", "")  # clear cookie
        self.redirect("/home")

class Welcome(webapp2.RequestHandler):
    def get(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            self.redirect("/blog/")
            # template = jinja_environment.get_template("welcome.html")
            # self.response.out.write(template.render(template_values))
        else:
            logging.info("----out----")
            self.redirect("/Register")
class BlogReply(db.Model):
    postID = db.IntegerProperty(required=True)
    reply = db.TextProperty(required=True)
    created = db.DateTimeProperty(auto_now_add=True)
    user = db.StringProperty(required=True)
class BlogPost(db.Model):
    subject = db.StringProperty(required=True)
    content = db.TextProperty(required=True)
    created = db.DateTimeProperty(auto_now_add=True)

    def toJSON(self):
        d = dict(subject=self.subject, content=self.content,
                 created=self.created.strftime("%b %d, %Y"))
        return json.dumps(d)
class BasicHandler(webapp2.RequestHandler):
    def write_html(self, template_file, **kwargs):
        template = jinja_environment.get_template(template_file)
        self.response.out.write(template.render(**kwargs))
def set_question(key, val):
    memcache.set(key, val)

def get_question(key):
    r = memcache.get(key)
def age_set(key, val):
    save_time = time.time()
    memcache.set(key, (val, save_time))

def age_get(key):
    r = memcache.get(key)
    if r:
        val, save_time = r
        age = time.time() - save_time
    else:
        val, age = None, 0
    return val, age

class Blog(BasicHandler):
    def get(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the blog cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                logging.info("----blog----")
                blog_posts,age = self.get_blog_posts()
                # comments = self.get_comments()
                # logging.info(comments)
                comments = BlogReply.all().order("-created")
                for comment in comments:
                    logging.info(comment.reply)

                self.write_html("blog.html", blog_posts=blog_posts, queried=age, username=username, comments = comments)
        else:
            self.redirect("/login")

    def get_comments(self):
        comments = get_question("question")
        if comments:
            return comments
        else:
            comments = BlogReply.all().order("-created")
            set_question("question",comments)
        return get_question("question")

    def get_blog_posts(self):
        """Get blog posts. If they are in cache, don't hit the db"""
        blog_posts,age = age_get("blog_posts")
        if blog_posts:
            return blog_posts, age
        else:
            blog_posts = BlogPost.all().order("-created")
            age_set("blog_posts", blog_posts)
        return age_get("blog_posts")

    def post(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the blogjson cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                reply = self.request.get("reply")

                qID = int(self.request.get("id"))
                # logging.info(reply + " " + qID)
                r = BlogReply(postID=qID,reply=reply,user=username)
                r.put()
                memcache.delete("question")
                # set_question("question",r)
                # comments = get_question("question")
                self.redirect("/blog")
                # blog_posts,age = self.get_blog_posts()
                # # comments = BlogReply.all().order("-created")
                # # logging.info("inside post--->")
                # # for comment in comments:
                # #     logging.info(comment.reply)
                # self.write_html("blog.html", blog_posts=blog_posts, queried=age, username=username, comments = comments) 
        else:
            self.redirect("/login")

class BlogJSON(BasicHandler):
    def get(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the blogjson cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                posts = BlogPost.all().order("-created")
                json_objs = [p.toJSON() for p in posts]
                self.response.headers["Content-Type"] = "application/json"
                self.response.out.write('[' + ','.join(json_objs) + ']')
        else:
            self.redirect("/login")
class Post(BasicHandler):
    def get(self, post_id):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the post cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                post,age = self.get_post(post_id)
                self.write_html("post.html", post=post, queried=age)
        else:
            self.redirect("/login")

    def get_post(self, post_id):
        postkey = "post_%s" % post_id
        post, age = age_get(postkey)
        if post:
            return post, age
        else:
            key = db.Key.from_path("BlogPost", int(post_id))
            post = db.get(key)
            age_set(postkey, post)
            
        return age_get(postkey)
class PostJSON(BasicHandler):
    def get(self, post_id):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the post cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                key = db.Key.from_path("BlogPost", int(post_id))
                post = db.get(key)
                self.response.headers["Content-Type"] = "application/json"
                self.response.out.write(post.toJSON())
        else:
            self.redirect("/login")
class NewPost(BasicHandler):
    def get(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the newpost cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                self.write_html("newpost.html")
        else:
            self.redirect("/login")

    def post(self):
        c = self.request.cookies.get("username")
        logging.info(c + " <--the newpost cookie")
        if check_secure_val(c):
            logging.info("----in---")
            username = c.split('|')[0]
            template_values = {"username": username}
            if username != "":
                subject = self.request.get("subject")
                content = self.request.get("content")
                if not (subject and content):
                    error_msg = "subject and content, please."
                    self.write_html("newpost.html", error=error_msg)
                else:
                    b = BlogPost(subject=subject, content=content)
                    b.put()
                    memcache.delete("blog_posts")
                    self.redirect("/blog/%s" % b.key().id())
        else:
            self.redirect("/login")
class FlushCache(BasicHandler):
    def get(self):
        memcache.flush_all()
        self.redirect("/blog")

class Home(MainHandler):
    def get(self):
        c = self.request.cookies.get("username")
        if check_secure_val(c):
            username = c.split('|')[0]
            template_values = {"username": username}
            # template = JINJA_ENVIRONMENT.get_template("index.html")
            # self.response.out.write(template.render(template_values))
            self.render('index.html')
        else:
            self.render('index.html')
            
class About(MainHandler):
    def get(self):
        self.render('about.html')

class Contact(MainHandler):
    def get(self):
        self.render('contact.html')

class Refresh(MainHandler):
    def get(self):
        self.redirect("/blog")


app = webapp2.WSGIApplication([
    ('/', Home),
    ('/home', Home),
    ('/login', Login),
    ('/Register', Register),
    ('/logout/?', Logout),
    ('/blog/logout/?', Logout),
    ('/about', About),
    ('/contact', Contact),
    ('/refresh', Refresh),
    ("/welcome/?", Welcome),
    ("/blog/?", Blog),
    ("/blog/([0-9]+)/?", Post),
    ("/newpost/?", NewPost),
    #json
    (("/.json"), BlogJSON),
    (("/blog/([0-9]+).json"), PostJSON),
    ("/blog/flush/?", FlushCache)

], debug=True)
