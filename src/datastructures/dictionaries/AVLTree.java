package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;

/**
 * TODO: Replace this comment with your own as appropriate.
 *
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and callst o superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 *
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 *    instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 *    children array or left and right fields in AVLNode.  This will 
 *    instead mask the super-class fields (i.e., the resulting node 
 *    would actually have multiple copies of the node fields, with 
 *    code accessing one pair or the other depending on the type of 
 *    the references used to access the instance).  Such masking will 
 *    lead to highly perplexing and erroneous behavior. Instead, 
 *    continue using the existing BSTNode children array.
 * 4. If this class has redundant methods, your score will be heavily
 *    penalized.
 * 5. Cast children array to AVLNode whenever necessary in your
 *    AVLTree. This will result a lot of casts, so we recommend you make
 *    private methods that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 */

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V>  {

    
    private class AVLNode extends BSTNode {
        public int height; 

        public AVLNode(K key, V value, int height) {
            super(key, value);
            this.height = height;  
        } 
    }
    
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        AVLNode result = find(key, castToAVL(super.root));
        V oldValue = result.value;
        result.value = value;
        if (oldValue == null) { 
            root = castToAVL(fixBalance(root, key));
            super.size++;
        }
        return oldValue;
    }
    
    private void updateH(AVLNode node) {
        node.height =  Math.max(getHeight(node.children[1]), 
                getHeight((node.children[0]))) + 1;
    }
   
    private BSTNode fixBalance(BSTNode node, K key) {
        if (getHeight(node) > 0) {
            if (node.key.compareTo(key) < 0) {
                node.children[1] = fixBalance(node.children[1], key);
            } else {
                node.children[0] = fixBalance(node.children[0], key);
            }
            updateH(castToAVL(node));
            if (getHeight(node.children[1]) - getHeight(node.children[0]) > 1) {
                if (node.children[1].key.compareTo(key) < 0) {
                  //case 4
                    node = singleRotate(castToAVL(node), 1, 0);
                } else {
                  //case 3
                    node.children[1] = singleRotate(castToAVL(node.children[1]), 0, 1);
                    node = singleRotate(castToAVL(node), 1, 0);
                }
            } else if (getHeight(node.children[0]) - getHeight(node.children[1]) > 1) {
                if (node.children[0].key.compareTo(key) < 0) {
                    //case 2
                    node.children[0] = singleRotate(castToAVL(node.children[0]), 1, 0);
                    node = singleRotate(castToAVL(node), 0, 1);
                } else {
                    //case 1
                    node = singleRotate(castToAVL(node), 0, 1);
                }
            }
        }
        return node;
    }
    private BSTNode singleRotate(AVLNode node, int a, int b) {
        AVLNode temp = castToAVL(node.children[a]);
        node.children[a] = temp.children[b];
        temp.children[b] = node;
        updateH(node);
        updateH(temp);
        node = temp;
        return node;
    }
    
    protected AVLNode find(K key, AVLNode node) {
        AVLNode temp;
        if (node == null) {
            root = new AVLNode(key, null, 0);
            return castToAVL(super.root);
        }
        if (node.key.equals(key)) {
            return node;
        } else if (node.key.compareTo(key) < 0) {
            temp = findHelp(1, node, key);
        } else {
            temp = findHelp(0, node, key);
        }
        updateH(node);
        return temp;
    }
    
    private AVLNode findHelp(int a, AVLNode node, K key) {
        if (node.children[a] == null) {
            node.children[a] = new AVLNode(key, null, 0);
            return castToAVL(node.children[a]);
        } 
        return find(key, castToAVL(node.children[a]));
    }

    
    private int getHeight(BSTNode node) {
        AVLNode avl = castToAVL(node);
        if (avl == null) {
            return -1;
        }
        return avl.height;
    }
    
    private AVLNode castToAVL(BSTNode node) {
        return (AVLNode) node; 
    }
    

    
}

