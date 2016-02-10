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
class buck
{
    int bucket[][];
    int elecount[];
    int index;
    int buckcount;
}
class hashimp 
{
     buck b[];
    void init(int count,int n)
    {
         b=new buck[n];
        for(int i=0;i<n;i++)
        {
            b[i]=new buck();
           b[i].bucket= new int[n][count];
           b[i].elecount= new int[n];
           for(int j=0;j<(n);j++)
             b[i].elecount[j]=0;  
           b[i].index=0;
            b[i].buckcount=0;
        }
    }
     void hashfun(int data[],int n,int count,int minc)
    {
     //   System.out.println("yes");
        for(int i=0;i<n && data[i]!=-1;i++)
        {
            for(int j=i+1;j<n && data[j]!=-1;j++)
            {
                int s=data[i]+data[j];
        //        System.out.println(data[i]+" "+data[j]);
                 s%=n;
         //        System.out.println(s);
                 int flag=0;
                 for(int p=0;p<b[s].index;p++)
                 {
                     if(data[i]==b[s].bucket[p][0] && b[s].bucket[p][1]==data[j])
                     {
                         flag=1;
                         b[s].elecount[p]++;
                 //        System.out.println(b[s].elecount[b[s].]);
                         b[s].buckcount++;
                         break;
                     }
                 }
                 if(flag==0){
                     b[s].elecount[b[s].index]=1;
                 b[s].bucket[b[s].index][0]=data[i];
                 b[s].bucket[b[s].index][1]=data[j];
         //        System.out.println(b[s].bucket[b[s].index][0]+" "+b[s].bucket[b[s].index][1]);
                 b[s].index++;
                 b[s].buckcount++;
                 }
             }
          }
      }
     void countbucks(int n,int minc,int count)
     {
         for(int i=0;i<n;i++)
         {
             if(b[i].buckcount>=minc)
             {
                 for(int j=0;j<b[i].index;j++)
                 {
                    if(b[i].elecount[j]>=minc)
                     {
                         System.out.println(b[i].bucket[j][0]+" "+b[i].bucket[j][1]);
                     }
                 }
             }
         }
     }
    }
public class Hash {
    public static void main(String args[]) throws Exception
    {
        hashimp h=new hashimp();
        int maxc=6,range=6;
        h.init(2,maxc);
        int data[];
        int a[]=new int[range];
        for(int i=0;i<range;i++)
            a[i]=0;
       System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
       int minc=s.nextInt();
         FileReader fin =new FileReader("data.txt");
        BufferedReader br =new BufferedReader(fin);
         String st;
        int count=1;
        do
        {
            int i=0;
            st=br.readLine();
            data=new int[maxc];
            for(int j=0;j<maxc;j++)
            data[j]=-1;
            if(st!=null)
            {
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                int k=Integer.parseInt(str.nextToken());
                a[k]++;
                data[i++]=k;
            }
            count++;
            h.hashfun(data,maxc,count,minc);
            }
        }while(st!=null);
        for(int i=0;i<range;i++)
        {
            if(a[i]>=minc)
              System.out.println(i);
        }
 //       System.out.println("two");
        h.countbucks(maxc,minc,count);
        br.close();
        fin.close();
    }
}
