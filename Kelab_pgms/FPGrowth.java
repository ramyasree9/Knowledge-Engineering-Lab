/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.*;
import java.io.*;
/**
 *
 * @author c137212
 */
class Node
{
    int data;
    int count;
    ArrayList<Node> child = new ArrayList <Node>();
   Node()
    {
        this.data=-1;
        this.count=0;
    }
     Node(int data,int count)
    {
        this.data=data;
        this.count=count;
    }
    void add(int data)
    {
        Node n =new Node(data,1);
        this.child.add(n);
    }
}
public class FPGrowth {
    
    public static void main(String args[])
    {
        int minc;
        System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
        minc=s.nextInt();
        
    }
}
