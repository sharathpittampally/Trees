package com.sharath.trees.binarytree;

import com.sharath.trees.bst.model.Node;

public class LargestBSTInBT
{

    public static void main(String[] args)
    {
        final Node root = Node.create(25);
        final Node n1 = Node.create(18);
        final Node n2 = Node.create(50);
        final Node n3 = Node.create(19);
        final Node n4 = Node.create(20);
        final Node n5 = Node.create(35);
        final Node n6 = Node.create(60);
        final Node n7 = Node.create(15);
        final Node n8 = Node.create(18);
        final Node n9 = Node.create(25);
        final Node n10 = Node.create(20);
        final Node n11 = Node.create(40);
        final Node n12 = Node.create(55);
        final Node n13 = Node.create(70);
        final Node n14 = Node.create(25);

        // left tree
        n3.setRight(n7);
        n4.setLeft(n8);
        n4.setRight(n9);
        n1.setLeft(n3);
        n1.setRight(n4);

        // right tree
        n6.setLeft(n12);
        n6.setRight(n13);
        n10.setRight(n14);
        n5.setLeft(n10);
        n5.setRight(n11);
        n2.setLeft(n5);
        n2.setRight(n6);

        /// connecting to root
        root.setLeft(n1);
        root.setRight(n2);
        System.out.println("Largest BST size is: " + largestBST(root).size);
    }

    private static Info largestBST(final Node root)
    {
        if (root == null)
        {
            return null;
        }

        Info leftInfo = largestBST(root.getLeft());
        Info rightInfo = largestBST(root.getRight());

        if (leftInfo == null && rightInfo == null)
        {
            return new Info(true, 1, root.getData(), root.getData());
        }
        if (leftInfo != null && rightInfo == null)
        {
            boolean isBST = root.getData() > leftInfo.max ? true : false;
            if (isBST)
                return new Info(isBST, leftInfo.size + 1, leftInfo.min, root.getData());
            return new Info(isBST, leftInfo.size, 0, 0);
        }
        if (leftInfo == null && rightInfo != null)
        {
            boolean isBST = root.getData() < rightInfo.min ? true : false;
            if (isBST)
                return new Info(isBST, rightInfo.size + 1, root.getData(), rightInfo.max);
            return new Info(isBST, rightInfo.size, 0, 0);
        }

        // This below code until the end of function is when both leftInfo and
        // rightInfo are not null. There will not be a case where both leftInfo
        // and rightInfo are false(for isBST. Since leaf nodes always return
        // isBST as true).
        if (!leftInfo.isBST)
        {
            return new Info(false, rightInfo.size, 0, 0);
        }
        if (!rightInfo.isBST)
        {
            return new Info(false, leftInfo.size, 0, 0);
        }
        boolean isBST = leftInfo.max < root.getData() && root.getData() < rightInfo.min ? true : false;
        if (isBST)
            return new Info(isBST, leftInfo.size + rightInfo.size + 1, leftInfo.min, rightInfo.max);
        return new Info(isBST, Math.max(leftInfo.size, rightInfo.size), 0, 0);
    }

    private static class Info
    {
        boolean isBST;
        int size;
        int min;
        int max;

        Info(boolean isBST, int size, int min, int max)
        {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
}
