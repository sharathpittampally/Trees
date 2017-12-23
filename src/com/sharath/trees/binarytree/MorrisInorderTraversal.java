package com.sharath.trees.binarytree;

import com.sharath.trees.bst.model.Node;

/**
 * This is a very interesting traversal. It will traverse the tree in O(n) using
 * only a constant space (O(1)). So no usage of stacks or any data structure and
 * no usage of recursion.
 * 
 * @author sharath
 *
 */
public class MorrisInorderTraversal
{
    public static void main(String[] args)
    {
        final Node root = Node.create(10);
        final Node n1 = Node.create(5);
        final Node n2 = Node.create(15);
        final Node n3 = Node.create(2);
        final Node n4 = Node.create(7);
        final Node n5 = Node.create(30);

        // left tree
        n1.setLeft(n3);
        n1.setRight(n4);

        // right tree
        n2.setRight(n5);

        /// connecting to root
        root.setLeft(n1);
        root.setRight(n2);
        morrisInorderTraversal(root);
    }

    private static void morrisInorderTraversal(final Node root)
    {
        if (root == null)
            return;
        Node curr = root;
        while (curr != null)
        {
            if (curr.getLeft() == null)
            {
                System.out.print(curr.getData() + ", ");
                curr = curr.getRight();
            }
            else
            {
                Node pred = findInorderPredecessor(curr);
                if (pred.getRight() == null)
                {
                    pred.setRight(curr);
                    curr = curr.getLeft();
                }
                else
                {
                    pred.setRight(null);
                    System.out.print(curr.getData() + ", ");
                    curr = curr.getRight();
                }
            }
        }
    }

    // Right most node in the left sub-tree
    private static Node findInorderPredecessor(Node root)
    {
        Node curr = root.getLeft();
        while (curr.getRight() != null && curr.getRight() != root)
        {
            curr = curr.getRight();
        }
        return curr;
    }
}
