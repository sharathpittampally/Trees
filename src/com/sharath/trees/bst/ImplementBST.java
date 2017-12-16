package com.sharath.trees.bst;

import java.util.LinkedList;
import java.util.Queue;

import com.sharath.trees.bst.model.Node;

public class ImplementBST
{
    public static void main(String[] args)
    {
        System.out.println("Inserting a bunch of elements: ");
        Node root = insert(15, null);
        insert(10, root);
        insert(12, root);
        insert(8, root);
        insert(6, root);
        insert(7, root);
        insert(20, root);
        insert(25, root);
        insert(17, root);
        printTree(root);
        System.out.println();
        System.out.println("Minimum element in the tree: " + findMin(root).getData());
        System.out.println("Maximum element in the tree: " + findMax(root).getData());
        System.out.println("Height of the tree: " + height(root));
        System.out.println("Predorder traversal: ");
        preOrderTraversal(root);
        System.out.println();
        System.out.println();
        System.out.println("Inorder traversal: ");
        inOrderTraversal(root);
        System.out.println();
        System.out.println();
        System.out.println("PostOrder traversal: ");
        postOrderTraversal(root);
        System.out.println();
        System.out.println();
        System.out.println("Level order traversal: ");
        levelOrderTraversal(root);
        System.out.println();
        System.out.println();
        System.out.println("Is the given tree a BST?: " + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println();
        System.out.println("Inorder successor of 12: " + inorderSuccessor(root, 12).getData());
        System.out.println("Inorder successor of 10: " + inorderSuccessor(root, 10).getData());
        System.out.println("Inorder successor of 17: " + inorderSuccessor(root, 17).getData());
        System.out.println("Inorder successor of 6: " + inorderSuccessor(root, 6).getData());
        System.out.println("Inorder successor of 8: " + inorderSuccessor(root, 8).getData());
        System.out.println();
        Node root1 = delete(root, 10);
        printTree(root1);
        System.out.println();
        Node root2 = delete(root1, 6);
        printTree(root2);
        System.out.println();
        Node root3 = delete(root2, 7);
        printTree(root3);
    }

    /**
     * Insert the data into the BST.
     * 
     * @param data
     * @param root
     * @return
     */
    private static Node insert(int data, Node root)
    {
        if (root == null)
        {
            root = Node.create(data);
        }
        else if (root.getData() > data)
        {
            root.setLeft(insert(data, root.getLeft()));
        }
        else
        {
            root.setRight(insert(data, root.getRight()));
        }
        return root;
    }

    // Prints in ascending order.
    private static void printTree(final Node root)
    {
        if (root == null)
            return;
        printTree(root.getLeft());
        System.out.print(root.getData() + ",");
        printTree(root.getRight());
    }

    /**
     * Find the minimum element. -> left most element in the tree.
     * 
     * @param root
     * @return
     */
    private static Node findMin(final Node root)
    {
        if (root == null)
        {
            return null;
        }
        Node min = findMin(root.getLeft());
        if (min == null)
            return root;
        return min;
    }

    /**
     * find the maximum element. -> right most element in the tree.
     * 
     * @param root
     * @return
     */
    private static Node findMax(final Node root)
    {
        if (root == null)
        {
            return null;
        }
        Node max = findMax(root.getRight());
        if (max == null)
            return root;
        return max;
    }

    private static int height(final Node root)
    {
        if (root == null)
            return -1;
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }

    private static void preOrderTraversal(final Node root)
    {
        if (root == null)
            return;
        System.out.print(root.getData() + ",");
        preOrderTraversal(root.getLeft());
        preOrderTraversal(root.getRight());
    }

    private static void inOrderTraversal(final Node root)
    {
        if (root == null)
            return;
        inOrderTraversal(root.getLeft());
        System.out.print(root.getData() + ",");
        inOrderTraversal(root.getRight());
    }

    private static void postOrderTraversal(final Node root)
    {
        if (root == null)
            return;
        postOrderTraversal(root.getLeft());
        postOrderTraversal(root.getRight());
        System.out.print(root.getData() + ",");
    }

    /**
     * Also called as breadth first traversal.
     * 
     * @param root
     */
    private static void levelOrderTraversal(final Node root)
    {
        final Queue<Node> s = new LinkedList<>();
        s.add(root);
        while (!s.isEmpty())
        {
            Node elem = s.remove();
            System.out.print(elem.getData() + ", ");
            if (elem.getLeft() != null)
                s.add(elem.getLeft());
            if (elem.getRight() != null)
                s.add(elem.getRight());
        }
    }

    private static boolean isBST(final Node root, int min, int max)
    {
        if (root == null)
            return true;

        if (root.getData() <= min || root.getData() > max)
        {
            return false;
        }

        return isBST(root.getLeft(), min, root.getData()) && isBST(root.getRight(), root.getData(), max);
    }

    private static Node delete(Node root, int val)
    {
        if (root == null)
        {
            return root;
        }
        else if (val < root.getData())
        {
            root.setLeft(delete(root.getLeft(), val));
        }
        else if (val > root.getData())
        {
            root.setRight(delete(root.getRight(), val));
        }
        else if (val == root.getData())
        {
            // case:1 node has no children
            if (root.getLeft() == null && root.getRight() == null)
            {
                return null;
            }

            // case:2 node has one child
            if (root.getLeft() == null && root.getRight() != null)
            {
                return root.getRight();
            }
            if (root.getLeft() != null && root.getRight() == null)
            {
                return root.getLeft();
            }

            // case:3 node has two children
            if (root.getLeft() != null && root.getRight() != null)
            {
                Node minElemOnRight = findMin(root.getRight());
                root.setData(minElemOnRight.getData());
                root.setRight(delete(root.getRight(), minElemOnRight.getData()));
            }
        }
        return root;
    }

    private static Node inorderSuccessor(Node root, int val)
    {
        if (root == null)
            return null;
        Node ansestor = root;
        Node successor = null;
        while (ansestor.getData() != val)
        {
            if (val < ansestor.getData())
            {
                successor = ansestor;
                ansestor = ansestor.getLeft();
            }
            else if (val > ansestor.getData())
            {
                ansestor = ansestor.getRight();
            }
        }
        if (ansestor.getRight() != null)
            return findMin(ansestor.getRight());
        return successor;
    }
}
