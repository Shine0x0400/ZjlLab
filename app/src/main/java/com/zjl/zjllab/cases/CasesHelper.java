package com.zjl.zjllab.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasesHelper {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Case> ITEMS = new ArrayList<Case>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Case> ITEM_MAP = new HashMap<String, Case>();

    static {
        addItem(createCase(1, "Test PorterDuffXfermode"));
        addItem(createCase(2, "Test FLAG_ACTIVITY_FORWARD_RESULT"));
        addItem(createCase(3, "Test RoundImageView"));
        addItem(createCase(4, "地图"));
    }

    private static void addItem(Case item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Case createCase(int position, String content) {
        return new Case(String.valueOf(position), content);
    }

    public static class Case {
        public final String id;
        public final String content;

        public Case(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
