package com.example;

import com.example.datastructures.trees.Tree;
import com.example.handlers.HandlerInputFilter;
import com.example.parsers.CsvFileParser;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tree tree;
        String inputFilter;
        Scanner scanner;

        while (true) {
            try {
                printFormatFilter();
                System.out.print("Введите фильтр: ");
                scanner = new Scanner(System.in);
                inputFilter = scanner.nextLine();

                HandlerInputFilter handlerInputFilter = new HandlerInputFilter(inputFilter);

                handlerInputFilter.replaceNotEquals();
                handlerInputFilter.replaceEquals();
                handlerInputFilter.replaceAnd();
                handlerInputFilter.replaceQuotes();
                handlerInputFilter.unwrapValueFromQuotes();

                inputFilter = handlerInputFilter.getStrFilter();

                CsvFileParser csvFileParser = new CsvFileParser();
                tree = csvFileParser.generateTreeByFilter(inputFilter);
                break;
            } catch (RuntimeException e) {
                System.out.println("Ошибка при вводе фильтра. Попробуйте ещё раз");
            }
        }

        while (true) {
            System.out.print("Введите название аэропорта: ");
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

    public static void printFormatFilter() {
        System.out.println("Инструкция для фильтра: \n" +
                "Фильтр для числовых колонок 1,7,8,9:\n" +
                "- Доступные операции сравнения: равно (=), не равно (<>), больше (>), меньше (<)\n" +
                "\n" +
                "Фильтр для строчных колонок 2,3,4,5,6,10,11,12,13,14:\n" +
                "- Доступные операции сравнения: равно (=), не равно (<>)\n" +
                "- Формат ввода значений 'в кавычах': column[2]='Madang Airport'\n" +
                "\n" +
                "Фильтры могут соединяться отношением И (&) и ИЛИ (||)\n" +
                "\n" +
                "Пример корректного ввода:  column[1]<10 & column[1]>1 & column[2]<> 'Madang Airport'");
    }
}
