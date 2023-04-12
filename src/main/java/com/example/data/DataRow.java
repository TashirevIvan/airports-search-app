package com.example.data;

public class DataRow {

    public Object[] column;

    public DataRow(int numColumns) {
        this.column = new Object[numColumns];
    }

    public Object getColumn(int index) {
        return column[index];
    }

    public void setColumn(int index, Object value) {
        column[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(column[2]).append(" [");

        for (int i = 1; i < column.length - 1; i++) {
            stringBuilder.append(column[i].toString()).append(", ");
        }
        stringBuilder.append(column[column.length - 1]).append("]");
        return stringBuilder.toString();
    }
}
