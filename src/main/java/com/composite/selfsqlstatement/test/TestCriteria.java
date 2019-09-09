package com.composite.selfsqlstatement.test;

import java.util.*;

public class TestCriteria {
    private static Map<String,Object> params = new HashMap<>();

    public static void main(String[] args) {
        SdkQuery query = new SdkQuery();
        query.setTable("user_info").addField("name", "age", "address").setDistinct(false).setOrder("id desc");
        SdkCriteria criteria = new SdkCriteria();
        List<Integer> ageList = new ArrayList<>();
        ageList.add(1);
        ageList.add(3);
        criteria.operators(
                SdkCriteria.addOperators(SdkCriteria.and("age").equal(25),
                        SdkCriteria.or("address").equal("南京")),
                SdkCriteria.and("name").equal("zhangsan"),
                SdkCriteria.and("age").in(ageList),
                SdkCriteria.and("createTime").between(new Date(), new Date()));
        query.setSdkCriteria(criteria);

        StringBuffer buffer = new StringBuffer("");
        jiexi(criteria, buffer);
        System.out.println(buffer.toString());
    }


    public static void jiexi(SdkCriteria criteria, StringBuffer buffer) {
        List<SdkCriteria> criteriaList = criteria.getOperators();
        if (criteriaList.size() > 0) {
            for (SdkCriteria c : criteriaList) {
                if (c.getOperators().size() > 0) {
                    buffer.append(" " + c.getExpress() + " ( ");
                    jiexi(c, buffer);
                    buffer.append(" ) ");
                } else {
                    String key = c.getKey();
                    if (!buffer.toString().trim().endsWith("(")) {
                        buffer.append(" " + c.getExpress());
                    }

                    buffer.append(" ").append(key);
                    String type = c.getType();
                    if (type.equals("equal") || type.equals("prefixLike")) {
                        Object value = c.getValue();
                        buffer.append(" = ").append(value);
                    } else if (type.equals("in")) {
                        buffer.append(" in ( ");
                        List objList = c.getValues();
                        for (int i = 0; i < objList.size(); i++) {
                            Object obj = objList.get(i);
                            buffer.append(obj);
                            if (i != objList.size() - 1) {
                                buffer.append(",");
                            }
                        }
                        buffer.append(" ) ");
                    } else if (type.equals("between")) {
                        buffer.append(" between ");
                        buffer.append(c.getStart());
                        buffer.append(" and ");
                        buffer.append(c.getEnd());
                    }
                }
            }
        } else {
            return;
        }

    }
}
