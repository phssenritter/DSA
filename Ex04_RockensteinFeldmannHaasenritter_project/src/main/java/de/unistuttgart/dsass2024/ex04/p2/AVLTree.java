package de.unistuttgart.dsass2024.ex04.p2;



public class AVLTree<K extends Comparable<K>> implements IAVLTree<K> {
    private AVLNode<K> root = null;

    @Override
    public AVLNode<K> getRootNode() {
        return root;
    }

    @Override
    public void setRootNode(AVLNode<K> node) {
        root = node;
    }

    @Override
    public void insert(K k) {
        if (root == null)
            root = new AVLNode<>(k);
        else
            root = insertRecursive(root, k);
    }

    private AVLNode<K> insertRecursive(AVLNode<K> n, K k) {
        int cmp = n.getKey().compareTo(k);
        if (cmp == 0) {
            // Schlüssel bereits vorhanden. Es ist nichts zu tun.
        } else if (cmp < 0) {
            // Rechts einfügen
            if (n.getRight() != null) {
                // Im rechten Teilbaum einfügen (rekursiv)
                n.setRight(insertRecursive(n.getRight(), k));
            } else {
                // Neuen Blattknoten rechts erzeugen
                n.setRight(new AVLNode<>(k));
            }
            // Höhe des rechten Teilbaums könnte sich verändert haben
            n.updateHeight();
            // Ggf. rebalancieren
            n = rebalance(n);
        } else {
            // Links einfügen (symmetrisch)
            if (n.getLeft() != null) {
                // Im linken Teilbaum einfügen (rekursiv)
                n.setLeft(insertRecursive(n.getLeft(), k));
            } else {
                // Neuen Blattknoten links erzeugen
                n.setLeft(new AVLNode<>(k));
            }
            // Höhe des linken Teilbaums könnte sich verändert haben
            n.updateHeight();
            // Ggf. rebalancieren
            n = rebalance(n);
        }

        return n;
    }



    private AVLNode<K> rotateLeft(AVLNode<K> n) {
        AVLNode<K> tmp = n.getRight();
        n.setRight(tmp.getLeft());
        n.updateHeight();
        tmp.setLeft(n);
        tmp.updateHeight();
        return tmp;
    }

    private AVLNode<K> rotateRight(AVLNode<K> n) {
        AVLNode<K> tmp = n.getLeft();
        n.setLeft(tmp.getRight());
        n.updateHeight();
        tmp.setRight(n);
        tmp.updateHeight();
        return tmp;
    }

    public AVLNode<K> rebalance(AVLNode<K> n){
        int balance = n.getBalance();
        if(balance > 1){
            if(n.getLeft().getBalance() >= 0) {
                return rotateRight(n);
            } else {
                n.setLeft(rotateLeft(n.getLeft()));
                return rotateRight(n);
            }
        } else if (balance < -1){
            if(n.getRight().getBalance() <= 0 ){
                return rotateLeft(n);
            } else {
                n.setRight(rotateRight(n.getBalance()));
                return rotateLeft(n);
            }
        }
        return n;
    }

    private AVLNode<K> findSmallest(AVLNode<K> n) {
        while (n.getLeft() != null) {
            n = n.getLeft();
        }
        return n;
    }

    private AVLNode<K> removeRecursive(AVLNode<K> n, K k) {
        int cmp = n.getKey().compareTo(k);
        if (cmp == 0) {
            if (n.getLeft() == null && n.getRight() == null) {
                    return null;
            } else if (n.getLeft() == null) {
                return n.getRight();
            } else if (n.getRight() == null) {
                return n.getLeft();
            } else {
                AVLNode<K> smallest = findSmallest(n.getRight());
                n.setKey(smallest.getKey());
                n.setRight(removeRecursive(n.getRight(), smallest.getKey()));
            }
        } else if (cmp < 0) {
            n.setRight(removeRecursive(n.getRight(), k));
        } else {
            n.setLeft(removeRecursive(n.getLeft(), k));
        }
        n.updateHeight();
        return rebalance(n);
    }

    public void remove(K k){
        if (root != null) {
            root.removeRecursive(root,k)
        }
    }
}