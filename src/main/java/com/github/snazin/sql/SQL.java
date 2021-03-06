package com.github.snazin.sql;


import org.intellij.lang.annotations.Language;

import static org.apache.commons.lang3.StringUtils.join;


public class SQL {

    @Language("SQL")
    private String query = "";


    public SQL() {
    }

    public SQL(@Language("SQL") String query) {
        this.query = query;
    }


    public static SQL create() {
        return new SQL();
    }


    public SQL append(@Language("SQL") String statement) {
        query = query + statement;
        return this;
    }

    public SQL appendLine(@Language("SQL") String statement) {
        return append(" \n" + statement);
    }


    public SQL select(String... fields) {
        return append("select " + join(fields, ", "));
    }

    public SQL selectAll() {
        return append("select *");
    }

    public SQL from(String... tables) {
        return appendLine("from " + join(tables, ", "));
    }

    public SQL where(String... conditions) {
        return appendLine("where " + join(conditions, " \nand "));
    }

    public SQL where() {
        return where("");
    }

    public SQL and(String condition) {
        return appendLine("and " + condition);
    }

    public SQL is(Number value) {
        return append(" = " + value);
    }

    public SQL is(String value) {
        return append(" = '" + value + "'");
    }

    public SQL like(String value) {
        return append(" like '%" + value + "%'");
    }

    public SQL lower(String value) {
        return append("lower(" + value + ")");
    }

    public SQL likeIgnoreCase(String field, String value) {
        return lower(field).like(value.toLowerCase());
    }

    @Override
    public String toString() {
        return query;
    }
}
