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
class Association {
    
    static void is_associated(int a[],int n,int data[][],int trans,int maxc)
    {
        int arr[]=new int[n];
        int c=0;
        for(int i=0;i<n;i++)
         arr[i]=-1;
        double co1=Math.pow(2, n);
        int co=(int) co1;
        for(int i=0;i<co;i++)
        {
            c=0;
            for(int j=0;j<n;j++)
            {
                if((i & (1<<j))!=0)
                {
                    arr[c++]=a[j];
                }
            }
            if(c<n)
            fun(arr,c,data,trans,maxc,a,n);
        }
    }
    static void fun(int arr[],int c,int data[][],int trans,int maxc,int a[],int n)  // for count 
    {
        int c1=0,c2=0;
        int minp=70;
        for(int l=0;l<trans;l++)
                    {
                        int m=0,k=0;
                        for(int p=0;p<maxc;p++)
                        {
                            if(data[l][p]!=-1 && m<c && arr[m]==data[l][p])
                                m++;
                             if(data[l][p]!=-1 && k<n && a[k]==data[l][p])
                               k++;
                        }
                        if(m==c)
                            c1++;
                        if(k==n)
                            c2++;
                    }
        if(c1 >0 && ((c2*100)/c1)>minp)
        {
            for(int i=0;i<c;i++)
            {
                System.out.print(arr[i]+" ");
            }
            System.out.println("is associated with");
             for(int i=0;i<n;i++)
            {
 	        int flag=0;
		for(int j=0;j<c;j++)
		{
			if(a[i]==arr[j])
			{
			flag=1;
			break;
			}
		}
		if(flag==0)
                System.out.print(a[i]+" ");
            }
            System.out.println();
        }
        
    }
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
        int t=25,maxc=6,range=21;      // t -no of transactions & range = item no range & maxc =max item sets in a transaction
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
			no=is_subset(visit,level+1,m,a,rows,level);
		    }
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
          //      for(int j=0;j<=level;j++)
                {
                 //   System.out.print(b[i][j]+" ");
                    is_associated(b[i],level+1,data,trans,maxc);
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
