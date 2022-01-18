package com.increff.pos.util;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;

public class convertUtil {
    public static BrandData convertBrandMastePojotoBrandData(BrandMasterPojo p) {
        BrandData d = new BrandData();
        d.category = p.getCategory();
        d.brand = p.getBrand();
        d.id = p.getId();
        return d;
    }

    public static BrandMasterPojo convertBrandFormtoBrandMasterPojo(BrandForm f) {
        BrandMasterPojo b = new BrandMasterPojo();
        b.setCategory(f.category);
        b.setBrand(f.brand);
        return b;
    }


    public static BrandData convertBrandMasterPojotoBrandData(BrandMasterPojo p) {
        BrandData d = new BrandData();
        d.brand = p.getBrand();
        d.category = p.getCategory();
        d.id = p.getId();
        return d;
    }

    public static BrandMasterPojo convertBrandFormtoBrandMastePojo(BrandForm f) {
        BrandMasterPojo p = new BrandMasterPojo();
        p.setBrand(f.brand);
        p.setCategory(f.category);
        return p;
    }
}
