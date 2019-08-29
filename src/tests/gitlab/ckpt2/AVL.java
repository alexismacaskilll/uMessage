package tests.gitlab.ckpt2;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;
import cse332.interfaces.misc.Dictionary;
import datastructures.dictionaries.AVLTree;

public class AVL {

    public static void main(String[] args) {
        checkStructure();

    }
    
    protected static AVLTree<String, Integer> init() {
        AVLTree<String, Integer> tree = new AVLTree<>();

        return tree;
    }
    
    private static <E extends Comparable<E>> void incCount(Dictionary<E, Integer> tree, E key) {
        Integer value = tree.find(key);
        if (value == null) {
            tree.insert(key, 1);
        } else {
            tree.insert(key, value + 1);
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static int checkStructure() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        incCount(tree, 6);
        incCount(tree, 5);
        incCount(tree, 4);
        incCount(tree, 3);
        incCount(tree, 2);
        incCount(tree, 1);
        incCount(tree, 10);
        incCount(tree, 9);
        incCount(tree, 8);
        incCount(tree, 7);
//      {10, 14, 10, 31, 10, 13, 10, 10, 12, 10, 13, 10, 10, 11, 10, 14, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
//      {10, 14, 31, 13, 12, 11, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
        return 0;

    }

    @SuppressWarnings("rawtypes")
    public static String nestd(BSTNode root) {
        if(root == null)
            return ".";
        return " [" + root.key + nestd(root.children[0]) + nestd(root.children[1]) + "]";
    }
    @SuppressWarnings("rawtypes")
    public static String nestc(BSTNode root) {
        if(root == null)
            return ".";
        return " [" + root.value + nestc(root.children[0]) + nestc(root.children[1]) + "]";
    }
    
    

}
