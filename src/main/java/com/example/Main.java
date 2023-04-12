package com.example;

import com.example.datastructures.trees.Tree;
import com.example.handlers.ReplacerInputFilter;
import com.example.parsers.CsvFileParser;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Give me a filter: ");
        Scanner scanner = new Scanner(System.in);
        String inputFilter = scanner.nextLine();

        ReplacerInputFilter replacerInputFilter = new ReplacerInputFilter();
        inputFilter = replacerInputFilter.replaceNotEquals(inputFilter);
        inputFilter = replacerInputFilter.replaceEquals(inputFilter);
        inputFilter = replacerInputFilter.replaceAnd(inputFilter);

        CsvFileParser csvFileParser = new CsvFileParser();
        Tree tree = csvFileParser.generateTreeByFilter(inputFilter);

        while (true) {
            System.out.print("Give me a query: ");
            String query = scanner.nextLine();

            if (query.equals("!quit")) {
                break;
            }

            long start = System.currentTimeMillis();
            Set<String> stringSet = tree.wordsWithPrefix(query);
            long elapsed = System.currentTimeMillis() - start;

            printAllStrings(stringSet);
            System.out.println("Количество найденых строк: " + stringSet.size());
            System.out.println("Время, затраченное на поиск мс: " + elapsed);
        }
    }

    private static void printAllStrings(Set<String> strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }
}