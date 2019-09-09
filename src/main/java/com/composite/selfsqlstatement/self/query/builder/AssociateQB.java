package com.composite.selfsqlstatement.self.query.builder;

import java.util.Arrays;
import java.util.List;

public class AssociateQB {

    private String operator;

    private List<QueryBuilder> queryBuilders;

    private AssociateQB(String operator, List<QueryBuilder> queryBuilders) {
        this.operator = operator;
        this.queryBuilders = queryBuilders;
    }

    public static AssociateQB defaultQB(QueryBuilder... queryBuilder) {
        return new AssociateQB("default", Arrays.asList(queryBuilder));
    }

    public static AssociateQB and(QueryBuilder... queryBuilder) {
        return new AssociateQB("and", Arrays.asList(queryBuilder));
    }

    public static AssociateQB or(QueryBuilder... queryBuilder) {
        return new AssociateQB("or", Arrays.asList(queryBuilder));
    }

    public String getOperator() {
        return operator;
    }

    public List<QueryBuilder> getQueryBuilders() {
        return queryBuilders;
    }
}
