# Lab09-BBTree

# TODO Append your README.md here. Do not create a separate file.
# Check the rubric on what kind of information should be here.

**NOTES**

- for `depthOfTree()` method: depth counts root node when not null as 0
- for `addAll()` helper method, used resource: https://www.geeksforgeeks.org/insertion-in-a-binary-tree-in-level-order/. 
  - From this I was able to understand how to implement a queue list to add nodes to a tree in level order (which means adding nodes by depth of tree). To keep track of which nodes to visit at the next level/depth, a queue is used to store the order of nodes visited at the current level from left to right. This means that on the next level, the first nodes visited are the children of the current level's first visited nodes (from leftmost to right). 

**RUNTIME AND SPACE COMPLEXITY ANALYSIS**

`public boolean addAll()`
- TIME: O(n^2)
  - loops through given array of size n and calls private helper `allAll()` method to traverse and insert each item into tree = O(n) * O(n)
- SPACE: O(1)
  - only creates nodes to store data in

`private addAll()`
- TIME: O(n)
  - traverses tree in level order to find first available position in tree to insert new data node at
  - each traversal visits 1 + 2 + 3 + . . . + n-3 + n-2 + n-1 nodes before inserting at available position 
- SPACE: O(1)
  - only uses linked list queue to store nodes

`public int depthOfTree()`
- TIME: O(log n)
  - calls private helper `depthOfTree()` method
- SPACE: O(1)

`private int depthOfTree()`
- TIME: O(log n)
  - finds depth of each subtree down to root to find greatest depth
- SPACE: O(1)

`public boolean isLeaf()`
- TIME: O(1)
- SPACE: O(1)

`public boolean isValidNumber()`
- TIME: O(log n)
  - calls `allValidNums()` to get list of possible binary sequences in tree; # of binary sequences depend on depth of tree * 2 = about log n where n = # of nodes
  - traverses through each binary sequence in list 
- SPACE: O(log n)
  - list of possible binary sequences in tree

`public List<String> allValidNums()`
- TIME: O(log n)
  - calls private helper `allValidNums()` method, which is O(log n)
- SPACE: O(1)

`private List<String> allValidNums()`
- TIME: O(log n)
  - traverses tree from root to each leaf; depends on depth and width of tree
  - the # of binary sequences depend on depth of tree * 2 = about log n where n = # of nodes
- SPACE: O(log n)
  - list of possible binary sequences in tree; each sequence length depends on depth of tree

`public int revealSecretNumber()`
- calls `allValidNums()` to get list of possible binary sequences in tree; # of binary sequences depend on depth of tree * 2 = about log n where n = # of nodes
- traverses through each binary sequence in list to add to total
- SPACE: O(log n)
    - list of possible binary sequences in tree

`public void printByLevel()`
- TIME: O(log n)
  - calls `depthOfTree()` = O(log n) and `printCurrentLeve()` = O(log n); O(log n) + O(log n)
- SPACE: O(1)

`public void printCurrentLevel()`
- TIME: O(log n)
  - traverses each level of tree to print nodes
- SPACE: O(1)
