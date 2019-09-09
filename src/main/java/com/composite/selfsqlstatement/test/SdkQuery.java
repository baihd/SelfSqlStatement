package com.composite.selfsqlstatement.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SdkQuery {
    private SdkCriteria sdkCriteria;

    private String table;

    private Boolean isDistinct = false;

    private String order;

    private List<String> fields = new ArrayList<>();

    public SdkQuery addField(String... field) {
        fields.addAll(Arrays.asList(field));
        return this;
    }

    public SdkCriteria getSdkCriteria() {
        return sdkCriteria;
    }

    public SdkQuery setSdkCriteria(SdkCriteria sdkCriteria) {
        this.sdkCriteria = sdkCriteria;
        return this;
    }

    public String getTable() {
        return table;
    }

    public SdkQuery setTable(String table) {
        this.table = table;
        return this;
    }

    public List<String> getFields() {
        return fields;
    }

    public SdkQuery setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public Boolean getDistinct() {
        return isDistinct;
    }

    public SdkQuery setDistinct(Boolean distinct) {
        isDistinct = distinct;
        return this;
    }

}
