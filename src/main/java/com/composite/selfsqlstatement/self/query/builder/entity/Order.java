package com.composite.selfsqlstatement.self.query.builder.entity;

/**
 * 排序类
 */
public class Order {

    private String fieldName;

    private String order;

    private Order(String fieldName, String order) {
        this.fieldName = fieldName;
        this.order = order;
    }

    public static Order ascOrder(String fieldName) {
        return new Order(fieldName, "ASC");
    }

    public static Order descOrder(String fieldName) {
        return new Order(fieldName, "DESC");
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getOrder() {
        return order;
    }
}
