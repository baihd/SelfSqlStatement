package com.composite.selfsqlstatement.self.query.builder.instance;

import com.composite.selfsqlstatement.self.query.builder.QueryBuilder;

/**
 * 字段范围
 */
public class RangeQB extends QueryBuilder {

    private String operator;

    private String fieldName;

    private String symbol;

    private Object from;

    private Object to;

    public RangeQB(String operator, String fieldName, String symbol, Object from, Object to) {
        this.operator = operator;
        this.fieldName = fieldName;
        this.symbol = symbol;
        this.from = from;
        this.to = to;
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param to
     * @return
     */
    public static RangeQB andLt(String fieldName, Object to) {
        return new RangeQB("and", fieldName, "lt", null, to);
    }

    /**
     * 大于
     *
     * @param fieldName
     * @param from
     * @return
     */
    public static RangeQB andGt(String fieldName, Object from) {
        return new RangeQB("and", fieldName, "gt", from, null);
    }

    /**
     * 小于且大于
     *
     * @param fieldName
     * @param from
     * @param to
     * @return
     */
    public static RangeQB andLtGt(String fieldName, Object from, Object to) {
        return new RangeQB("and", fieldName, "ltGt", from, to);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param to
     * @return
     */
    public static RangeQB andLte(String fieldName, Object to) {
        return new RangeQB("and", fieldName, "lte", null, to);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param from
     * @return
     */
    public static RangeQB andGte(String fieldName, Object from) {
        return new RangeQB("and", fieldName, "gte", from, null);
    }

    /**
     * 小于等于且大于等于
     *
     * @param fieldName
     * @param from
     * @param to
     * @return
     */
    public static RangeQB andLteGte(String fieldName, Object from, Object to) {
        return new RangeQB("and", fieldName, "lteGte", from, to);
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param to
     * @return
     */
    public static RangeQB orLt(String fieldName, Object to) {
        return new RangeQB("or", fieldName, "lt", null, to);
    }

    /**
     * 大于
     *
     * @param fieldName
     * @param from
     * @return
     */
    public static RangeQB orGt(String fieldName, Object from) {
        return new RangeQB("or", fieldName, "gt", from, null);
    }

    /**
     * 小于且大于
     *
     * @param fieldName
     * @param from
     * @param to
     * @return
     */
    public static RangeQB orLtGt(String fieldName, Object from, Object to) {
        return new RangeQB("or", fieldName, "ltGt", from, to);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param to
     * @return
     */
    public static RangeQB orLte(String fieldName, Object to) {
        return new RangeQB("or", fieldName, "lte", null, to);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param from
     * @return
     */
    public static RangeQB orGte(String fieldName, Object from) {
        return new RangeQB("or", fieldName, "gte", from, null);
    }

    /**
     * 小于等于且大于等于
     *
     * @param fieldName
     * @param from
     * @param to
     * @return
     */
    public static RangeQB orLteGte(String fieldName, Object from, Object to) {
        return new RangeQB("or", fieldName, "lteGte", from, to);
    }

    public String getOperator() {
        return operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getSymbol() {
        return symbol;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }
}
