package com.increff.pos.util;

import com.increff.pos.model.BrandForm;
import com.increff.pos.model.ProductSearchForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.ProductMasterPojo;

public class NormalizeUtil {
    public static void normalizeBrandMasterPojo(BrandMasterPojo p) {
        p.setBrand(StringUtil.toLowerCase(p.getBrand()));
        p.setCategory(StringUtil.toLowerCase(p.getCategory()));
    }
    public static void normalizaProductMasterPojo(ProductMasterPojo b){
        b.setBarcode(StringUtil.toLowerCase(b.getBarcode()));
        b.setName(StringUtil.toLowerCase(b.getName()));
    }

    public static void normalizeBrandForm(BrandForm b) {
        b.setBrand(StringUtil.toLowerCase(b.getBrand()));
        b.setCategory(StringUtil.toLowerCase(b.getCategory()));
    }

    public static void normalizeProductSearchForm(ProductSearchForm f){
        f.setName(StringUtil.toLowerCase(f.getName()));
        f.setBarcode(StringUtil.toLowerCase(f.getBarcode()));
        f.setBrand(StringUtil.toLowerCase(f.getBrand()));
        f.setCategory(StringUtil.toLowerCase(f.getCategory()));
    }
}
