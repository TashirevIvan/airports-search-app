package com.example.datastructures.trees;

import java.util.*;

public class Trie implements Tree {
    private final Node root;

    public Trie() {
        this.root = new Node(' ');
    }

    @Override
    public Tree insert(String key, String value) {
        Node currentNode = root;
        Map<Character, Node> children = root.getChildren();
        for (char c : key.toCharArray()) {
            char lowerCase = Character.toLowerCase(c);

            if (children.containsKey(lowerCase)) {
                currentNode = children.get(lowerCase);
            } else {
                currentNode = new Node(lowerCase);
                children.put(lowerCase, currentNode);
            }
            children = currentNode.getChildren();
        }
        currentNode.setEndOfWord(true);
        currentNode.setValue(value);
        return this;
    }

    @Override
    public Set<String> wordsWithPrefix(String prefix) {
        Set<String> wordSet = new TreeSet<>();
        Node prefixNode = search(prefix);
        if (prefixNode != null) {
            addWords(prefixNode, wordSet);
        }
        return wordSet;
    }

    private Node search(String str) {
        Node currentNode = null;
        Map<Character, Node> children = root.getChildren();
        for (char c : str.toCharArray()) {
            char lowerCase = Character.toLowerCase(c);
            if (!children.containsKey(lowerCase)) {
                return null;
            }
            currentNode = children.get(lowerCase);
            children = currentNode.getChildren();
        }
        return currentNode;
    }

    private void addWords(Node node, Set<String> wordList) {
        if (node.isEndOfWord()) {
            wordList.add(node.getValue());
        }
        node.getChildren().values().forEach(child -> addWords(child, wordList));
    }
}
