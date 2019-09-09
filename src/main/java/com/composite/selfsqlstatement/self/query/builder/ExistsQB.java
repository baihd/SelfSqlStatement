package com.composite.selfsqlstatement.self.query.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据存在或不存在
 */
public class ExistsQB {

    private Boolean exist;

    private String table;

    private List<AssociateQB> associateQBs = new ArrayList<>();

    private List<String> fields = new ArrayList<>();

    public ExistsQB() {
    }

    public ExistsQB setExist() {
        this.exist = true;
        return this;
    }

    public ExistsQB setNotExist() {
        this.exist = false;
        return this;
    }

    public ExistsQB setTable(String table) {
        this.table = table;
        return this;
    }

    public ExistsQB setAssociateQBs(AssociateQB... associateQB) {
        this.associateQBs.addAll(Arrays.asList(associateQB));
        return this;
    }

    public ExistsQB setFields(String... field) {
        this.fields.addAll(Arrays.asList(field));
        return this;
    }


    public Boolean getExist() {
        return exist;
    }

    public String getTable() {
        return table;
    }

    public List<AssociateQB> getAssociateQBs() {
        return associateQBs;
    }

    public List<String> getFields() {
        return fields;
    }
}
