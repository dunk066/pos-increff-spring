package com.increff.pos.service;

import com.increff.pos.model.BrandForm;

public class testUtil {

    public static BrandForm getBrandFormDto(String brand, String category) {
        BrandForm brandForm = new BrandForm();
        brandForm.setBrand(brand);
        brandForm.setCategory(category);
        return brandForm;
    }
}
