package com.composite.selfsqlstatement.self.query.multi;

import com.composite.selfsqlstatement.self.query.builders.QueryBuilders;

import java.util.List;

public class OuterQueryBuilders {

    private String operator;

    private QueryBuilders queryBuilders;

    private List<String> joinFields;

    private OuterQueryBuilders(String operator, QueryBuilders queryBuilders, List<String> joinFields) {
        this.operator = operator;
        this.queryBuilders = queryBuilders;
        this.joinFields = joinFields;
    }

    public static OuterQueryBuilders buildOQBs(QueryBuilders queryBuilders) {
        return new OuterQueryBuilders("", queryBuilders, null);
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setQueryBuilders(QueryBuilders queryBuilders) {
        this.queryBuilders = queryBuilders;
    }

    public void setJoinFields(List<String> joinFields) {
        this.joinFields = joinFields;
    }

    public String getOperator() {
        return operator;
    }

    public QueryBuilders getQueryBuilders() {
        return queryBuilders;
    }

    public List<String> getJoinFields() {
        return joinFields;
    }
}
