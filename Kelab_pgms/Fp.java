package javaapplication1;
import java.io.*;
import java.util.*;
class Item
{
	int range,max,trans;
	int count[];
	int item[];
	int data[];
        int cur[];
        boolean present[];
	int height;
        int minc;
	public Item()
	{
		range=6;
		max=6;
		height=range-1;
		trans=0;
		count=new int[range];
		item=new int[range];
		data=new int[range];
                cur=new int[range];
                present=new boolean[range];
		for(int i=1;i<range;i++)
		{
			item[i]=0;
                        count[i]=0;
                        data[i]=i;
		}

	}
}
class Fp extends Item
{
//	class Table;
	 class Tree
	{
		int count1;
                int cc;
		int data1;
		Tree pptr;
		Tree next2;
		Tree cptr[]=new Tree[range-1];
		public Tree()
		{
                    cc=0;
			count1=0;
			data1=-1;
			pptr=null;
                        next2=null;
			for(int i=0;i<range-1;i++)
			{
                      //      cptr[i]=new Tree();
				cptr[i]=null;
			}
		}
	}
	class Table
	{
		int data2;
		int count2;
		Tree next2;
		void init(int i)
		{
			data2=data[i];
			count2=item[i];
			next2=null;
		}
	}
        Tree t;
        Table tab[];
	public Fp(int c)
	{
		minc=c;
                t=new Tree();
                tab=new Table[height];
                for(int i=1;i<height;i++)
                {
                    tab[i]=new Table();
                    tab[i].init(i);
                }
           /*     for(int i=0;i<range-1;i++)
                {
                    t.cptr[i]=new Tree();
                }*/
	}
        Tree insert()
        {
            Tree temp=new Tree();
            return temp;
        }
        Tree tree_const(int p)
        {
            int i=0,j=0;
            int flag=0;
            Tree tp=t;
            while(j<p)
            {
                flag=0;
                i=0;
                 while(i<range-1 && tp.cptr[i]!=null )
            {
                if(tp.cptr[i].data1==cur[j])
                {
                    System.out.println("found");
                    tp.cptr[i].count1++;
                    tp=tp.cptr[i];
                    flag=1;
                    break;
                }
                i++;
            }
                 if(flag==0)
                 {
                       System.out.println("not found");
                       tp.cptr[tp.cc]=insert();
              //       tp.cptr[tp.cc]=new Tree();
                     tp.cptr[tp.cc].cc=0;
                     tp.cptr[tp.cc].pptr=tp;
                     tp.cptr[tp.cc].count1=1;
                     tp.cptr[tp.cc].data1=cur[j];
                       
                     Tree ta=tab[cur[j]].next2;
                     if(ta==null)
                         ta=tp.cptr[tp.cc];
                     else
                     {
                          while(ta.next2!=null)
                         ta=ta.next2;
                     ta.next2=tp.cptr[tp.cc];
                     }
                 tp=tp.cptr[tp.cc];
                      tp.cc++;
                 }
                 j++;
            }
           
   return t;
        }
        void print_tree(Tree tb)
        {
      //      Tree tb=t;
            if(tb!=null)
            {
            for(int i=0;i<=tb.cc;i++)
            {
                if(tb.cptr[i]!=null)
                {
                    
                System.out.println(tb.cptr[i].data1+" "+tb.cptr[i].count1);
                print_tree(tb.cptr[i]);
                }
            }
            System.out.println();
           }
        }
        void const_tree()
        {
            for(int i=0;i<range;i++)
            cur[i]=-1;
       //     System.out.println("trans");
            int p=0;
            for(int i=1;i<height;i++)
            {
                if(present[data[i]]==true)
                {
                    cur[p++]=data[i];
                }
            } 
            System.out.println("after sort");
           for(int i=0;i<p;i++)
            {
                System.out.println(cur[i]);
            }
           // tree construct
           t=tree_const(p);
           print_tree(t);
        }
        void read_data() throws Exception
        {
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
                item[k]++;
                count[k]++;
            }
            }
            trans++;
        }while(st!=null);
        br.close();
        fin.close();
     //   sort();
  /*       System.out.println("before sort");
        for(int i=1;i<range;i++)
            System.out.println(data[i]+" "+item[i]);*/
        int k=range-1;
        for(int i=1;i<range;i++)
        {
            for(int j=1;j<k-1;j++)
            {
                if(item[j]<item[j+1])
                {
                    int temp=item[j];
                    item[j]=item[j+1];
                    item[j+1]=temp;
                     temp=data[j];
                    data[j]=data[j+1];
                    data[j+1]=temp;
                }
            }
        }
   /*     System.out.println("after sort");
        for(int i=1;i<range;i++)
            System.out.println(data[i]+" "+item[i]);*/
        for(int i=range-2;i>=1;i--)
        {
            if(item[i]<minc)
                height--;
            else
                break;
        }
        
/*        System.out.println("after min");
        for(int i=1;i<height;i++)
            System.out.println(data[i]+" "+item[i]);*/
        // read again
       fin =new FileReader("data.txt");
    br =new BufferedReader(fin);
        do
        {
            int i=0;
            st=br.readLine();
            for(int p=1;p<range;p++)
                present[p]=false;
            if(st!=null)
            {
            StringTokenizer str=new StringTokenizer(st);
            while(str.hasMoreTokens())
            {
                k=Integer.parseInt(str.nextToken());
                present[k]=true;
              //  cur[i++]=k;
        //        item[k]++;
        //        count[k]++;
            }
            const_tree();
     //       break;          // remove this
            }
        //    trans++;
        }while(st!=null);
        br.close();
        fin.close();
   //     System.out.println("child cou"+t.cc);
   //     print_tree(t);
        }
	public static void main(String args[]) throws Exception
	{
             System.out.println("enter min count");
        Scanner s=new Scanner(System.in);
       int minc=s.nextInt();
		Fp f=new Fp(minc);
      //          Tree t=new Tree();
                
                f.read_data();
	}
}