



import java.io.*;
import java.util.*;

class Node
 {
	public int id;
	public int count;
	public char color;
	Node left,right,parent;
	
	
		
	public Node()
	{	
	  this.id = -1;
	  this.count = -1;
	  color = '-';
	  left = right = parent = this; 
	}
	
	
	public Node(int id, int count)
	{
	   this.id =id;
	   this.count=count;
	   color = '-';
	   Node nil = new Node();
	   left =right=parent=nil;
	}
	
	//Checks if a given node is a null or sentinel node
	public boolean isNil()
	{
	  if(this.id==-1 && this.count ==-1)
            return true;
	  else return false;
	}
 
      	public void print()
	{
	  System.out.println(this.id+" "+this.count);
	}
	
 }


class Tree
 {
	Node root = new Node();
	Node temp = new Node();
	
	public Tree(){}
	
	public void insert(Node n)
	{
	 n.color ='r';
         if(this.root.isNil())
	    this.root =n;		
	 else
	    this.root = insert(root,n);
	 insertFixup(n);  	
	}
	
	private Node insert(Node trnode, Node n)
	{
	  if(trnode.isNil())
	   {
             trnode = n;
             return trnode;
	   }
	  else
	    if(n.id <= trnode.id)
	     {	
		n.parent = trnode;
		trnode.left =insert(trnode.left, n);
	     }	
	     else if(n.id > trnode.id)
                   {
		     n.parent = trnode;
		     trnode.right =insert(trnode.right, n);
	           }
	   return trnode;
         }
	
	//Left rotates the tree
	public void l_rot(Node x)
	{
	 if(x.isNil()) 
           return;	
	 else{
		Node y = x.right;
		x.right = y.left;
		if(!y.left.isNil())
                  y.left.parent = x;
		
		 y.parent = x.parent;
		
		if(x.parent.isNil())
		  root = y;
		else if(x==x.parent.left)
		  x.parent.left = y;
		else x.parent.right = y;
		
		y.left = x;
		x.parent = y;
	     }
         }
	//Right rotates the node
	public void r_rot(Node x)
	{
	  if(x.isNil()) 
	    return;	
	  else
	  {		
	    Node y = x.left;
            x.left = y.right;
	    if(!y.right.isNil())
	       y.right.parent = x;
	    y.parent = x.parent;
	    if(x.parent.isNil())
		  root = y;
	    else if(x == x.parent.right)
	            x.parent.right =y;
            else
	      x.parent.left =y;       
	    y.right =x;
	    x.parent =y;
	   }
	}       
	
	// Maintains RB properties after insering node
	public void insertFixup(Node n)
	{
	  while((!n.parent.isNil()) && n.parent.color == 'r')
	   {
	     Node y;
             if(n.parent == n.parent.parent.left)
	       {
		 y = n.parent.parent.right;
		 if((!y.isNil()) && y.color =='r')
	            {
	             n.parent.color ='b';
		     y.color ='b';
		     n.parent.parent.color = 'r';
		     n = n.parent.parent;
	            }
		       
	          else if(n==n.parent.right)   
	               {
			n=n.parent;
	                l_rot(n);
	               }
	        n.parent.color = 'b';
	        n.parent.parent.color ='r';
	        r_rot(n.parent.parent)
	       }	
	      else
	      {			    
		y = n.parent.parent.left;
		if((!y.isNil()) && y.color =='r')
	          {
		    n.parent.color ='b';
		    y.color ='b';
		    n.parent.parent.color = 'r';
		    n = n.parent.parent;
	          }			    
	        else if(n==n.parent.left)     
	         {
		   n=n.parent;
	           r_rot(n);
		 }
		n.parent.color = 'b';
	        n.parent.parent.color ='r';
	        l_rot(n.parent.parent);
              }
            }
	    root.color = 'b';	
       }
	
       public void transplant(Node x, Node y)
       {
	 if(x.parent.isNil())
           this.root = y;
         else if(x ==x.parent.left)
                 x.parent.left = y;
	      else x.parent.right = y;
		 y.parent = x.parent;
       }

       public Node treeMin(Node x)
       {
	while(!x.left.isNil())
          x = x.left;
	return x;
	}
	 
	//Deletes the node from a tree
	public void delete(Node z)
	{
	 Node x,y;
         y = z;
         char orig = z.color;
         if(z.left.isNil())
	    {
	     x = z.right;
             transplant(z,z.right);
	    }
	 else if(z.right.isNil())    
             { 
	       x = z.right;   
	       transplant(z, z.left);
	     }
         else{
	      y = treeMin(z.right); 
	      orig = y.color;
	      x = y.right;
	      if(y.parent==z)  
	        x.parent=y;
	      else{
	           transplant(y,y.right);
	           y.right = z.right;
	           y.right.parent =y;
	          }
	     transplant(z,y);		        
	     y.left = z.left;
	     y.left.parent =y;
	     y.color = z.color;
	    }
	    if(orig == 'b')
            deleteFixup(x);
       }

	public void deleteFixup(Node x)
	{
	  while(x!= root && (x.color =='b' || x.color == '-'))
	  {
	    Node y;
            if(x==x.parent.left)
	    { 
              y = x.parent.right; 
              if(y.color =='r')
	       { 
		 y.color ='b';
	         x.parent.color ='r';
	         l_rot(x.parent);
	         y = x.parent.right;
	       }
	      if((y.left.color =='b'|| y.left.color == '-' )&& (y.right.color == 'b'|| y.right.color == '-'))
	        {
		  y.color = 'r';
		  x=x.parent;	   
		}    
	       else if(y.right.color =='b' || y.right.color =='-')
		    {
		     y.left.color = 'b';
	             y.color ='r';
	             r_rot(y);
		     y=x.parent.right;
		     }
	       y.color =x.parent.color;
               x.parent.color ='b';
	       y.right.color ='b';   
	       l_rot(x.parent);
	       x =root;
	    }
	    else
	     {
	      if(x==x.parent.right)
	       {
	         y = x.parent.left; 
	         if(y.color =='r')
	          {
		   y.color ='b';
		   x.parent.color ='r';
	           r_rot(x.parent);
		   y = x.parent.left;
	          }
	         if((y.left.color =='b'|| y.left.color == '-' )&& (y.right.color == 'b'|| y.right.color == '-'))
	          {
                   y.color = 'r';
	           x = x.parent;	   
	          }    
	         else if(y.left.color =='b' || y.left.color =='-')
		  {
		   y.right.color = 'b';
		   y.color ='r';
		   l_rot(y);
		   y=x.parent.left;
		  }
	         y.color =x.parent.color;
	         x.parent.color ='b';
	         y.left.color ='b';   
	         r_rot(x.parent);
	         x =root;
		}
	     }
         }
         x.color ='b';
       }
	
	// Prints the tree in preorder fashion
	public void print()
	 {
	   if(root.isNil())
	      System.out.println("Tree is empty!");	
	   else{
		print(root.left);
		System.out.println(root.id+" "+root.count+"\n");
		print(root.right);
	       }	
	  }
		
	private void print(Node n)
	 {
	    if(!n.isNil())
	    {
	      print(n.left);
	      System.out.println(n.id+" "+n.count+"\n");
	      print(n.right);
	     }	
	 }   
	
	public Node search(int id)
	 {
	   Node found= new Node();
	   if(root.isNil())
	     {
		 found = null;
	     }
	   if(this.root.id == id)
	      {
		return this.root;
	      }
	   else found = search(this.root, id);
	   return found;
	 }
	
	 public Node search(Node n, int id)
	 {
	   Node found = new Node();
	   if(n.isNil())
		{
		 found = null;
		}
	   if(n.id == id)
	     {
	       found = n;
	     }
	   else if(id < n.id)	
		 found = search(n.left, id);
		else if(id > n.id)
	              found = search(n.right, id);
           return found;
	 }
 }


public class bbst
 {
	//Creates a tree from a sorted array of elements from start to end
	public static Node createTree(Tree t, int arr[][], int start, int end)
	 {
	  Node nil = new Node();
	  if(start > end)
	    return nil;
          int mid = (start +end)/2;
		
          Node n = new Node(arr[mid][0],arr[mid][1]);
	  n.color ='b';	
	  n.left = createTree(t, arr, start,mid-1);
	  n.left.parent = n;
          n.right =createTree(t, arr, mid+1,end);
          n.right.parent = n;
          if(n.left.isNil() && t.root.right.isNil())
		{     
		 n.color ='r';
		}
	  return n;
         }
	
	//Increases the count of the node with id by m
	public static void increase(Tree t, int id , int m)
	 {
	   Node x = t.search(id);
	   if(x.isNil())
	    {
	      x = new Node(id, m);
	      t.insert(x);
	    }
	   else x.count += m;
	   System.out.println(x.count);
         }
	
	//decreases the count of the node with id by m
	public static void reduce(Tree t, int id , int m)
	 {
	  Node x = t.search(id);
	  if(x.isNil())
	   {
	     System.out.println(0); 
           }
	  else
	   {
	     x.count-=m;
	     if(x.count<=0)
	       {
		 t.delete(x);
	         System.out.println(0);
	       }
	     else
	       System.out.println(x.count);
	   }
         }
	
	// Prints the count of the node with id
	public static void count(Tree t, int id){
		
		Node x = t.search(id);
		
		if(x.isNil()){
		
			System.out.println(0);
					
		}
		 
		else System.out.println(x.count);
		
                
		
		
	}
	
	//Returns the successor of the node with id
	public static Node next(Tree t, int id){
		
	    Node x = t.search(id);
	    Node z;
	    Node nil = new Node();
	    
	    
	    if(x.isNil())
		 return nil;
	    
	    else if(!x.right.isNil())
	    {
	    	
	    	Node y = t.treeMin(x.right);	    
	    	return y;
	    }
	    	 
	    else
	    {
	    	z=x;
	    	
	    	
	    	while(z.parent.left!=z && (!z.parent.isNil()))
	    	{
	    		z =z.parent;
	    		
	    	}
	    	    
	    	 
	    	   return z.parent;
	    	  	    	
	      
	    	    
	    }
	  	   	
	}
	
	// Takes the id(either present in the tree or not) and prints the next node's id and count
	 public static void printNext(Tree t, int id){
		   
		Node x = t.search(id);
			Node y;
			Node z = new Node();
			 if(!x.isNil())
			   {
				 Node nex =next(t,id);
				 if(!nex.isNil())
				   nex.print();
				 else System.out.println("0 0");
			   }
		   
			 else if(x.isNil()){
				 
				 
		    y = t.root;
				 if(id <= y.id)
				   while(id <= y.id){
					   z = prev(t,y.id);
			            if(z.isNil())break;
			             y=z;
			     }   
			       if(!z.isNil())
			         y = next(t, y.id);     
				   
	              
				 
				 else if(id >= y.id)
				     {
					   while(id >= y.id && !y.isNil())
					     y = next(t, y.id);
					   
				     }			 
	 
			       if(y.isNil())
			    	   System.out.println("0 0");
			       else
			        y.print();
				  
				  
			 
			 }
		   
	   }
	
	 //Prints all nodes in the tree with ids between id1 and id2
	 public static void inRange(Tree t, int id1, int id2)
	 {
		 
		 if(t.root.isNil())
		System.out.println("0");	 
			 
		 
		 else{ 
			 
	     int count = inRange(t, t.root,id1,id2);
		 
		 System.out.println(count);
		 
		 }
	 }
	 
	 
	 private static int inRange(Tree t, Node x, int id1, int id2)
	 {
		
		 int count=0;
		 
		 if(x.isNil())
		   return 0;
		 
		 else if(id1<= x.id && id2 >= x.id)
		 {
			 count+=x.count;
			 
		 }		 
			 count+= inRange(t, x.left,id1,id2);
			 count+= inRange(t, x.right,id1,id2);
			 
		 
	      
		 return count;
	 
	 }
	 
	 //Prints the node previous to the given id(present or absent in the tree)
	 public static void printPrev(Tree t, int id){
		 
		Node x = t.search(id);
		Node y;
		Node z = new Node();
		 if(!x.isNil())
		   {
			 Node pre =prev(t,id);
			 if(!pre.isNil())
			   pre.print();
			 else System.out.println("0 0");
		   }
	   
		 else if(x.isNil()){
			 y = t.root;
			 if(id <= y.id)
			   while(id <= y.id && !y.isNil())
			      y = prev(t,y.id);     
			 else
			     {
				   while(id >= y.id){
				     
					   z = next(t,y.id);
				       if(z.isNil())break;
				       y=z;
				     }   
				   if(!z.isNil())
				  y = prev(t, y.id);
				   
			     }			 
 
			 if(y.isNil())
				 System.out.println("0 0");
			 else 
			  y.print();
		 
		 }
	 
	 } 
	 
	 //Returns the predecessor of the node with id
	 public static Node prev(Tree t, int id){
		 
		 Node x = t.search(id);
		 
		 if(x.isNil())
			 return x;
		 
		 else if(!x.left.isNil())
		      {
			    Node y = x.left;
			    
			    while(!y.right.isNil())
			         y=y.right;
			    
			    return y;
			 
		      }
		 
		 else 
		      {
			    Node z =x;
			    
			    while(z.parent.right!=z && (!z.parent.isNil()))
		    	{
		    		z =z.parent;
		    		
		    	}
		    	    
		    	 
		    	   return z.parent;
			    			 
		      }
		 
		 
		 
		 
	 }
	 
	 
	 
	 
	 public static void main(String args[]){
		 

	      // Initialize tree using Scanner class to read file from argument 
	        String s = new String();

                 Tree t = new Tree();

	        if(args[0]== null)
	             System.out.println("File expected");
	        else s = args[0];
try{
	       File f = new File(s);
	       Scanner sc = new Scanner(f); 
	       int size = sc.nextInt();
	       
	       int arr [][]= new int[size][2];
	       
	       for(int i=0;i<size;i++){
	    	   
	    	   arr[i][0] = sc.nextInt();
	    	   
	    	   arr[i][1] = sc.nextInt();
	    	   
	       }

	      sc.close();
	      
	     
	      
	      t.root =createTree(t, arr, 0, size-1);
	      
	      
	      
   }

catch(Exception e){e.printStackTrace();}
	       


//Reading commands from redirected stdin stream  
 
       Scanner sc = new Scanner(System.in);	

	
        String s3 = new String();

	while(sc.hasNextLine())		
	  {

                s3 = sc.nextLine();
              

                Scanner sc3 = new Scanner(s3);	

                
                String token = sc3.next();
               
               
              

                
                if(token.equals("increase"))
                  {
                     int i = sc3.nextInt();
                      
                     int j = sc3.nextInt();

                     increase(t,i,j);
                  }  

                 else if(token.equals("reduce"))
                  {
                     int i = sc3.nextInt();
                      
                     int j = sc3.nextInt();

                     reduce(t,i,j);
                  }  

                       else if(token.equals("count"))
                            {
                             
                             int i = sc3.nextInt();
                             
                             count (t,i);
                            }  

                              else if (token.equals("inrange"))
                                   {
                                    int i = sc3.nextInt();
                      
                                    int j = sc3.nextInt();

                                    inRange(t,i,j);
                                    }   

            
                                    else if(token.equals("next"))
                                          {
                                           int i = sc3.nextInt();
                                           printNext(t,i);
                                           }  


                                          else if(token.equals("previous"))
                                          {
                                           
                                           int i = sc3.nextInt();
                                           
                                           printPrev(t,i);
                                           }
                                                else if(token.equals("quit"))
                                                  break;

                                       
                                            

              }


                                 

 
		 
	 }
	 
	 
}
