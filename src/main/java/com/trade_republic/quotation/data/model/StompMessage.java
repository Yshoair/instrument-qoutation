package com.trade_republic.quotation.data.model;

import lombok.Data;

@Data
public abstract class StompMessage <T> {
    protected String type;
    protected T data;
}
