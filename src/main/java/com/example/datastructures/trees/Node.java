package com.example.datastructures.trees;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Node {
    private final char character;
    private boolean isEndOfWord;
    private String value;
    private Map<Character, Node> children = new HashMap<>();
}
