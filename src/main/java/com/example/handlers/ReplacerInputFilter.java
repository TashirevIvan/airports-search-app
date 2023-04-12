package com.example.handlers;

public class ReplacerInputFilter {
    public String replaceNotEquals(String inputFilter) {
        return inputFilter.replaceAll("(?<=column\\[\\d]\\s{0,30})<>", "!=");
    }

    public String replaceEquals(String inputFilter) {
        return inputFilter.replaceAll("(?<=column\\[\\d]\\s{0,30})=", "==");
    }

    public String replaceAnd(String inputFilter) {
        return inputFilter.replaceAll("&(?=\\s*column\\[)", "&&");
    }
}
