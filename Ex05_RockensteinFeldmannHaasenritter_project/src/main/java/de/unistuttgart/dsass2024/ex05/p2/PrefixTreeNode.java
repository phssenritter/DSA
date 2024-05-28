package de.unistuttgart.dsass2024.ex05.p2;

import java.util.HashMap;
import java.util.Set;

public class PrefixTreeNode implements IPrefixTreeNode{

    public String prefix;
    public Map<Character, IPrefixTreeNode> children;
    public char label;

    public PrefixTreeNode() {
        this.prefix = prefix;
        this.children = new HashMap<>();
        this.label = label;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;

    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public Set<Character> getLabels() {
        return this.children.keySet();
    }

    @Override
    public void setChild(char label, IPrefixTreeNode node) {
        this.children.put(label, node);

    }

    @Override
    public IPrefixTreeNode getChild(char label) {
        return this.children.get(label);
    }

    @Override
    public void removeChildren() {
        this.children.clear();
    }

}