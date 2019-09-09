package com.composite.selfsqlstatement.self.query.builders;

import com.composite.selfsqlstatement.self.query.builder.AssociateQB;
import com.composite.selfsqlstatement.self.query.builder.ExistsQB;
import com.composite.selfsqlstatement.self.query.builder.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilders {

    private String table;

    private List<AssociateQB> associateQBs = new ArrayList<>();

    private ExistsQB existsQB;

    private Integer page = 0;

    private Integer size = 10;

    private List<String> fields = new ArrayList<>();

    private List<String> groups = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();

    public QueryBuilders() {

    }

    public QueryBuilders setTable(String table) {
        this.table = table;
        return this;
    }

    public QueryBuilders setAssociateQB(AssociateQB... associateQB) {
        this.associateQBs.addAll(Arrays.asList(associateQB));
        return this;
    }

    public QueryBuilders setExistsQB(ExistsQB existsQB) {
        this.existsQB = existsQB;
        return this;
    }

    public QueryBuilders setLimit(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        return this;
    }

    public QueryBuilders setFields(String... field) {
        this.fields.addAll(Arrays.asList(field));
        return this;
    }

    public QueryBuilders setGroups(String... group) {
        this.groups.addAll(Arrays.asList(group));
        return this;
    }

    public QueryBuilders setOrders(Order... order) {
        this.orders.addAll(Arrays.asList(order));
        return this;
    }

    public String getTable() {
        return table;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public List<AssociateQB> getAssociateQBs() {
        return associateQBs;
    }

    public ExistsQB getExistsQB() {
        return existsQB;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
