package com.increff.pos.service;

import com.increff.pos.dao.ProductDao;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import com.increff.pos.util.StringUtil;
import com.increff.pos.util.normalizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    @Transactional(rollbackFor = ApiException.class)
    public ProductMasterPojo add(ProductMasterPojo p, BrandMasterPojo b) throws ApiException {
        normalizeUtil.normalizaProductMasterPojo(p);
        ProductMasterPojo ex = dao.selectByBarcode(p.getBarcode());
        if(ex == null){
            dao.insert(p);
            return p;
        }else{
            String barcode = StringUtil.generateBarcode();
            ProductMasterPojo newP = new ProductMasterPojo();
            newP.setBarcode(barcode);
            newP.setBrand_category(b.getId());
            newP.setMrp(p.getMrp());
            newP.setName(p.getName());
            return add(newP,b);
        }
    }

    @Transactional
    public ProductMasterPojo get(int id){
        return dao.select(ProductMasterPojo.class,id);
    }

    @Transactional
    public List<ProductMasterPojo> getAll(){
        return dao.selectAll();
    }

    @Transactional
    public ProductMasterPojo getByBarcode(String barcode) throws ApiException {
        barcode = StringUtil.toLowerCase(barcode);
        ProductMasterPojo p = dao.selectByBarcode(barcode);
        if(p == null){
            throw new ApiException("Barcode doesn't exist");
        }else{
            return p;
        }
    }

    @Transactional(rollbackFor = ApiException.class)
    public ProductMasterPojo update(int id,ProductMasterPojo p,BrandMasterPojo b) throws ApiException {
        normalizeUtil.normalizaProductMasterPojo(p);
        ProductMasterPojo newP = check(id);
        newP.setBarcode(p.getBarcode());
        newP.setBrand_category(b.getId());
        newP.setMrp(p.getMrp());
        newP.setName(p.getName());
        dao.update(p);
        return newP;
    }

    @Transactional
    public ProductMasterPojo check(int id) throws ApiException {
        ProductMasterPojo p = dao.select(ProductMasterPojo.class,id);
        if(p == null){
            throw new ApiException("Product doesn't exist - id : " + id);
        }else{
            return p;
        }
    }
}
