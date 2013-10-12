package com.kellyfj.codingkata.trees;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeNode {
    
    private int value;
    protected BinaryTreeNode left;
    protected BinaryTreeNode right;
    
    public BinaryTreeNode(int value)
    {
        this.value=value;
    }
    
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public BinaryTreeNode getLeft() {
        return left;
    }
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }
    public BinaryTreeNode getRight() {
        return right;
    }
    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
    
    public int getSize() {
        int leftSize = left == null ? 0 : left.getSize();
        int rightSize = right == null ? 0 : right.getSize();
        return 1 + leftSize + rightSize;
    }

    public int getDepth()
    {
        int leftDepth = left == null ? 0 : left.getDepth();
        int rightDepth = right == null ? 0 : right.getDepth();
        
        if(leftDepth > rightDepth)
            return leftDepth+1;
        else
            return rightDepth +1;
    }
    
    public BinaryTreeNode getDeepestNode()
    {
        Queue<BinaryTreeNode> queue = new LinkedBlockingQueue<BinaryTreeNode>();
        queue.add(this);
        BinaryTreeNode answer = null;
        while(!queue.isEmpty())
        {
            answer= queue.poll();
            if(answer.left!=null)
                queue.add(answer.left);
            if(answer.right!=null)
                queue.add(answer.right);
        }
        return answer;
    }
    
    public boolean isStructurallySame(BinaryTreeNode e)
    {
        if(e==null)
            return false;
        
        boolean b1 = e.getValue() == this.getValue();
        boolean b2 = this.getLeft() == null ? e.getLeft() == null : this.getLeft().isStructurallySame(e.getLeft());
        boolean b3 = this.getRight() == null ? e.getRight() == null : this.getRight().isStructurallySame(e.getRight());
        
        return b1 && b2 && b3;
    }
    
    public int getSumOfAllLeafValues()
    {
            int ansLeft = left==null ? 0 : left.getSumOfAllLeafValues();
            int ansRight = right == null ? 0 : right.getSumOfAllLeafValues();
            return this.value + ansLeft + ansRight;
    }
    
    public String serialize() {
        StringBuilder retVal = new StringBuilder();
        if (left == null && right == null) {
            retVal.append(value + ",L;");
        } else {
            retVal.append(value + ",N;");
            if (left != null)
                retVal.append(left.serialize());
            if (right != null)
                retVal.append(right.serialize());
        }
        return retVal.toString();
    }
    
    public static BinaryTreeNode deserialize(String s) {
        if(s==null || s.trim().length()==0)
            throw new IllegalArgumentException("String cannot be null or blank");
        
        String[] nodes = s.split(";");
        if(nodes.length==0) 
            throw new IllegalArgumentException("String does not contain any nodes");
        
        Queue<String> q = new ArrayBlockingQueue<String>(nodes.length);
        for(int i=0; i< nodes.length; i++) {
            q.add(nodes[i]);
        }
        return reconstruct(q);
        
    }

    private static BinaryTreeNode reconstruct(Queue<String> q) {
        BinaryTreeNode node = null;
        if(!q.isEmpty()) {
            String s = q.poll();
            String[] nodeDetails = s.split(",");
            String value = nodeDetails[0];
            boolean isLeaf = nodeDetails[1].equals("L");
           
            node = new BinaryTreeNode(new Integer(value));
            if(!isLeaf) {
                node.setLeft(reconstruct(q));
                node.setRight(reconstruct(q));
            }           
        }
        return node;        
    }
    
    @Override
    public boolean equals(Object o) {
        if(o==null)
            return false;
        if(o == this)
            return true;
        if(! (o instanceof BinaryTreeNode))
            return false;
        BinaryTreeNode other = (BinaryTreeNode)o;
        
        boolean b1 = this.value == other.getValue();
        if(b1==false)
            return false;
        
        boolean leftEquals=false;
        if(this.getLeft()==null && other.getLeft()==null)
            leftEquals = true;
        else if ((this.getLeft()==null && other.getLeft()!=null) ||
                 (this.getLeft()!=null && other.getLeft()==null)) {
            leftEquals=false;
        } else
        {
            leftEquals = this.getLeft().equals(other.getLeft()) ;
        }
        if(leftEquals==false)
            return false;  
        
        
        boolean rightEquals=false;
        if(this.getRight()==null && other.getRight()==null)
            rightEquals = true;
        else if ((this.getRight()==null && other.getRight()!=null) ||
                 (this.getRight()!=null && other.getRight()==null)) {
            rightEquals=false;
        } else
        {
            rightEquals = this.getRight().equals(other.getRight()) ;
        }
        return b1 && leftEquals && rightEquals;
    }
}
