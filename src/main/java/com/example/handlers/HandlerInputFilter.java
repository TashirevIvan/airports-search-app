package com.example.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HandlerInputFilter {

    private String strFilter;

    public void replaceNotEquals() {
        strFilter = strFilter.replaceAll("(?<=column\\[\\d]\\s{0,30})<>", "!=");
    }

    public void replaceEquals() {
        strFilter = strFilter.replaceAll("(?<=column\\[\\d]\\s{0,30})=", "==");
    }

    public void replaceAnd() {
        strFilter = strFilter.replaceAll("&(?=\\s*\\(*\\s*column\\[)", "&&");
    }

    public void replaceQuotes() {
        strFilter = strFilter.replaceAll("’", "'");
    }

    public void unwrapValueFromQuotes() { // ForNumerationColumn
        strFilter = strFilter.replaceAll("column\\[([1789])]\\s*(!=|==|<|>)\\s*['\"](\\w+)['\"]\\s*", "column[$1]$2$3");
    }
}
