package com.sharath.trees.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.sharath.trees.bst.model.Node;

public class BinaryTree
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        final Node root = Node.create(10);
        final Node n1 = Node.create(12);
        final Node n2 = Node.create(13);
        final Node n3 = Node.create(15);
        final Node n4 = Node.create(14);
        final Node n5 = Node.create(16);

        root.setLeft(n1);
        n3.setRight(n5);
        n2.setLeft(n3);
        n2.setRight(n4);
        root.setRight(n2);

        final Node root2 = Node.create(10);
        final Node n11 = Node.create(12);
        final Node n22 = Node.create(13);
        final Node n33 = Node.create(15);
        final Node n44 = Node.create(14);
        final Node n55 = Node.create(16);

        root2.setLeft(n11);
        n33.setRight(n55);
        n22.setLeft(n33);
        n22.setRight(n44);
        root2.setRight(n22);

        System.out.println("Are same?: " + areSame(root, root2));
        System.out.println("Size of tree: " + size(root));

        final List<Integer> result = new ArrayList<>();
        rootToLeafSum(root, 37, result);
        System.out.println("Root to leaf sum of 37: " + result);
        System.out.println();
        System.out.println("Level by level printing: ");
        System.out.println();
        levelByLevelPrinting(root);
        System.out.println("reverse Level by level printing: ");
        System.out.println();
        reverseLevelByLevel(root);

        final Node root3 = Node.create(10);
        final Node m1 = Node.create(-10);
        final Node m2 = Node.create(30);
        final Node m3 = Node.create(8);
        final Node m4 = Node.create(25);
        final Node m5 = Node.create(60);
        final Node m6 = Node.create(6);
        final Node m7 = Node.create(9);
        final Node m8 = Node.create(28);
        final Node m9 = Node.create(78);
        m3.setLeft(m6);
        m3.setRight(m7);
        m1.setRight(m3);
        root3.setLeft(m1);
        m4.setRight(m8);
        m5.setRight(m9);
        m2.setLeft(m4);
        m2.setRight(m5);
        root3.setRight(m2);
        System.out.println();
        System.out.println("loweset common ansestor: " + lowestCommonAncestorBST(root3, 28, 78).getData());
    }

    private static boolean areSame(final Node r1, final Node r2)
    {
        if (r1 == null && r2 == null)
            return true;
        if (r1 == null || r2 == null)
            return false;
        if (r1.getData() != r2.getData())
            return false;
        return areSame(r1.getLeft(), r2.getLeft()) && areSame(r1.getRight(), r2.getRight());
    }

    private static int size(Node root)
    {
        if (root == null)
            return 0;
        return 1 + size(root.getLeft()) + size(root.getRight());
    }

    private static boolean rootToLeafSum(Node root, int sum, List<Integer> result)
    {
        if (root == null)
            return false;
        if (root.getData() == sum && isLeaf(root))
        {
            result.add(root.getData());
            return true;
        }
        boolean leftStatus = rootToLeafSum(root.getLeft(), sum - root.getData(), result);
        boolean rightStatus = rootToLeafSum(root.getRight(), sum - root.getData(), result);

        if (leftStatus || rightStatus)
        {
            result.add(root.getData());
            return true;
        }
        return false;
    }

    private static boolean isLeaf(Node root)
    {
        return root.getLeft() == null && root.getRight() == null;
    }

    private static void levelByLevelPrinting(final Node root)
    {
        if (root == null)
            return;
        final Queue<Node> q = new LinkedList<>();
        q.add(root);
        final Node delimiter = null;
        q.add(delimiter);
        while (!q.isEmpty())
        {
            Node top = q.poll();
            if (top == null)
            {
                System.out.println();
            }
            else
            {
                System.out.print(top.getData());
                q.add(top.getLeft());
                q.add(top.getRight());
                q.add(delimiter);
            }
        }
    }

    private static void reverseLevelByLevel(Node root)
    {
        final Stack<Node> s = new Stack<>();
        final Queue<Node> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty())
        {
            Node elem = q.poll();
            if (elem != null)
            {
                s.push(elem);
                q.add(elem.getRight());
                q.add(elem.getLeft());
            }
        }

        while (!s.isEmpty())
        {
            System.out.print(s.pop().getData() + ", ");
        }
    }

    private static Node lowestCommonAncestorBST(final Node root, int a, int b)
    {
        if (root == null)
            return root;
        if (root.getData() == a)
            return root;
        if (root.getData() == b)
            return root;
        if ((a < root.getData() && root.getData() < b) || (a > root.getData() && root.getData() > b))
        {
            return root;
        }
        if ((root.getData() < a) && (root.getData() < b))
            return lowestCommonAncestorBST(root.getRight(), a, b);
        return lowestCommonAncestorBST(root.getLeft(), a, b);
    }

    private static Node lowestCommonAncestorBT(Node root, int a, int b)
    {
        if (root == null)
            return null;
        if (root.getData() == a || root.getData() == b)
            return root;
        Node left = lowestCommonAncestorBT(root.getLeft(), a, b);
        Node right = lowestCommonAncestorBT(root.getRight(), a, b);
        if (left != null && right != null)
            return root;
        if (left == null && right == null)
            return null;
        if (left != null)
            return left;
        return right;
    }
}
