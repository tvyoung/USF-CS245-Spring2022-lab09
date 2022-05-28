/**
 * Lab09: Binary Binary Tree
 *
 * @author Vicki Young
 * @version 2022.04.19
 * CS 245-03
 */
package Lab09.BBTree;

import java.lang.*;
import java.util.*;

public class BBTree {
    protected TreeNode root;
    protected int size;

    /**
     * Nested inner TreeNode that is used to construct our BBTree
     */
    protected class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int elementToAdd) {
            data = elementToAdd;
            left = right = null;
        }
    }

    /**
     * Constructor that initializes the Binary Binary Tree that takes no parameters.
     */
    public BBTree() {
        root = null;
        size = 0;
    }

    /**
     * Adds all elements within the given int array into the Binary Binary Tree.
     * @param elementsToAdd array of 0s and 1s to be added into the tree
     * @return boolean true if the construction of the tree was successful, false otherwise.
     * @throws NullPointerException when the int array is an empty array
     */
    public boolean addAll(int[] elementsToAdd) throws NullPointerException {
        //throw exception if given array is empty
        if (elementsToAdd == null) {
            throw new NullPointerException("Given array is empty.");
        }
        //set size variable based on number of elements in given array
        size = elementsToAdd.length;
        //loop through each element in given array and adds it in level order to tree
        for (int i = 0; i < size; i++) {
            addAll(elementsToAdd, root, i);
        }
        return true;
    }

    /**
     * Helper method to add all elements within the given int array into the Binary Binary Tree by utilizing the add method above.
     * Resource used to implement method: https://www.geeksforgeeks.org/insertion-in-a-binary-tree-in-level-order/
     * @param elementsToAdd array of 0s and 1s to be added into the tree
     * @param current current TreeNode in the tree
     * @param index index of current item in elementsToAdd
     */
    private void addAll(int[] elementsToAdd, TreeNode current, int index) {
        //create new node based on current item in given array
        TreeNode node = new TreeNode(elementsToAdd[index]);
        //if the root is null, set root to the newly created node
        if (current == null) {
            root = node;
            return;
        }
        //create queue to keep track of level order for nodes in tree
        Queue<TreeNode> q = new LinkedList<>();
        q.add(current);
        //use queue to find where to add new node of data in level order to tree
        while (!q.isEmpty()) {
            //while the queue is not empty, take the first node in queue as the current node
            current = q.remove();
            //if current node left child is null, add data node there
            if (current.left == null) {
                current.left = node;
                break;
            //else add left child to queue
            } else {
                q.add(current.left);
            }
            //if current node right child is null, add data node there
            if (current.right == null) {
                current.right = node;
                break;
            //else add right child to queue
            } else {
                q.add(current.right);
            }
        }
    }

    /**
     * This method returns lowest depth of the tree.
     * @return int of the lowest depth of the tree
     */
    public int depthOfTree() {
        int depth = depthOfTree(root);
        //if depth is not 0, subtract 1 (root node counts as 0) and return
        if (depth != 0) {
            return depth - 1;
        }
        return depth;
    }

    /**
     * Helper method returns lowest depth of the tree.
     * @return int of the lowest depth of the tree
     */
    private int depthOfTree(TreeNode root) {
        //return 0 at root node
        if (root == null) {
            return 0;
        }
        //return 1 for current node + the greater depth between left and right nodes
        return 1 + Math.max(depthOfTree(root.left), depthOfTree(root.right));
    }

    /**
     * This method determines if a given TreeNode is a leaf node.
     * @param node to evaluate
     * @return boolean true if node is a leaf node, false otherwise
     */
    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * This method determines if a given number is a valid number within the Binary Binary Tree.
     * @param integer of type String which represents a binary sequence
     * @return boolean true if valid single digit int exists, false otherwise
     */
    public boolean isValidNumber(String integer) {
        //get list of all valid binary number sequences from tree
        List<String> binaryNumbers = allValidNums();
        //loop through list, check if the String integer matches a binary sequence in list
        for (String sequence : binaryNumbers) {
            if (sequence.equals(integer)) {
                return true;
            }
        }
        //no match found, return false
        return false;
    }

    /**
     * This public method returns a list of all strings representing valid binary numbers from the tree.
     * @return boolean true if valid single digit int exists, false otherwise
     */
    public List<String> allValidNums() {
        //call helper function with an initial empty sequence and list to store and return binary sequences
        return allValidNums(root, "", new LinkedList<>());
    }

    /**
     * Helper method returns a list of all strings representing valid binary numbers from the tree.
     * @param root of the tree
     * @param sequence current String binary sequence in tree
     * @param list the list of possible binary sequences in the tree (each sequence is from root --> leaf)
     * @return List<String> of all valid binary number sequences in tree
     */
    private List<String> allValidNums(TreeNode root, String sequence, List<String> list) {
        //add current node data to current sequence
        sequence += root.data;
        //if current node is not a leaf, traverse its left and right nodes
        if (!isLeaf(root)) {
            allValidNums(root.left, sequence, list);
            allValidNums(root.right, sequence, list);
        //else current node is a leaf, so add the total sequence to list
        } else {
            list.add(sequence);
        }
        //return list
        return list;
    }

    /**
     * This private method returns the full secret integer that was encoded in the int array.
     * @return int secret integer in the tree
     */
    public int revealSecretNumber() {
        int secretNumber = 0;
        //get list of all valid binary number sequences from tree
        List<String> binaryNumbers = allValidNums();
        //convert each string binary sequence to its number value and adds to total
        for (String sequence : binaryNumbers) {
            secretNumber += Integer.parseInt(sequence, 2);
        }
        return secretNumber;
    }

    /**
     * This method prints a readable binary binary tree by level; calls printCurrentLevel().
     * Warning: will not work until you implement depthOfTree() correctly.
     */
    public void printByLevel() {
        int h = depthOfTree(root);
        int i;
        for (i = 1; i <= h; i++) {
            printCurrentLevel(root, i);
            System.out.println();
        }
    }

    /**
     * This method is called by printByLevel() and prints each level in the binary tree.
     * @param root that is the root of the Binary Binary Tree
     * @param level of the tree current at.
     */
    public void printCurrentLevel(TreeNode root, int level) {
        if (root == null)
            return;
        if (level == 1) System.out.print(root.data + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }

    /** Main method for testing your TreeNode and BBTree class */
    public static void main(String[] args) {
        BBTree bt = new BBTree();
        int[] arr = {1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1};
        System.out.println("addAll successful: " + bt.addAll(arr));
        bt.printByLevel();
        System.out.println("----------------------------");
        System.out.println("Actual Tree Node Count: " + bt.size);
        System.out.println("Expected Tree Node Count: " + 13);
        System.out.println("----------------------------");
        System.out.println("Actual Tree Depth Count: " + bt.depthOfTree());
        System.out.println("Expected Tree Depth Count: " + 3);
        System.out.println("----------------------------");
        System.out.println("Actual List<String>: " + bt.allValidNums());
        System.out.println("Expected List<String>: [1110, 1111, 1100, 1101, 1011, 1011, 101]");
        System.out.println("----------------------------");
        System.out.println("Actual Secret Number: " + bt.revealSecretNumber());
        System.out.println("Expected Secret Number: " + 81);
        System.out.println("----------------------------");
    }
}
