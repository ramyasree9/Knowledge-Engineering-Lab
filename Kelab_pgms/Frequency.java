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
public class Frequency
{
    public static void main(String args[]) throws IOException
    {
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
    for(int i=1;i<=100;i++)
    {
        if(A[i]!=0)
        System.out.println(i+" "+A[i]);
    }
    }
}
