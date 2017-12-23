package com.sharath.trees.binarytree;

/**
 * A balanced BST is known as AVL tree. ref:
 * https://www.youtube.com/watch?v=rbg7Qf8GkQ4
 * 
 * @author sharath
 */
public class AVLTreeInsertion
{
    public static void main(String[] args)
    {
        AVLTreeInsertion avl = new AVLTreeInsertion();
        Node root = avl.insert(null, 10);
        Node root1 = avl.insert(root, 20);
        Node root2 = avl.insert(root1, 30);
        Node root3 = avl.insert(root2, 40);
        Node root4 = avl.insert(root3, 50);
        Node root5 = avl.insert(root4, 25);
        avl.preOrder(root5);
    }

    /**
     * 
     * @param root
     * @param data
     * @return
     */
    Node insert(Node root, int data)
    {
        if (root == null)
        {
            return new Node(data);
        }
        if (root.data >= data)
        {
            root.left = insert(root.left, data);
        }
        else
        {
            root.right = insert(root.right, data);
        }
        int diff = height(root.left) - height(root.right);
        // This means either left-left or left-right
        if (diff > 1)
        {
            // left-left case. so rotate right.
            if (height(root.left.left) > height(root.left.right))
            {
                return rightRotate(root);
            }
            // left-right case. So rotate left and then right.
            else
            {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        // This means either right-right or right-left
        if (diff < -1)
        {
            // right-right so rotate left.
            if (height(root.right.right) > height(root.right.left))
            {
                return leftRotate(root);
            }
            // right-left. so rotate right then left.
            else
            {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    /**
     * 
     * @param root
     * @return
     */
    Node rightRotate(Node root)
    {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    /**
     * 
     * @param root
     * @return
     */
    Node leftRotate(Node root)
    {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    int height(Node root)
    {
        if (root == null)
            return 0;
        return root.height;
    }

    /**
     * Printing.
     * 
     * @param node
     */
    void preOrder(Node node)
    {
        if (node != null)
        {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private static class Node
    {
        int height;
        int data;
        Node left;
        Node right;

        Node(int data)
        {
            this.data = data;
            height = 1;
            left = null;
            right = null;
        }
    }
}
