package com.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KDNode{
    KDNode left;
    KDNode right;
    int []data;
    int type; //vertical.even=0, else 1

    public KDNode(){
        left=null;
        right=null;
    }

    public KDNode(int []x){
        left=null;
        right=null;
        data = new int[2];
        for (int k = 0; k < 2; k++)
            data[k]=x[k];
    }
}
class Rectangle{
	int x1, x2, y1, y2;
	public Rectangle(int a, int b, int c, int d) {
		x1=a; x2=b; y1=c; y2=d;
	}
	boolean contain(int x, int y){
		return (x1<=x&&x<=x2 &&y1<=y&&y<=y2);
	}
}
class KDTreeImpl{
    KDNode root;
    int cd=0;
    int DIM=2;

    public KDTreeImpl() {
        root=null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int []x){
        root = insert(x,root,cd);
    }
    private KDNode insert(int []x,KDNode t,int cd){
        if (t == null)
            t = new KDNode(x);
        else if (x[cd] < t.data[cd])
            t.left = insert(x, t.left, (cd+1)%DIM);
        else
            t.right = insert(x, t.right, (cd+1)%DIM);
        return t;
    }

    
    public List<int[]> rangeSearch(Rectangle r,int cd){
    	return rangeSearch(root, r, cd);
    }
    public List<int[]> rangeSearch(KDNode t,Rectangle r,int cd){
    	List<int[]> result = new ArrayList<int[]>();
    	int left, right, median;
    	if(cd%2==0) //even.vertical
    	{
    		left = r.x1; 
    		right=r.x2;
    		median = t.data[0];
    	}
    	else
    	{
    		left = r.y1; 
    		right=r.y2;
    		median = t.data[1];
    	}
        
    	if((left<=median && median <=right) && r.contain(t.data[0], t.data[1]))
    	{
    		result.add(t.data);
    	}
    	
    	if(t.left!=null &&left<median) result.addAll(rangeSearch(t.left, r, cd+1));
    	if(t.right!=null &&right>median) result.addAll(rangeSearch(t.right, r, cd+1));
    	
    		
    	return result;
    		
    }
    
    
    public boolean search(int []data){
        return search(data,root,0);
    }

    private boolean search(int []x,KDNode t,int cd){
        boolean found=false;
        if(t==null){
            return false;
        }
        else {
            if(x[cd]==t.data[cd]){
                if(x[0]==t.data[0] && x[1]==t.data[1]) 
                return true;
            }else if(x[cd]<t.data[cd]){
                found = search(x,t.left,(cd+1)%DIM);
            }else if(x[cd]>t.data[cd]){
                found = search(x,t.right,(cd+1)%DIM);
            }
            return found;
        }
    }

    public void inorder(){
        inorder(root);
    }
    private void inorder(KDNode r){
        if (r != null){
            inorder(r.left);
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
            inorder(r.right);
        }
    }
    public void preorder() {
        preorder(root);
    }
    private void preorder(KDNode r){
        if (r != null){
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
            preorder(r.left);             
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }
    private void postorder(KDNode r) {
        if (r != null){
            postorder(r.left);             
            postorder(r.right);
            System.out.print("("+r.data[0]+","+r.data[1] +") ");
        }
    }
}
public class KDTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KDTreeImpl kdt = new KDTreeImpl();
        int x[] = new int[2];
        x[0] = 30;
        x[1] = 40;
        kdt.insert(x);

        x[0] = 5;
        x[1] = 25;
        kdt.insert(x);

        x[0] = 10;
        x[1] = 12;
        kdt.insert(x);

        x[0] = 70;
        x[1] = 70;
        kdt.insert(x);

        x[0] = 50;
        x[1] = 30;
        kdt.insert(x);
        System.out.println("Input Elements");
        System.out.println("(30,40) (5,25) (10,12) (70,70) (50,30)\n\n");
        System.out.println("Printing KD Tree in Inorder");
        kdt.inorder();
        System.out.println("\nPrinting KD Tree in PreOder");
        kdt.preorder();
        System.out.println("\nPrinting KD Tree in PostOrder");
        kdt.postorder();
        System.out.println("\nsearching...............");
        x[0]=40;x[1]=40;
        System.out.println(kdt.search(x));
        
        List<int[]> result = kdt.rangeSearch( new Rectangle(5,70,25,70), 0);
        System.out.println(result.size());
        for (int i=0;i <result.size();i++){
        	System.out.println(result.get(i)[0]+","+result.get(i)[1]);
        }
    }
}