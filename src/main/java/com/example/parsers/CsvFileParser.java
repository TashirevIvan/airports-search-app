package com.example.parsers;

import com.example.data.DataRow;
import com.example.datastructures.trees.Tree;
import com.example.datastructures.trees.Trie;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CsvFileParser {

    private static final String FILE_PATH = "src/main/resources/files/airports.csv";

    public Tree generateTreeByFilter(String inputFilter) {
        Expression expression = parseInputFilter(inputFilter);

        Tree tree = new Trie();
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            lines.map(this::parseLineToDataRow)
                    .filter(e -> expression.getValue(e, Boolean.class))
                    .forEach(e -> tree.insert(e.getColumn(2).toString(), e.toString()));
        } catch (IOException e) {
            throw new RuntimeException("File reading Exception");
        }
        return tree;
    }

    //parse input filter to SpEL expression
    private Expression parseInputFilter(String inputFilter) {
        SpelExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(inputFilter);
    }

    private DataRow parseLineToDataRow(String line) {
        List<String> values = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\",|([^,]*)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String strValue = matcher.group(1);
            String numValue = matcher.group(2);
            if (strValue != null) {
                values.add(strValue);
            } else if (numValue != null && !numValue.isEmpty()) {
                values.add(numValue);
            }
        }
        if (values.size() != 14) {
            throw new RuntimeException("Parsing exception in line: " + line);
        }

        DataRow myDataRow = new DataRow(15);
        myDataRow.setColumn(1, Integer.valueOf(values.get(0)));
        myDataRow.setColumn(2, values.get(1));
        myDataRow.setColumn(3, values.get(2));
        myDataRow.setColumn(4, values.get(3));
        myDataRow.setColumn(5, values.get(4));
        myDataRow.setColumn(6, values.get(5));
        myDataRow.setColumn(7, Double.valueOf(values.get(6)));
        myDataRow.setColumn(8, Double.valueOf(values.get(7)));
        myDataRow.setColumn(9, Integer.valueOf(values.get(8)));
        myDataRow.setColumn(10, values.get(9));
        myDataRow.setColumn(11, values.get(10));
        myDataRow.setColumn(12, values.get(11));
        myDataRow.setColumn(13, values.get(12));
        myDataRow.setColumn(14, values.get(13).replace("\"", ""));
        return myDataRow;
    }
}
