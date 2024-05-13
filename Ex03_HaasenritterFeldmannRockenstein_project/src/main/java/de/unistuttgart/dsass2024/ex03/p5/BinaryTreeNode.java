package de.unistuttgart.dsass2024.ex03.p5;

public class BinaryTreeNode<T extends Comparable<T>> implements IBinaryTreeNode<T> {

    private T value;
    private IBinaryTreeNode<T> leftChild;
    private IBinaryTreeNode<T> rightChild;



    public BinaryTreeNode() {
        this.value = null;
        this.leftChild = null;
        this.rightChild = null;

    }

    public void setValue(T val){
        this.value = val;
    }

    public T getValue() {
        return this.value;
    }

    public void setLeftChild(IBinaryTreeNode<T> left){
        this.leftChild = left;

    }

    public IBinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setRightChild(IBinaryTreeNode<T> right) {
        this.rightChild = right;
    }
}