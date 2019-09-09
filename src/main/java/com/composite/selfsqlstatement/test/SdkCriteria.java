package com.composite.selfsqlstatement.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SdkCriteria {
    private String key;

    private Object value;

    private List<Object> values;

    private String type;

    private Date start;

    private Date end;

    private String express = "and";

    private List<SdkCriteria> criteriaList = new ArrayList<>();

    public SdkCriteria() {
    }

    private SdkCriteria(String key, String express) {
        this.key = key;
        this.express = express;
    }

    public SdkCriteria equal(Object obj) {
        this.value = obj;
        this.type = "equal";
        return this;
    }

    public SdkCriteria notEqual(Object obj) {
        this.value = obj;
        this.type = "notEqual";
        return this;
    }

    public SdkCriteria lt(Object obj) {
        this.value = obj;
        this.type = "lt";
        return this;
    }

    public SdkCriteria gt(Object obj) {
        this.value = obj;
        this.type = "gt";
        return this;
    }

    public SdkCriteria ltEqual(Object obj) {
        this.value = obj;
        this.type = "lte";
        return this;
    }

    public SdkCriteria gtEqual(Object obj) {
        this.value = obj;
        this.type = "gte";
        return this;
    }

    public SdkCriteria between(Date start, Date end) {
        this.start = start;
        this.end = end;
        this.type = "between";
        return this;
    }

    public SdkCriteria prefixLike(String data) {
        this.value = data;
        this.type = "prefixLike";
        return this;
    }

    public SdkCriteria suffixLike(String data) {
        this.value = data;
        this.type = "suffixLike";
        return this;
    }

    public SdkCriteria like(String data) {
        this.value = data;
        this.type = "like";
        return this;
    }

    public SdkCriteria in(List datas) {
        this.values = datas;
        this.type = "in";
        return this;
    }

    public SdkCriteria notIn(List datas) {
        this.values = datas;
        this.type = "notIn";
        return this;
    }

    public void operators(SdkCriteria... criteria) {
        this.criteriaList.addAll(Arrays.asList(criteria));
    }

    public List<SdkCriteria> getOperators() {
        return criteriaList;
    }

    public static SdkCriteria addOperators(SdkCriteria... criteria) {
        SdkCriteria criteria1 = new SdkCriteria();
        criteria1.criteriaList.addAll(Arrays.asList(criteria));
        return criteria1;
    }

    public static SdkCriteria and(String key) {
        return new SdkCriteria(key, "and");
    }

    public SdkCriteria andTogetherOperators(SdkCriteria... sdkCriteria) {
        this.criteriaList.addAll(Arrays.asList(sdkCriteria));
        return this;
    }

    public static SdkCriteria or(String key) {
        return new SdkCriteria(key, "or");
    }

    public SdkCriteria orTogetherOperators(SdkCriteria... sdkCriteria) {
        this.criteriaList.addAll(Arrays.asList(sdkCriteria));
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getExpress() {
        return (express == null || express.isEmpty()) ? "and" : express;
    }

}
