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

class Partition
{
    static int is_same(int a[],int b[])
    {
        for(int i=0;b[i]!=-1 ;i++)
        {
 /*           if(a[i]==-1 && b[i]!=-1)
                return 0;
            if(b[i]==-1 && a[i]!=-1)
                return 0;
            if(a[i]==-1 && b[i]==-1)
                return 1;*/
            if(a[i]!=b[i])
                return 0;
        }
        return 1;
    }
    public static void main(String args[]) throws Exception
    {
        Appriori app=new Appriori();
          int t=12,maxc=6,range=6,parts=3;      // t -no of transactions & range = item no range & maxc =max item sets in a transaction
        int A[]=new int[range];
        int res[][]=new int[t*(t-1)/2][maxc];
          int tres[][]=new int[t*(t-1)/2][maxc];
          int rescount=0;
        int data[][]=new int[t][maxc],trans=0,minc;
        System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
        minc=s.nextInt();
        int init[];
        int rows=0,level=1;
        for(int i=1;i<range;i++)
            A[i]=0;
        for(int i=0;i<(t/2+1);i++)
        {
        for(int j=0;j<maxc;j++)
            data[i][j]=-1;
        }
        for(int i=0;i<(t*(t-1))/2;i++)
        {
        for(int j=0;j<maxc;j++)
        {
            tres[i][j]=-1;
            res[i][j]=-1;
        }
        }
   FileReader fin =new FileReader("data.txt");
    BufferedReader br =new BufferedReader(fin);
    String st;
    int pars=0;
    while(pars<parts){
        for(int i=1;i<range;i++)
            A[i]=0;
        for(int i=0;i<(t/2+1);i++)
        {
        for(int j=0;j<maxc;j++)
            data[i][j]=-1;
        }
        trans=0;
         for(int i=0;i<(t*(t-1))/2;i++)
        {
        for(int j=0;j<maxc;j++)
        {
            tres[i][j]=-1;
     //       res[i][j]=-1;
        }
        }
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
        }while(trans<t/2 && pars<(parts-1) || (pars==parts-1) && st!=null);
        app.appriorifun(trans,maxc,range,A,data,minc/parts,tres);
  /*      for(int i=0;i<(t*(t-1))/2;i++)
        {
        for(int j=0;j<maxc;j++)
        {
           System.out.print(tres[i][j]+" ");
     //       res[i][j]=-1;
        }
        System.out.println();
        }*/
        int resflag=0;
        for(int i=0;tres[i][0]!=-1;i++)
        {
            resflag=1;
                for(int k=0;k<rescount;k++)
                {
           //         System.out.println("in cmp");
                    if(is_same(res[k],tres[i])==1)
                    {
                       resflag=0;
                       break;
                    }
                }
                if(resflag==1 || rescount==0)
                {
                    for(int j=0; tres[i][j]!=-1; j++)
                    {
                        res[rescount][j]=tres[i][j];
                    }
                    rescount++;
                }
        }
        pars++;
    }
    int finalcount[]=new int[rescount];
        br.close();
        fin.close();
          fin =new FileReader("data.txt");
  br =new BufferedReader(fin);
                 do
        {
            int h=0;
            int tran[]=new int[maxc];
            for(int i=0;i<maxc;i++)
                tran[i]=-1;
            int p=0;
            st=br.readLine();
            if(st!=null)
            {
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                int k=Integer.parseInt(str.nextToken());
        //        if(res[i][h]==k)
        //          h++;
                tran[p++]=k;
            }
            for(int i=0;i<rescount;i++)
            {
                h=0;
                for(int j=0;j<p ;j++)
                {
                    if(tran[j]==res[i][h])
                        h++;
                }
                if(res[i][h]==-1)
                    finalcount[i]++;
            }
                    
            
            }
        }while(st!=null);
         for(int i=0;i<rescount;i++)
         {
             if(finalcount[i]>=minc)
             {
                 for(int j=0;res[i][j]!=-1;j++)
                     System.out.print(res[i][j]+" ");
                 System.out.println();
             }
         }
    }
}
class Appriori{
    
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
    void appriorifun(int trans,int maxc,int range,int A[],int data[][],int minc,int tres[][]) throws Exception
    {
            // t -no of transactions & range = item no range & maxc =max item sets in a transaction
   //     int A[]=new int[range];
 //       int data[][]=new int[t][maxc],trans=0,minc;
 /*       System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
        minc=s.nextInt();*/
 //      System.out.println("yes");
        int trescount=0;
        int init[];
        int rows=0,level=1;
        for(int i=1;i<range;i++)
            A[i]=0;
   /*     for(int i=0;i<t;i++)
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
        fin.close();*/
         for(int i=0;i<trans;i++)
        {
        for(int j=0;j<maxc;j++)
        {
            if(data[i][j]!=-1)
            {
                A[data[i][j]]++;
            }
        }
    //      System.out.print(data[i][j]+" ");
      //  System.out.println();
        }

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
//      System.out.println(a[i][0]);
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
   //                 System.out.print(b[i][j]+" ");
                    tres[trescount][j]=b[i][j];
                }
                trescount++;
  //             System.out.println();
            }
                a=new int[(rows*(rows-1))/2][level+1];
                a=b;
                rows=c;
                level++;
        }
    }
    
}
