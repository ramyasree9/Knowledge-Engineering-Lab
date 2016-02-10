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
class DIC1 {
    static void sort(int a[],int n)
    {
       // int pass,int k;
           int k=n-1;
    for(int pass=1;pass<n;pass++)
    {
    for(int i=0;i<k;i++)
    {
        if(a[i]>a[i+1])
        {
            int temp;
            temp=a[i];
        //  temp=i;
            a[i]=a[i+1];
            a[i+1]=temp;
    }
        }
        k=k-1;
        
    }
    }
    static int addsuper(int result[][],int resindex,int current[][],int curindex,int maxc,int A[],int range)
    {
    //    System.out.println("in addauper");
   /*     for(int i=0;i<maxc;i++)
            System.out.print(result[resindex-1][i]+" ");
        System.out.println();*/
        for(int i=1;i<range;i++)
        {
            int flag=0;
            int cand[]=new int[maxc];
            for(int r=0;r<maxc;r++)
                cand[r]=-1;
            if(A[i]!=0)
            {
                for(int j=0;j<maxc && result[resindex-1][j]!=-1;j++)
                {
                    if(result[resindex-1][j]==i)
                    {
                        flag=1;
                        break;
                    }
                }
            }
            if(flag==0)
            {
      //          System.out.println("not equal is "+i);
                int j;
                for(j=0;j<maxc && result[resindex-1][j]!=-1;j++)
                {
        //            if(result[resindex-1][j]!=-1)
                    cand[j]=result[resindex-1][j];
        /*            else if(result[resindex-1][j]==-1)
                    break;*/
                }
                cand[j]=i;
                sort(cand,j+1);
           /*     for(int l=0;l<maxc ;l++)
                {
                    System.out.print(cand[l]+" ");
                }*/
     //           System.out.println();
                int present=0;
                for(int k=0;k<curindex;k++)
                {
                    if(is_same(current[k],maxc,cand,j+1)==1)
                    {
                  //       System.out.println("always equal ");
                        present=1;
                        break;
                    }
                }
                if((present==0 || curindex==0) )
                {
             //        System.out.println("enetered ");
                    if(freq_subsets(cand,j+1,result,resindex,maxc)==1)
                {
        //            System.out.println("add new ");
                for(int k=0;k<maxc;k++)
                {
       //             System.out.print(cand[k]+" ");
                    current[curindex][k]=cand[k];
                }
          //      System.out.println();
                curindex++;
                }
                }
            }
        }
        return curindex;
    }
    static int freq_subsets(int cand[],int n,int result[][],int resindex,int maxc)
    {
        int sub[]=new int[n-1];
        for(int i=0;i<n;i++)
        {
            int c=0;
            int flag=0;
            for(int j=0;j<n;j++)
            {
                if(i!=j)
                    sub[c++]=cand[j];
            }
            for(int h=0;h<resindex;h++)
            {
                if(is_same(result[h],maxc,sub,n-1)==1)
                {
                    flag=1;
                    break;
                }
            }
                if(flag==0)
                    return 0;
        }
        return 1;
    }
 /*   static int freq_subsets(int cand[],int n,int result[][],int resindex,int maxc)
    {
        System.out.println("subsets check");
        for(int i=0;i<n;i++)
        {
            System.out.print(cand[i]+" ");
    //  System.out.println();
        }
          System.out.println();
        int pow_set_size=(int)Math.pow(2,n);
         System.out.println(pow_set_size);
        int sub[]=new int[n];
    for(int k=0;k<pow_set_size;k++)
    {
       for(int i=0;i<n;i++)
            sub[i]=-1;
             int c=0;
      for(int j = 0; j < n; j++)
       {
          if((k & (1<<j))!=0)
          {
            sub[c++]=cand[j];
          }
       }
      System.out.println("subsets");
      for(int p=0;p<n;p++)
          System.out.print(cand[p]+" ");
      System.out.println();
       int flag=0;
       if(c>0)
       {
        for(int i=0;i<resindex;i++)
        {
            if(is_same(result[i],maxc,sub,n)==1)
            {
                flag=1;
                break;
            }
        }
       }
        if(c>0 && flag==0)
            return 0;
    //   printf("\n");
    }
     return 1;  
    }*/
    static int is_same(int a[],int m,int b[],int n)
    {
        int flag=1,i;
        for(i=0;i<m && a[i]!=-1;i++)
        {
            if(i>=n)
                return 0;
            if(a[i]!=b[i])
                return 0;
        }
        if(i<n && b[i]!=-1)
            return 0;
        else
       return 1;
    }
	static int is_present(int data[],int n,int a[],int m)
	{
        int i;
		for(i=0;i<m && a[i]!=-1;i++)
        {

        }
        m=i;
			int x=0;
			for(int j=0;j<n && data[j]!=-1;j++)
			{
				if(x<m && data[j]==a[x])
				x++;
			}
			if(x==m)
			return 1;
        else
		return 0;
	}
    public static void main(String args[]) throws Exception
    {
        int t=6,range=5,maxc=5;
        int A[]=new int[range];
        int resindex=0,curindex=0;
        int max=(int)Math.pow(2,maxc-1);
        int current[][]=new int[max][maxc];
 //       int mark[]=new int[max];
        int count[]=new int[max];
        int visit[]=new int[max];
        int result[][]=new int[max][maxc];
        int trans=0,minc=2;
        int rows=0,level=1;
        for(int i=1;i<range;i++)
            A[i]=0;
       for(int i=0;i<max;i++)
        {
        for(int j=0;j<maxc;j++)
        {
            result[i][j]=-1;
             current[i][j]=-1;
         }
        }
        for(int i=0;i<max;i++)
        {
            count[i]=0;
            visit[i]=0;
   //         mark[i]=0;
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
            }
            }
            trans++;
        }while(st!=null);
        br.close();
        fin.close();
     for(int i=1;i<range;i++)
    {
        if(A[i]>=minc)
        {
    //        System.out.println("yes");
            result[resindex++][0]=i;
          // curindex=addsuper(result,resindex,current,curindex,maxc,A,range);
      /*     if(i==2)
               break;*/
        }
    }
     for(int i=0;i<resindex;i++)
        curindex=addsuper(result,i+1,current,curindex,maxc,A,range); 
 /*    for(int i=0;i<curindex;i++)
     {
         for(int j=0;j<maxc;j++)
         {
             System.out.print(current[i][j]+" ");
         }
         System.out.println();
     }*/
    int parts=2,partc=0;
  /*   System.out.println("enter no of check points");
        Scanner s=new Scanner(System.in);
        parts=s.nextInt();*/
    int flag=1;
    int data[]=new int[maxc];
            fin =new FileReader("data.txt");
    br =new BufferedReader(fin);
        while(flag==1)
        {
            partc++;
    int countp=0;
        do
        {
            int h=0;
            for(int p=0;p<maxc;p++)
                data[p]=-1;
            st=br.readLine();
            if(st!=null)
            {
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                int k=Integer.parseInt(str.nextToken());
                data[h++]=k;
            }
            for(int l=0;l<curindex;l++)
            {
                if(count[l]!=-1)
                {
                if(is_present(data,maxc,current[l],maxc)==1)
                {
             //       System.out.println("count inc");
                    count[l]++;
                }
          //      visit[l]++;
                }
            }
            }
           countp++;
        }while(countp<(trans/parts) ||  (partc==parts && st!=null));
        for(int l=0;l<curindex;l++)
        {
            if(count[l]!=-1)
            {
                visit[l]++;
            }
        }
            if(partc==parts)
            {
                br.close();
                fin.close();
                fin =new FileReader("data.txt");
                br =new BufferedReader(fin);
            }
            int old_ind=curindex;
            int old_res=resindex;
            // check for count
            for(int i=0;i<curindex;i++)
            {
                if(count[i]>=minc)
                {
                   // result[resindex++]
                    count[i]=-1;
          //              System.out.println("result");
                    for(int j=0;j<maxc;j++)
                    {
                        result[resindex][j]=current[i][j];
          //               System.out.print(result[resindex][j]+" ");
                    }
          //          System.out.println();
                    resindex++;
                    
         /*           curindex=addsuper(result,resindex,current,curindex,maxc,A,range);
                    if(old_ind==curindex)
                        flag=0;*/
                }
                else if(visit[i]>=parts)
                {
                    count[i]=-1;
                }
            }
            for(int d=old_res;d<resindex;d++)
            {
                curindex=addsuper(result,d+1,current,curindex,maxc,A,range);
            }
            if(old_ind==curindex)
                        flag=0;
           if(parts==partc)
           {
                partc=0;
           }
        }
        System.out.println("result");
        for(int i=0;i<resindex;i++)
            {
                for(int j=0;j<maxc && result[i][j]!=-1;j++)
                {
                    System.out.print(result[i][j]+" ");
                }
                System.out.println();
            }
    }
    
}
