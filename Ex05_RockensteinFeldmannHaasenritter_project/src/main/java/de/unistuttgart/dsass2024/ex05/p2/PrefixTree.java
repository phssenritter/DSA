package de.unistuttgart.dsass2024.ex05.p2;

public class PrefixTree implements IPrefixTree {

    private PrefixTreeNode root;
    private int size;

    public PrefixTree() {
        this.root = new PrefixTreeNode();
        this.size = 0;
    }

    @Override
    public void insert(String word) {
        if (word == null || word.isEmpty()) {

        }

        if ( size == 0) {
            root.setPrefix(word);
        }

        PrefixTreeNode currentNode = root;
        for (char character : word.toCharArray()) {
            IPrefixTreeNode childNode = currentNode.getChild(character);

            if(childNode == null) {
                childNode = new PrefixTreeNode();
                currentNode.setChild(character, childNode);
            }

        }
        size ++;
    }

    @Override
    public int size() {
        return this.size;

    }

}