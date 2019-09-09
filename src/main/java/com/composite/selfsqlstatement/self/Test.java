package com.composite.selfsqlstatement.self;

import com.composite.selfsqlstatement.self.query.analysis.QueryBuildersAnalysis;
import com.composite.selfsqlstatement.self.query.builder.AssociateQB;
import com.composite.selfsqlstatement.self.query.builder.ExistsQB;
import com.composite.selfsqlstatement.self.query.builder.entity.Order;
import com.composite.selfsqlstatement.self.query.builder.instance.RangeQB;
import com.composite.selfsqlstatement.self.query.builder.instance.TermsQB;
import com.composite.selfsqlstatement.self.query.builder.instance.WildcardQB;
import com.composite.selfsqlstatement.self.query.builders.QueryBuilders;
import com.composite.selfsqlstatement.self.query.multi.MultiQueryBuilders;
import com.composite.selfsqlstatement.self.query.multi.OuterQueryBuilders;

public class Test {

    public static void main(String[] args) {

        QueryBuilders queryBuilders = new QueryBuilders();
        queryBuilders.setTable("user")
                .setFields("id", "name", "age", "sex", "phone", "birthday").setOrders(
                Order.ascOrder("id"), Order.descOrder("name"))
                .setLimit(0, 10).setGroups("name");

        //查询where条件
        queryBuilders.setAssociateQB(
                AssociateQB.defaultQB(
                        TermsQB.andEqual("sex", "男"),
                        TermsQB.andIn("id", "1", "2", "3"),
                        RangeQB.orLteGte("age", 20, 30),
                        WildcardQB.orFull("name", "赵")),
                AssociateQB.and(
                        TermsQB.orEqual("sex", "女"),
                        TermsQB.orIn("id", "4", "5", "6"),
                        RangeQB.orLteGte("age", 30, 40),
                        WildcardQB.orFull("name", "钱")));
        //查询exist条件
        ExistsQB existsQB = new ExistsQB();
        existsQB.setExist().setTable("org").setFields("*")
                .setAssociateQBs(
                        AssociateQB.defaultQB(
                                TermsQB.orIn("id", "1", "2", "3"),
                                TermsQB.orEqual("sex", "男"),
                                RangeQB.orLteGte("age", 20, 40)));

        queryBuilders.setExistsQB(existsQB);

        String sql = QueryBuildersAnalysis.analysisSql(queryBuilders);


        MultiQueryBuilders multiQueryBuilders = new MultiQueryBuilders();

        multiQueryBuilders.
                setBaseOuterQBs(OuterQueryBuilders.buildOQBs(queryBuilders)).
                innerJoin(OuterQueryBuilders.buildOQBs(queryBuilders), "id")
                .union(OuterQueryBuilders.buildOQBs(queryBuilders));

    }


}
