package com.increff.pos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductData extends ProductForm{
    private String barcode;
    private int id;
}
