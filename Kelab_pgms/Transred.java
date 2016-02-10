/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.util.*;

/**
 *
 * @author c137212
 */
class Transred {
    
	static int is_subset(int a[],int n,int p,int data[][],int m,int col) //prune  method
	{
		int test[]=new int[n];
		int j=0;
		for(int i=0;i<n;i++)
		{
			if(i!=p)
			{
				test[j++]=a[i];
			}
		}
		int x=0;
		for(int i=0;i<m;i++)
		{
			x=0;
			for(j=0;j<col;j++)
			{
				if(x<n-1 && data[i][j]==test[x])
				x++;
			}
			if(x==n-1)
			return 1;
		}
		return 0;
	}
        static int contains(int data[],int maxc,int a[],int level)  // transaction contains that item set or not
        {
            int m=0;
            for(int p=0;p<maxc && m<level && a[m]!=-1;p++)
                        {
                            if( m< level && a[m]!=-1  && a[m]==data[p])
                                m++;
                        }
                        if(m==level)
                           return 1;
                        else
                            return 0;
        }
        static void is_present(int a[][],int rows,int maxc,int data[][],int trans,int tvisit[],int level)    // check if transaction has frequent item sets
        {
            for(int i=0;i<rows && a[i][0]!=-1 ;i++)
            {
                for(int j=0;j<trans && data[j][0]!=-1;j++)
                {
                    if(tvisit[j]==0 )
                    {
                   if(contains(data[j],maxc,a[i],level)==1) 
                   {
        //               System.out.println("present"+ (j+1)+" "+ (i+1));
                       tvisit[j]=1;
                   }
                    }
                }
            }
            for(int i=0;i<trans;i++)
            {
             //   System.out.println("yes");
                if(tvisit[i]==0)
                {
                    if(i<trans-1)
                     System.out.println("transaction "+(i+1) +"removed");
                   tvisit[i]=-1;
                }
               else if(tvisit[i]==1)
                    tvisit[i]=0;
            }
        }
    public static void main(String args[]) throws Exception
    {
        int t=9,maxc=6,range=6;      // t -no of transactions & range = item no range & maxc =max item sets in a transaction
        int A[]=new int[range];
        int data[][]=new int[t][maxc],trans=0,minc,tvisit[]=new int[t];
        System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
        minc=s.nextInt();
        int init[];
        int rows=0,level=1;
        for(int i=1;i<range;i++)
            A[i]=0;
        for(int i=0;i<t;i++)
        {
            tvisit[i]=0;
        for(int j=0;j<maxc;j++)
            data[i][j]=-1;
        }
   FileReader fin =new FileReader("data.txt");
    BufferedReader br =new BufferedReader(fin);
    String st;
        do
        {
            int i=0;
            st=br.readLine();
            if(st!=null)
            {
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                int k=Integer.parseInt(str.nextToken());
                A[k]++;
                data[trans][i++]=k;
            }
            }
            trans++;
        }while(st!=null);
        br.close();
        fin.close();
    for(int i=1;i<range;i++)
    {
        if(A[i]!=0)
        rows++;
    }
    init=new int[rows];
    int c=0;
     for(int i=1;i<range;i++)
    {
        if(A[i]!=0 && A[i]>=minc)
        init[c++]=i;
         if(A[i]>0 && A[i]>=minc)
            System.out.println(i);
    }
        int a[][],b[][];
        int flag=1;
        a=new int[rows][level];
     for(int i=0;i<rows;i++)
    {
      a[i][0]=init[i];
    }
        int flag2=1;
        int visit[];
        while(flag==1)
        {
            if(level==1)
            is_present(a,rows,maxc,data,trans,tvisit,level);
            flag=0;
            c=0;
            b=new int[(rows*(rows-1))/2][level+1];
            visit=new int[level+1];
            for(int i=0;i<rows;i++)
            {
                for(int j=i+1;j<rows;j++)
                {
		    visit=new int[level+1];
                    if(level-1==0)
                    {
                        visit[0]=a[i][0];
                        visit[1]=a[j][0];
                    }
                    else
                    {
                        flag2=1;
                        for(int k=0;k<(level-1);k++)
                        {
                            if(a[i][k]!=a[j][k])
                            {
                                flag2=0;
                                break;
                            }
                        }
                        if(flag2==1)
                        {
                            flag=1;
                            for(int k=0;k<level-1;k++)
                            {
                                visit[k]=a[i][k];
                            }
                            visit[level-1]=a[i][level-1];
                            visit[level]=a[j][level-1];
                        }
                    }
                    int count=0,no=1;
		    for(int m=0;m<=level;m++)
		    {
                        if(visit[level]!=0)
			no=is_subset(visit,level+1,m,a,rows,level);
                        if(no==0)
                            break;
		    }
            /*       if(no==0)
                    {
                         for(int pl=0;pl<level+1;pl++)
                            {
                                if(visit[pl]!=0)
                                System.out.print(visit[pl]+" ");
                            }
                            if(visit[level]!=0)
                           System.out.println();
                    }*/
		    if(no==1)
		    { 
                    for(int l=0;l<trans;l++)
                    {
                        if(tvisit[l]!=-1)
                        {
                        int m=0;
                        for(int p=0;p<maxc;p++)
                        {
                            if(data[l][p]!=-1 && m<level+1 && visit[m]==data[l][p])
                                m++;
                        }
                        if(m==level+1)
                            count++;
                        }
                    }
                    if(count>=minc)
                    {
                        flag=1;
                        for(int l=0;l<=level;l++)
                        {
                            b[c][l]=visit[l];
                        }
                        c++;
                    }
                    }
                }
            }
           for(int i=0;i<c;i++)                      
            {
                for(int j=0;j<=level;j++)
                {
                    System.out.print(b[i][j]+" ");
                }
                System.out.println();
            }
                a=new int[(rows*(rows-1))/2][level+1];
                a=b;
                rows=c;
                level++;
        }
    }
    
}
