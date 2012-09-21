package org.isma.web.versusfighting.model;

import java.util.ArrayList;
import java.util.List;

public class MatchesAmount {
    private final String label;
    private final int amount;

    public static final MatchesAmount BESTOF_3 = new MatchesAmount("Bestof3", 3);
    public static final MatchesAmount BESTOF_5 = new MatchesAmount("Bestof5", 5);
    public static final MatchesAmount BESTOF_7 = new MatchesAmount("Bestof7", 7);

    private final static List<MatchesAmount> LIST = new ArrayList<MatchesAmount>();
    static {
        LIST.add(BESTOF_3);
        LIST.add(BESTOF_5);
        LIST.add(BESTOF_7);
    }

    private MatchesAmount(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public int getAmount() {
        return amount;
    }

    public static List<MatchesAmount> getList() {
        return LIST;
    }

    @Override
    public String toString() {
        //TODO essayer de faire mieux coté renderer jsp
        return label;
    }
}
