package com.trade_republic.quotation.data.enums;

public enum StompMessageTypes {
    ADD,
    DELETE,
    QUOTE;

    public static StompMessageTypes fromValue(String name) {
        for (StompMessageTypes t : StompMessageTypes.values())
            if (t.name().equals(name)) return t;
        return null;
    }
}
