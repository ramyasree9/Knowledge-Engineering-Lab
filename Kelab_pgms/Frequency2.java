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
public class Frequency2 {
    public static void main(String args[]) throws IOException
    {
         int count=0;
        int flag1=0,flag2=0;
        int A[]=new int[101];
        for(int i=1;i<=100;i++)
            A[i]=0;
   FileReader fin =new FileReader("data.txt");
    BufferedReader br =new BufferedReader(fin);
    String st;
        do
        {
            st=br.readLine();
            if(st!=null)
            {
                
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                A[Integer.parseInt(str.nextToken())]++;
            }
            }
        }while(st!=null);
        br.close();
        fin.close();
        int c[]=new int[101];
        int j=0;
        for(int i=1;i<=100;i++)
    {
        if(A[i]!=0)
        c[j++]=i;
    }
        int n=j,e,f;
        for(int i=0;i<n;i++)
        {
            e=c[i];
            for(int k=i+1;k<n;k++)
            {
                f=c[k];
                count=0;
                FileReader fin1 =new FileReader("data.txt");
    BufferedReader br1 =new BufferedReader(fin1);
    String st1;
        do
        {
            st1=br1.readLine();
            if(st1!=null)
            {
                
            StringTokenizer str=new StringTokenizer(st1);
            while(str.hasMoreTokens())
            {
                int p=Integer.parseInt(str.nextToken());
                if(p==e)
                    flag1=1;
                if(p==f)
                    flag2=1;
            }
            if(flag1==1 && flag2==1)
                count++;
            }
            flag1=0;
            flag2=0;
        }while(st1!=null);
        System.out.println(e+" "+f+" :"+count);
       br1.close();
        fin1.close();
            }
        }
    }
    
}
