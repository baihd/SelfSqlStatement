package com.composite.selfsqlstatement.self.query.builder.instance;

import com.composite.selfsqlstatement.self.query.builder.QueryBuilder;

/**
 * 字段模糊匹配
 */
public class WildcardQB extends QueryBuilder {

    private String operator;

    private String fieldName;

    private String wildcardType;

    private String value;

    private WildcardQB(String operator, String fieldName, String wildcardType, String value) {
        this.operator = operator;
        this.fieldName = fieldName;
        this.wildcardType = wildcardType;
        this.value = value;
    }

    public static WildcardQB andFull(String fieldName, String value) {
        return new WildcardQB("and", fieldName, "full", value);
    }

    public static WildcardQB andPrefix(String fieldName, String value) {
        return new WildcardQB("and", fieldName, "prefix", value);
    }

    public static WildcardQB andSuffix(String fieldName, String value) {
        return new WildcardQB("and", fieldName, "suffix", value);
    }

    public static WildcardQB orFull(String fieldName, String value) {
        return new WildcardQB("or", fieldName, "full", value);
    }

    public static WildcardQB orPrefix(String fieldName, String value) {
        return new WildcardQB("or", fieldName, "prefix", value);
    }

    public static WildcardQB orSuffix(String fieldName, String value) {
        return new WildcardQB("or", fieldName, "suffix", value);
    }

    public String getOperator() {
        return operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getWildcardType() {
        return wildcardType;
    }

    public String getValue() {
        return value;
    }
}
