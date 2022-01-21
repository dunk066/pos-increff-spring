package com.increff.pos.dto;

import com.increff.pos.model.BrandForm;
import com.increff.pos.model.ProductData;
import com.increff.pos.model.ProductForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.InventoryPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.service.InventoryService;
import com.increff.pos.service.ProductService;
import com.increff.pos.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductDto {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandDto brandDto;
    @Autowired
    private InventoryService inventoryService;

    @Transactional(rollbackFor = ApiException.class)
    public ProductMasterPojo add(ProductForm f) throws ApiException {
        validateForm(f);
        BrandForm brandForm = ConvertUtil.convertProductFormtoBrandForm(f);
        BrandMasterPojo b = brandDto.getByBrandCategory(brandForm);
        ProductMasterPojo p = ConvertUtil.convertProductFormtoProductMasterPojo(f,b);
        ProductMasterPojo newP = productService.add(p,b);
        InventoryPojo inventoryPojo = new InventoryPojo();
        inventoryPojo.setQuantity(0);
        inventoryPojo.setProductId(p.getId());
        inventoryService.add(inventoryPojo);
        return newP;
    }

    @Transactional
    public ProductData get(int id) throws ApiException {
        ProductMasterPojo p = productService.get(id);
        BrandMasterPojo b = brandService.get(p.getBrandCategoryId());
        return ConvertUtil.convertProductMasterPojotoProductData(p,b);
    }

    @Transactional
    public List<ProductData> getAll(){
        List<ProductMasterPojo> pList = productService.getAll();
        List<ProductData> reqList = new ArrayList<>();
        for(ProductMasterPojo p:pList){
            ProductData d = ConvertUtil.convertProductMasterPojotoProductData(p,brandService.get(p.getBrandCategoryId()));
            reqList.add(d);
        }
        return reqList;
    }

//    @Transactional
//    public ProductMasterPojo getByBarcode(){
//        ProductMasterPojo p ;
//        return p;
//    }

    public ProductMasterPojo update(int id,ProductForm f) throws ApiException {
        validateForm(f);
        BrandForm brandForm = ConvertUtil.convertProductFormtoBrandForm(f);
        BrandMasterPojo b = brandService.getByBrandCategory(brandForm);
        ProductMasterPojo p = ConvertUtil.convertProductFormtoProductMasterPojoU(f,b);
        return productService.update(id,p,b);
    }

    private void validateForm(ProductForm b) throws ApiException {
        if (b.getName() == null || b.getMrp() <= 0) {
            throw new ApiException("Please Enter Name and a positive mrp!");
        }
    }
}
