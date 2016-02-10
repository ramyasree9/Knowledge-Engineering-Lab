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
public class Data
{
    public static void main(String args[]) throws IOException
    {
         int n;                         // no of transactions
         int range=20,maxc=10;          //range = item no range & maxc =max item sets in a transaction
    System.out.println("enter n");
    Scanner s=new Scanner(System.in);
      n=s.nextInt();
    FileWriter fout=new FileWriter("data.txt");
     for(int i=1;i<n;i++)
     {
         for(int j=0;j<(int)(Math.random()*maxc+1);j++)
         {
             int a=(int)(Math.random()*range+1);
             fout.write(Integer.toString(a));
    //        System.out.println(a);
             fout.write(" ");
         }
         fout.write("\r\n");
     }
    fout.close();
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
                System.out.print(Integer.parseInt(str.nextToken())+" ");
            }
            }
            System.out.println();
        }while(st!=null);
        br.close();
        fin.close();
    }
}
