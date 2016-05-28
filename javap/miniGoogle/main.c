#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h> //used for converting all words to lower case letters by using tolower()method

#include "allp.h" //this header contains all the methods required for executing
int size=0;
typedef struct Words  //this stores words returned from add_pages_to_index method
{		
    char *word;
    char **url;
    int *freq;
    int count;
}Indexer;
Indexer *ind,*ind1; //variables are created for indexer type to store all the data

typedef struct Rank //this structue stores url and the freq of word occured 
{
    char *link;
    int freq;
}Rank;


//add_to_index method 
Indexer *add_to_index(char *word, char *url) //takes word and url it occured from add_pages_toindex()
{

   int j,flag,x,k;
  
    if(size==0) //this loop is run when frst method is called
    {  
        ind=(Indexer*)malloc(1*sizeof(Indexer));
        ind->word=(char *)malloc(50*sizeof(char));
        ind->url=(char **)malloc(100*sizeof(char *));
        ind->freq=(int *)malloc(100*sizeof(int));
        ind->word=word;
        ind->url[0]=(char *)malloc(50*sizeof(char));
        ind->url[0]=url;
        ind->freq[0]=1;
        ind->count=1;
        size++; 
    }
    
    else
    {      
        flag=0;
        for(j=0;j<size;j++)
        {
            if(strcmp(word,(ind+j)->word)==0)
            {
                x=0;
                flag=1;
                for(k=0;k<(ind+j)->count;k++)
                {
                    if(strcmp(url,(ind+j)->url[k])==0)
                    {
                        x=1;
                        (ind+j)->freq[k]+=1;
                        break;
                    }
                }
              
                if(x==0)
                {    
                                             
                    (ind+j)->url[(ind+j)->count]=(char*)malloc(50*sizeof(char));                          
                    (ind+j)->url[(ind+j)->count]=url;
                    (ind+j)->freq[(ind+j)->count]=1;
                    (ind+j)->count+=1;     
                }        
                break;
            }
        }

        if(flag==0) //when the first word encounters it stores directly into the structure
        {
            ind=(Indexer*)realloc(ind,(size+1)*sizeof(Indexer));
            (ind+size)->word=(char *)malloc(100*sizeof(char));
            (ind+size)->url=(char **)malloc(100*sizeof(char *)); //creating memory for whole block
            (ind+size)->url[0]=(char *)malloc(100*sizeof(char)); //creating memory for single url
            (ind+size)->freq=(int *)malloc(sizeof(int));         //creating memory for freq     
            (ind+size)->word=word; 
            (ind+size)->url[0]=url;
            (ind+size)->freq[0]=1;
            (ind+size)->count=1;
            size++;   

        }
    }
    return ind; //returns indexer variable to calling function
}
Indexer *add_pages_to_index(char **urlarray)
{
	char *data,*tdata; 
	//tdata contains data with tags
	//data contains only words(without tags)
	data=(char*)malloc(10000*sizeof(char));
	tdata=(char*)malloc(100000*sizeof(char));
	
	//puts(tdata);ok
	int i,j,k=0,z,b=0;
	for(z=0;urlarray[z]!='\0';z++)//this loop is run until all the urls are completed 
	{
		tdata=get_page(urlarray[z]);
		for(i=0;tdata[i]!='\0';i++)
		{
			if(data==NULL) //if the file doesn't exist it returns null
			{
				continue;
			}
			if(tdata[i]=='>') //only when some data is present,the tags will be removed
			{     
				for(j=i+1;tdata[j]!='\0';j++)
				{
					if(tdata[j]=='<')
					{
						break;
					}
					else
					{
						data[k]=tolower(tdata[j]);
						k++;
					}
				}
			}
		}//retrieving all words

		char **words;
		words=(char**)malloc(10000*sizeof(char*));
		words[0]=(char*)malloc(100*sizeof(char));
		words[0]=strtok(data," \n&=<>\t:"";.?,{}()-&9876543210"); //all the tokens listed will be removed from data if present
		//puts(words[0]);
		i=0;
		while(words[i]!='\0')
		{
			i++;
			words[i]=(char*)malloc(300*sizeof(char));
			words[i]=strtok(NULL," \n&=<>\t:"";.?,{}()-&9876543210");
			//printf("%d)%s\n",i,words[i]);
			
		}
		char *stopfile;
		stopfile=(char*)malloc(10000*sizeof(char));
		stopfile=get_page("stopwords.txt"); //data from stopwords is retrieved
		char **stopwords;
		stopwords=(char**)malloc(10000*sizeof(char*));
		stopwords[0]=(char*)malloc(100*sizeof(char));
		stopwords[0]=strtok(stopfile,"\n"); //data is divided into tokens when \n character occurs
		char **finalwords;
		finalwords=(char**)malloc(10000*sizeof(char*));
		i=0,k=0;
		while(stopwords[i]!='\0')
		{
			i++;
			stopwords[i]=(char*)malloc(100*sizeof(char));
			stopwords[i]=strtok(NULL,"\n");
		//printf("%s\n",stopwords[i]);
			stopwords[i+1]='\0';
		}
		for(i=0;words[i]!='\0';i++)
		{
			if(stopwords[i]!='\0')
			{
				for(j=0;stopwords[j]!='\0';j++)
				{
					count=0;
					if(strcmp(words[i],stopwords[j])==0) //compares words with stop words
					{
						count=1;
						break;
					}
				}
				if(count==0) //if the word doesn't occur in stopwords it is stored in final words
				{
					finalwords[k]=(char*)malloc(1000*sizeof(char));
					finalwords[k]=words[i];
					//printf("%d)%s--->%s\n",b,finalwords[k],urlarray[z]);
					add_to_index(finalwords[k],urlarray[z]); //sent to method
		
					k++;
					b++;
				}
			
			}
		}//retriving all words loop

	}//all urls loop
	

	return ind;

}



int main()
{
	char *url,**crawl;
	char *search;
	Indexer *final;
	 Rank *r,*temp;
    int p,x,i=0,flag;
	
	crawl=(char**)malloc(20000*sizeof(char));
	url=(char *)malloc(20000*sizeof(char));	
	search=(char *)malloc(20000*sizeof(char));
	r=(Rank *)malloc(20000*sizeof(char));
	printf("enter html file\n");
	scanf(" %[^\n]s",url);
	//crawl web method
		crawl=crawl_web(url);
		printf("\n");
		for(i=0;crawl[i]!='\0';i++)
		{
			//printf("%s\n",crawl[i]);
		}
		
	//add_pages_to_index and add_to_index methods	
	final=add_pages_to_index(crawl); //links from crawl web are sent to this function


    for(int i=0;i<size;i++)
    {
    	printf("\n****************\n");
        printf("\n%s ",(final+i)->word); //prints evry word with count it occured in each url
        for(int x=0;x<(final+i)->count;x++)
        {
            printf("\n  %s--->%d ",(final+i)->url[x],(final+i)->freq[x]);
        }
        printf("\ncount=%d ",(final+i)->count);
    }

  
  printf("\nEnter a query to search\n Search: ");
  scanf("%s",search);
  flag=0;
    
  for(p=0;p<size;p++)
  {
        if(strcmp(search,(final+p)->word)==0) //comparing every word for getting rank
        {
            flag=1;
            for(x=0;x<(final+p)->count;x++)
            {
                
                (r+x)->link=(final+p)->url[x];
                (r+x)->freq=(final+p)->freq[x];
              
            }
            break;
        }
   }
    
    if(flag==0)
    {
        printf("word not found");
        return 0;
    }

  
    temp=(Rank *)malloc(1*sizeof(Rank));

    for(int i=0;i<(final+p)->count;i++)
    {
        for(int j=i+1;j<(final+p)->count;j++)
        {
            if(((r+i)->freq)<((r+j)->freq))
            {
                temp->link=(r+i)->link; temp->freq=(r+i)->freq;
                (r+i)->link=(r+j)->link;(r+i)->freq=(r+j)->freq;
                (r+j)->link=temp->link;(r+j)->freq=temp->freq;
            }
        }
    }

    for(i=0;i<(final+p)->count;i++) //displays the final output
        printf("\n%s---> %d",(r+i)->link,(r+i)->freq);
	
	
return 0;
}
