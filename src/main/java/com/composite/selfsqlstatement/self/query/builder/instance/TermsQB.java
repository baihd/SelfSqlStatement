package com.composite.selfsqlstatement.self.query.builder.instance;

import com.composite.selfsqlstatement.self.query.builder.QueryBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * 字段等于或不等于或在或不在
 */
public class TermsQB extends QueryBuilder {

    private String operator;

    private String fieldName;

    private String termsType;

    private List<?> values;

    private TermsQB(String operator, String fieldName, String termsType, List<?> values) {
        this.operator = operator;
        this.fieldName = fieldName;
        this.termsType = termsType;
        this.values = values;
    }

    public static TermsQB andEqual(String name, String... values) {
        return new TermsQB("and", name, "equal", Arrays.asList(values));
    }

    public static TermsQB andNotEqual(String name, String... values) {
        return new TermsQB("and", name, "NotEqual", Arrays.asList(values));
    }

    public static TermsQB andIn(String name, String... values) {
        return new TermsQB("and", name, "in", Arrays.asList(values));
    }

    public static TermsQB andNotIn(String name, String... values) {
        return new TermsQB("and", name, "notIn", Arrays.asList(values));
    }

    public static TermsQB orEqual(String name, String... values) {
        return new TermsQB("or", name, "equal", Arrays.asList(values));
    }

    public static TermsQB orNotEqual(String name, String... values) {
        return new TermsQB("or", name, "NotEqual", Arrays.asList(values));
    }

    public static TermsQB orIn(String name, String... values) {
        return new TermsQB("or", name, "in", Arrays.asList(values));
    }

    public static TermsQB orNotIn(String name, String... values) {
        return new TermsQB("or", name, "notIn", Arrays.asList(values));
    }

    public String getOperator() {
        return operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTermsType() {
        return termsType;
    }

    public List<?> getValues() {
        return values;
    }
}
