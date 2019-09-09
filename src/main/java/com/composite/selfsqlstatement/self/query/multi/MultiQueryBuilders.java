package com.composite.selfsqlstatement.self.query.multi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiQueryBuilders {

    private OuterQueryBuilders baseOuterQBs;

    private List<OuterQueryBuilders> outerQBsList = new ArrayList<>();

    public MultiQueryBuilders() {
    }

    public MultiQueryBuilders setBaseOuterQBs(OuterQueryBuilders baseOuterQBs) {
        this.baseOuterQBs = baseOuterQBs;
        return this;
    }

    public MultiQueryBuilders innerJoin(OuterQueryBuilders nextQBs, String... joinField) {
        nextQBs.setOperator("innerJoin");
        nextQBs.setJoinFields(Arrays.asList(joinField));
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public MultiQueryBuilders leftJoin(OuterQueryBuilders nextQBs, String... joinField) {
        nextQBs.setOperator("leftJoin");
        nextQBs.setJoinFields(Arrays.asList(joinField));
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public MultiQueryBuilders rightJoin(OuterQueryBuilders nextQBs, String... joinField) {
        nextQBs.setOperator("rightJoin");
        nextQBs.setJoinFields(Arrays.asList(joinField));
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public MultiQueryBuilders fullJoin(OuterQueryBuilders nextQBs, String... joinField) {
        nextQBs.setOperator("fullJoin");
        nextQBs.setJoinFields(Arrays.asList(joinField));
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public MultiQueryBuilders union(OuterQueryBuilders nextQBs) {
        nextQBs.setOperator("union");
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public MultiQueryBuilders unionAll(OuterQueryBuilders nextQBs) {
        nextQBs.setOperator("unionAll");
        this.outerQBsList.add(nextQBs);
        return this;
    }

    public OuterQueryBuilders getBaseOuterQBs() {
        return baseOuterQBs;
    }

    public List<OuterQueryBuilders> getOuterQBsList() {
        return outerQBsList;
    }
}
