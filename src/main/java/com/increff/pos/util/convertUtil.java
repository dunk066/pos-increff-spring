package com.increff.pos.util;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;

public class convertUtil {
    public static BrandData convertBrandMastePojotoBrandData(BrandMasterPojo p) {
        BrandData d = new BrandData();
        d.setCategory(p.getCategory());
        d.setBrand(p.getBrand());
        d.setId(p.getId());
        return d;
    }

    public static BrandMasterPojo convertBrandFormtoBrandMasterPojo(BrandForm f) {
        BrandMasterPojo b = new BrandMasterPojo();
        b.setCategory(f.getCategory());
        b.setBrand(f.getBrand());
        return b;
    }


    public static BrandData convertBrandMasterPojotoBrandData(BrandMasterPojo p) {
        BrandData d = new BrandData();
        d.setBrand(p.getBrand());
        d.setCategory(p.getCategory());
        d.id = p.getId();
        return d;
    }

    public static BrandMasterPojo convertBrandFormtoBrandMastePojo(BrandForm f) {
        BrandMasterPojo p = new BrandMasterPojo();
        p.setBrand(f.getBrand());
        p.setCategory(f.getCategory());
        return p;
    }
}
