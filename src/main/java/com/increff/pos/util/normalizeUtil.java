package com.increff.pos.util;

import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.ProductMasterPojo;

public class normalizeUtil {
    public static void normalizeBrandMasterPojo(BrandMasterPojo p) {
        p.setBrand(StringUtil.toLowerCase(p.getBrand()));
        p.setCategory(StringUtil.toLowerCase(p.getCategory()));
    }
    public static void normalizaProductMasterPojo(ProductMasterPojo b){
        b.setBarcode(StringUtil.toLowerCase(b.getBarcode()));
        b.setName(StringUtil.toLowerCase(b.getName()));
    }

    public static void normalizeBrandForm(BrandForm b) {
        b.brand = StringUtil.toLowerCase(b.brand);
        b.category = StringUtil.toLowerCase(b.category);
    }
}
