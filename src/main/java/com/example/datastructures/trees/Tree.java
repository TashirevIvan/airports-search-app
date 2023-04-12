package com.example.datastructures.trees;

import java.util.Set;

public interface Tree {
    Tree insert(String key, String value);

    Set<String> wordsWithPrefix(String prefix);
}
