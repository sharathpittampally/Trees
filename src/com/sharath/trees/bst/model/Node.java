package com.sharath.trees.bst.model;

public class Node 
{
	private int data;
	private Node left;
	private Node right;
	
	public static Node create(int data)
	{
		return new Node(data);
	}
	
	private Node(int data)
	{
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setLeft(Node left)
	{
		this.left = left;
	}
	
	public void setRight(Node right)
	{
		this.right = right;
	}
	
	public int getData()
	{
		return data;
	}
	
	public void setData(int data)
	{
		this.data = data;
	}
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
	}
}
