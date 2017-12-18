## The main purpose of using binary search tree is that the cost of insertion, search and delete operations are O(logn) which is better than Arrays and linked lists.

#### Complexities:
|   | Array (unsorted)  | LinkedList  | Array (sorted)  | BST  |
|---|---|---|---|---|
| search  | O(n)  | O(n)  | O(logn)  | O(logn)  |
| insert  | O(1) - insert at end  | O(1)  | O(n)  | O(logn)  |
| delete  | O(n)  | O(n)  | O(n)  | O(logn)  |

**Note:** O(logn) applies only when the tree is balanced. In the worst case if the tree is not balanced, like if there is no right/left subtree at all, then it is equivalent to a linked list
and the complexities will be same as linked list. Hence always try to keep a BST balanced.

#### Definition: 
**It is a tree in which for each node, the value of all nodes in the left subtree is lesser or equal to that node and the value of all nodes in the right subtree is greater than that node.**

#### More definitions:
**Balanced BST:** A BST is said to be balanced if the difference b/w the heights of left subtree and right subtree is not greater than 1.    
**Height of a tree:** Number of edges in the longest path from the root to the leaf.    
**Depth of a node:** Don't confuse depth with height. Depth is for a specific node and height is for the entire tree. So depth of a node is the number of edges in the path from root to that node.

#### Tree traversals:
- **Breadth first:** Here nodes are visited level by level.
- **Depth first:** Here we can do this in 3 ways, inorder, preorder, postorder traversals.
  - Preorder is root, left and right.
  - Inorder is left, root and right.
  - Postorder is left, right and root.
