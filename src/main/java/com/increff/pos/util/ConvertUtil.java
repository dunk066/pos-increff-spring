package com.increff.pos.util;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.model.ProductData;
import com.increff.pos.model.ProductForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.ProductMasterPojo;

public class ConvertUtil {
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
    public static BrandForm convertProductFormtoBrandForm(ProductForm f){
        BrandForm b = new BrandForm();
        b.setBrand(f.getBrand());
        b.setCategory(f.getCategory());
        return b;
    }

    public static ProductMasterPojo convertProductFormtoProductMasterPojo(ProductForm f,BrandMasterPojo b){
        ProductMasterPojo p = new ProductMasterPojo();
        p.setName(f.getName());
        p.setMrp(f.getMrp());
        p.setBarcode(StringUtil.generateBarcode());
        p.setBrandCategoryId(b.getId());
        return p;
    }

    public static ProductData convertProductMasterPojotoProductData(ProductMasterPojo p,BrandMasterPojo b){
        ProductData d = new ProductData();
        d.setBrand(b.getBrand());
        d.setCategory(b.getCategory());
        d.setId(p.getId());
        d.setName(p.getName());
        d.setMrp(p.getMrp());
        d.setBarcode(p.getBarcode());
        return d;
    }

    public static ProductMasterPojo convertProductFormtoProductMasterPojoU(ProductForm f,BrandMasterPojo b){
        ProductMasterPojo p = new ProductMasterPojo();
        p.setBrandCategoryId(b.getId());
        p.setName(f.getName());
        p.setMrp(f.getMrp());
        return p;
    }
}
