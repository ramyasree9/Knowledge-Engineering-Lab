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
class Apriori {
    
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
    public static void main(String args[]) throws Exception
    {
        int t=10,maxc=5,range=6;      // t -no of transactions & range = item no range & maxc =max item sets in a transaction
        int A[]=new int[range];
        int data[][]=new int[t][maxc],trans=0,minc;
        System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
        minc=s.nextInt();
        int init[];
        int rows=0,level=1;
        for(int i=1;i<range;i++)
            A[i]=0;
        for(int i=0;i<t;i++)
        {
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
        if(A[i]!=0)
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
                        int m=0;
                        for(int p=0;p<maxc;p++)
                        {
                            if(data[l][p]!=-1 && m<level+1 && visit[m]==data[l][p])
                                m++;
                        }
                        if(m==level+1)
                            count++;
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
