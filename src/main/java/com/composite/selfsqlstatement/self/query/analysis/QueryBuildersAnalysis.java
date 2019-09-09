package com.composite.selfsqlstatement.self.query.analysis;

import com.composite.selfsqlstatement.self.query.builder.AssociateQB;
import com.composite.selfsqlstatement.self.query.builder.ExistsQB;
import com.composite.selfsqlstatement.self.query.builder.QueryBuilder;
import com.composite.selfsqlstatement.self.query.builder.entity.Order;
import com.composite.selfsqlstatement.self.query.builder.instance.RangeQB;
import com.composite.selfsqlstatement.self.query.builder.instance.TermsQB;
import com.composite.selfsqlstatement.self.query.builder.instance.WildcardQB;
import com.composite.selfsqlstatement.self.query.builders.QueryBuilders;
import com.composite.selfsqlstatement.self.utils.CheckUtils;

import java.util.List;

/**
 * 解析QueryBuilders
 */
public class QueryBuildersAnalysis {

    public static String analysisSql(QueryBuilders queryBuilders) {
        String table = queryBuilders.getTable();
        List<String> fields = queryBuilders.getFields();
        ExistsQB existsQB = queryBuilders.getExistsQB();
        if (CheckUtils.strIsEmpty(table)) {
            return "查询表名缺失";
        }
        if (CheckUtils.listIsEmpty(fields)) {
            return "查询字段缺失";
        }

        //拼接'where'
        String whereBuilder;

        if (existsQB != null) {
            String tableExistsQB = existsQB.getTable();
            List<String> fieldsExistsQB = existsQB.getFields();
            if (CheckUtils.strIsEmpty(tableExistsQB)) {
                return "Exist查询表名缺失";
            }
            if (CheckUtils.listIsEmpty(fieldsExistsQB)) {
                return "Exist查询字段缺失";
            }
            whereBuilder = buildExist(queryBuilders);
        } else {
            whereBuilder = buildWhere(queryBuilders);
        }

        //拼接'select field from table'
        String simpleQueryBuilder = buildSimpleQuery(queryBuilders);

        //拼接'group by'
        String groupBuilder = buildGroup(queryBuilders);

        //拼接'order by'
        String orderBuilder = buildOrder(queryBuilders);

        //拼接'limit'
        String limitBuilder = buildLimit(queryBuilders);

        StringBuilder finalSqlBuilder = new StringBuilder();
        finalSqlBuilder.append(simpleQueryBuilder);
        finalSqlBuilder.append(" ");
        finalSqlBuilder.append(whereBuilder);
        finalSqlBuilder.append(" ");
        finalSqlBuilder.append(groupBuilder);
        finalSqlBuilder.append(" ");
        finalSqlBuilder.append(orderBuilder);
        finalSqlBuilder.append(" ");
        finalSqlBuilder.append(limitBuilder);
        return finalSqlBuilder.toString();
    }

    /**
     * 拼接'select field from table'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildSimpleQuery(QueryBuilders queryBuilders) {
        String table = queryBuilders.getTable();
        List<String> fields = queryBuilders.getFields();

        StringBuilder simpleQueryBuilder = new StringBuilder();
        simpleQueryBuilder.append("SELECT ");
        for (String field : fields) {
            simpleQueryBuilder.append(field);
            simpleQueryBuilder.append(",");
        }
        simpleQueryBuilder.deleteCharAt(simpleQueryBuilder.length() - 1);
        simpleQueryBuilder.append(" FROM ");
        simpleQueryBuilder.append(table);
        return simpleQueryBuilder.toString();
    }

    /**
     * 拼接'group by'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildGroup(QueryBuilders queryBuilders) {
        List<String> groups = queryBuilders.getGroups();
        if (CheckUtils.listIsNotEmpty(groups)) {
            StringBuilder groupBuilder = new StringBuilder();
            groupBuilder.append("GROUP BY ");
            for (String group : groups) {
                groupBuilder.append(group);
                groupBuilder.append(",");
            }
            groupBuilder.deleteCharAt(groupBuilder.length() - 1);
            return groupBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * 拼接'order by'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildOrder(QueryBuilders queryBuilders) {
        List<Order> orders = queryBuilders.getOrders();
        if (CheckUtils.listIsNotEmpty(orders)) {
            StringBuilder orderBuilder = new StringBuilder();
            orderBuilder.append("ORDER BY ");
            for (Order order : orders) {
                orderBuilder.append(order.getFieldName());
                orderBuilder.append(" ");
                orderBuilder.append(order.getOrder());
                orderBuilder.append(",");
            }
            orderBuilder.deleteCharAt(orderBuilder.length() - 1);
            return orderBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * 拼接'limit'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildLimit(QueryBuilders queryBuilders) {
        Integer page = queryBuilders.getPage();
        Integer size = queryBuilders.getSize();
        if (page != null & size != null) {
            StringBuilder limitBuilder = new StringBuilder();
            limitBuilder.append("LIMIT ");
            limitBuilder.append(page);
            limitBuilder.append(",");
            limitBuilder.append(size);
            return limitBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * 拼接'exist'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildExist(QueryBuilders queryBuilders) {
        StringBuilder whereBuilder = new StringBuilder();
        ExistsQB existsQB = queryBuilders.getExistsQB();
        String table = existsQB.getTable();
        List<String> fields = existsQB.getFields();
        Boolean exist = existsQB.getExist();
        List<AssociateQB> associateQBList = existsQB.getAssociateQBs();
        whereBuilder.append(" WHERE");
        if (exist) {
            whereBuilder.append(" EXISTS ");
        } else {
            whereBuilder.append(" NOT EXISTS ");
        }
        whereBuilder.append(" ( ");
        whereBuilder.append(" SELECT ");
        for (String field : fields) {
            whereBuilder.append(field);
            whereBuilder.append(",");
        }
        whereBuilder.deleteCharAt(whereBuilder.length() - 1);
        whereBuilder.append(" FROM ");
        whereBuilder.append(table);
        whereBuilder.append(" ");
        whereBuilder.append(buildAssociateQBList(associateQBList));
        whereBuilder.append(" ) ");
        return whereBuilder.toString();
    }

    /**
     * 拼接'where'
     *
     * @param queryBuilders
     * @return
     */
    private static String buildWhere(QueryBuilders queryBuilders) {
        List<AssociateQB> associateQBList = queryBuilders.getAssociateQBs();
        return buildAssociateQBList(associateQBList);
    }

    private static String buildAssociateQBList(List<AssociateQB> associateQBList) {
        if (CheckUtils.listIsNotEmpty(associateQBList)) {
            StringBuilder defaultBuilder = new StringBuilder();
            StringBuilder noDefaultBuilder = new StringBuilder();
            defaultBuilder.append("WHERE ");
            for (AssociateQB associateQB : associateQBList) {
                List<QueryBuilder> queryBuilderList = associateQB.getQueryBuilders();
                String operator = associateQB.getOperator();
                if (operator.equals("default")) {
                    defaultBuilder.append("(");
                    defaultBuilder.append(" ");
                    String buildPerBuilderList = buildPerBuilderList(queryBuilderList);
                    defaultBuilder.append(buildPerBuilderList);
                    defaultBuilder.append(" ");
                    defaultBuilder.append(")");
                } else {
                    noDefaultBuilder.append(" ");
                    noDefaultBuilder.append(operator);
                    noDefaultBuilder.append(" ");
                    noDefaultBuilder.append("(");
                    noDefaultBuilder.append(" ");
                    String buildPerBuilderList = buildPerBuilderList(queryBuilderList);
                    noDefaultBuilder.append(buildPerBuilderList);
                    noDefaultBuilder.append(" ");
                    noDefaultBuilder.append(")");
                }
            }
            defaultBuilder.append(noDefaultBuilder);
            return defaultBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * 拼接条件语句
     *
     * @param queryBuilderList
     * @return
     */
    private static String buildPerBuilderList(List<QueryBuilder> queryBuilderList) {
        StringBuilder perBuilderList = new StringBuilder();
        for (QueryBuilder queryBuilder : queryBuilderList) {
            StringBuilder perBuilder = new StringBuilder();
            if (queryBuilder instanceof TermsQB) {
                TermsQB termsQB = (TermsQB) queryBuilder;

                String operatorTermsQB = termsQB.getOperator();
                String fieldName = termsQB.getFieldName();
                String termsType = termsQB.getTermsType();
                List values = termsQB.getValues();
                perBuilder.append(" ");
                perBuilder.append(operatorTermsQB);
                perBuilder.append(" ");
                perBuilder.append(fieldName);
                perBuilder.append(" ");
                if (termsType.equals("equal")) {
                    perBuilder.append("=");
                    perBuilder.append(" ");
                    perBuilder.append("'");
                    perBuilder.append(values.get(0));
                    perBuilder.append("'");
                } else if (termsType.equals("NotEqual")) {
                    perBuilder.append("!=");
                    perBuilder.append(" ");
                    perBuilder.append("'");
                    perBuilder.append(values.get(0));
                    perBuilder.append("'");
                } else if (termsType.equals("in")) {
                    perBuilder.append("IN");
                    perBuilder.append(" ");
                    perBuilder.append("(");
                    for (int i = 0; i < values.size(); i++) {
                        perBuilder.append("'");
                        perBuilder.append(values.get(i));
                        perBuilder.append("'");
                        perBuilder.append(",");
                    }
                    perBuilder.deleteCharAt(perBuilder.length() - 1);
                    perBuilder.append(")");
                } else if (termsType.equals("notIn")) {
                    perBuilder.append("NOT IN");
                    perBuilder.append(" ");
                    perBuilder.append("(");
                    for (int i = 0; i < values.size(); i++) {
                        perBuilder.append("'");
                        perBuilder.append(values.get(i));
                        perBuilder.append("'");
                        perBuilder.append(",");
                    }
                    perBuilder.deleteCharAt(perBuilder.length() - 1);
                    perBuilder.append(")");
                }
            } else if (queryBuilder instanceof WildcardQB) {
                WildcardQB wildcardQB = (WildcardQB) queryBuilder;
                String operatorWildcardQB = wildcardQB.getOperator();
                String fieldName = wildcardQB.getFieldName();
                String wildcardType = wildcardQB.getWildcardType();
                String value = wildcardQB.getValue();

                perBuilder.append(operatorWildcardQB);
                perBuilder.append(" ");
                perBuilder.append(fieldName);
                perBuilder.append(" ");
                perBuilder.append("LIKE");
                if (wildcardType.equals("full")) {
                    perBuilder.append(" ");
                    perBuilder.append("'%");
                    perBuilder.append(value);
                    perBuilder.append("%'");
                } else if (wildcardType.equals("prefix")) {
                    perBuilder.append(" ");
                    perBuilder.append("'%");
                    perBuilder.append(value);
                    perBuilder.append("'");
                } else if (wildcardType.equals("suffix")) {
                    perBuilder.append(" ");
                    perBuilder.append("%'");
                    perBuilder.append(value);
                    perBuilder.append("'");
                }
            } else if (queryBuilder instanceof RangeQB) {
                RangeQB rangeQB = (RangeQB) queryBuilder;
                String operatorRangeQB = rangeQB.getOperator();
                String fieldName = rangeQB.getFieldName();
                String symbol = rangeQB.getSymbol();
                Object from = rangeQB.getFrom();
                Object to = rangeQB.getTo();

                perBuilder.append(operatorRangeQB);
                perBuilder.append(" ");
                if (symbol.equals("lt")) {
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append("<");
                    perBuilder.append(from);
                } else if (symbol.equals("gt")) {
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append(">");
                    perBuilder.append(to);
                } else if (symbol.equals("ltGt")) {
                    perBuilder.append(from);
                    perBuilder.append(" ");
                    perBuilder.append("<");
                    perBuilder.append(" ");
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append("<");
                    perBuilder.append(" ");
                    perBuilder.append(to);
                } else if (symbol.equals("lte")) {
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append("<=");
                    perBuilder.append(from);
                } else if (symbol.equals("gte")) {
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append(">");
                    perBuilder.append(to);
                } else if (symbol.equals("lteGte")) {
                    perBuilder.append(from);
                    perBuilder.append(" ");
                    perBuilder.append("<=");
                    perBuilder.append(" ");
                    perBuilder.append(fieldName);
                    perBuilder.append(" ");
                    perBuilder.append("<=");
                    perBuilder.append(" ");
                    perBuilder.append(to);
                }
            }
            perBuilder.append(" ");
            perBuilderList.append(perBuilder);
        }
        String value = perBuilderList.substring(1, 4);
        if ("and".equals(value)) {
            return perBuilderList.substring(4);
        } else if ("or ".equals(value)) {
            return perBuilderList.substring(4);
        } else {
            return perBuilderList.toString();
        }
    }

}
