#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
int count=0,c;
char **links;


//this header files performs operations and returns output to every method called from main method


//get page method
char *get_page(char *htmlpage)
{
	int i=0;
	
	FILE *fptr;
	fptr=fopen(htmlpage,"r");
	char c;
	char *data;
	data=(char *)malloc(20000*sizeof(char *));
	if(fptr==NULL)
	{
		//printf("\nNo file found");
		return NULL;
	}
	
	for(i=0;!feof(fptr);i++)
	{
		c=fgetc(fptr); //reads character by character from file and sends it to the function
		data[i]=c;
	
	}
	data[i]='\0';
	fclose(fptr);
	if(strlen(data)>0)
	{
		return data;
	}
	else
	{
		return 0;
	}
	
   
}
//get next target method
char *get_next_target(char *stripedstring)
{	
	int i=0,j;
	char *a,*b;
	a=(char*)malloc(20000*sizeof(char));
	b=(char*)malloc(20000*sizeof(char));
	a=strstr(stripedstring,"href");
	//puts(a);
	//puts("see");
	if(a!=NULL) //searching for links
	{
		while(a[i]!='"')
		{
			i++;
		}
		i++;
		for(j=0;a[i]!='"';i++,j++)
		{
			b[j]=a[i];
		}
		b[j]='\0';

		return b; //returns first link encountered

	}
	else
	{
		return NULL; //returns null if there are no links
	}

}
//get all links method
char **get_all_links(char *htmlstring)
{
	count=0;
	char **x,*y;
	y=(char *)malloc(20000*sizeof(char));
	x=(char **)malloc(20000*sizeof(char*));
	y=get_next_target(htmlstring);
	x[0]=(char *)malloc(20000*sizeof(char*));
	strcpy(x[0],y);
	int i=1;
	while(y!=NULL)
	{
		htmlstring=strstr(htmlstring,y); //data is striped off to retrieve another link
		y=get_next_target(htmlstring); //receives 1 link from get next target function
		if(y!=NULL)
		{
			x[i]=(char *)malloc(20000*sizeof(char));
			strcpy(x[i],y);
			i++;
			count++;

		}

	}
	x[i]='\0';
	return x;
}
//crawl web method

char **crawl_web(char *seedpage)
{
	char **q,*a;
	int z;
	q=(char **)malloc(20000*sizeof(char*));
	
	a=(char *)malloc(20000*sizeof(char));
	links=(char **)malloc(20000*sizeof(char*));
	links[0]=seedpage;
			
	int j,k;
	
	int f,i=0;

	c=count+1;
	if(links[0]!='\0')
	{
		a=get_page(links[i]); //retriees data

		q=get_all_links(a); //calls get next target method and stores all links
			
		for(int x=0;q[x]!='\0';x++)
		{
			links[x+1]=q[x];
		}
				
		for(i=0;links[i]!='\0';i++)
		{
			a=get_page(links[i+1]);
			if(a==NULL)
			{
				continue;
			}
			q=get_all_links(a);
			if(!a)
			{
				continue;
			}
			f=count;
			for(j=0;j<=f;j++)
			{
				if(strcmp(links[i],q[j])!= 0)
				{
					for(k=1;links[k]!='\0';k++)
					{
						z=0;
						if(strcmp(links[k],q[j])==0)
						{
							z++;
							break;
						}
					}
					if(z==0)
					{
						links[++c]=(char *)malloc(500*sizeof(char));
						strcpy(links[c],q[j]);
												
					}
					
				}
			}
			
		}
	}
	
	
return links; //returns links array(double pointer) which contains all links

}
