package com.increff.pos.util;

import com.increff.pos.model.*;
import com.increff.pos.pojo.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    public static ProductSearchForm convertInventorySearchFormtoProductSearchForm(InventorySearchForm form) {
        ProductSearchForm productSearchForm = new ProductSearchForm();
        productSearchForm.setBarcode(form.getBarcode());
        productSearchForm.setName(form.getName());
        return productSearchForm;
    }

    public static InventoryData convertInventoryPojotoInventoryData(InventoryPojo i,
                                                                    ProductMasterPojo productMasterPojo) {
        InventoryData d = new InventoryData();
        d.id = i.getId();
        d.name = productMasterPojo.getName();
        d.barcode = productMasterPojo.getBarcode();
        d.quantity = i.getQuantity();
        return d;
    }

    public static InventoryPojo convertInventoryFormtoInventoryPojo(InventoryForm f, ProductMasterPojo p) {
        InventoryPojo i = new InventoryPojo();
        i.setProductId(p.getId());
        i.setQuantity(f.getQuantity());
        return i;
    }

    public static OrderItemData convertOrderItemPojotoOrderItemData(OrderItemPojo orderItemPojo,
                                                                    ProductMasterPojo productMasterPojo) {
        OrderItemData d = new OrderItemData();
        d.id = orderItemPojo.getId();
        d.orderId = orderItemPojo.getOrderId();
        d.name = productMasterPojo.getName();
        d.barcode = productMasterPojo.getBarcode();
        d.quantity = orderItemPojo.getQuantity();
        d.sellingPrice = orderItemPojo.getSellingPrice();
        return d;
    }

    public static ProductDetails convertProductDatatoProductDetails(ProductData productData,
                                                                    InventoryPojo inventoryPojo) {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setBarcode(productData.getBarcode());
        productDetails.setBrand(productData.getBrand());
        productDetails.availableQuantity = inventoryPojo.getQuantity();
        productDetails.setCategory(productData.getCategory());
        productDetails.setMrp(productData.getMrp());
        productDetails.setName(productData.getName());
        productDetails.setId(productData.getId());
        return productDetails;
    }

    public static BrandForm convertProductSearchFormtoBrandForm(ProductSearchForm form) {
        BrandForm brandForm = new BrandForm();
        brandForm.setBrand(form.getBrand());
        brandForm.setCategory(form.getCategory());
        return brandForm;
    }

    public static OrderItemPojo convertOrderItemFormToOrderItemPojo(OrderItemForm orderItemForm, OrderPojo orderPojo,
                                                                    ProductMasterPojo productMasterPojo) {
        OrderItemPojo item = new OrderItemPojo();
        item.setOrderId(orderPojo.getId());
        item.setProductId(productMasterPojo.getId());
        item.setQuantity(orderItemForm.quantity);
        item.setSellingPrice(orderItemForm.sellingPrice);
        return item;
    }

    public static String getDateTime() {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date dateobj = new Date();
        String datetime = df.format(dateobj);
        return datetime;
    }

    public static OrderData convertOrderPojotoOrderData(OrderPojo p, List<OrderItemPojo> orderItemPojos) {
        OrderData d = new OrderData();
        d.setId(p.getId());
        d.setDatetime(p.getDatetime());
        double billAmount = 0;
        for (OrderItemPojo orderItemPojo : orderItemPojos) {
            billAmount += orderItemPojo.getQuantity() * orderItemPojo.getSellingPrice();
        }
        d.setBillAmount(billAmount);
        return d;
    }

    public static OrderData convertOrderPojoOrderData(OrderPojo pojo) {
        OrderData d = new OrderData();
        d.setId(pojo.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datetime = String.valueOf(formatter);
        d.setDatetime(datetime);
        return d;
    }

    public static InventoryReportData convertToInventoryReportData(InventoryPojo inventoryPojo,
                                                                   BrandMasterPojo brandMasterPojo) {
        InventoryReportData inventoryReportData = new InventoryReportData();
        inventoryReportData.setBrand(brandMasterPojo.getBrand());
        inventoryReportData.setCategory(brandMasterPojo.getCategory());
        inventoryReportData.setQuantity(inventoryPojo.getQuantity());
        return inventoryReportData;
    }

    public static SalesReportData convertToSalesReportData(OrderItemPojo orderItemPojo,
                                                           BrandMasterPojo brandMasterPojo) {
        SalesReportData salesProductData = new SalesReportData();
        salesProductData.setBrand(brandMasterPojo.getBrand());
        salesProductData.setCategory(brandMasterPojo.getCategory());
        salesProductData.setQuantity(orderItemPojo.getQuantity());
        salesProductData.setRevenue(orderItemPojo.getQuantity() * orderItemPojo.getSellingPrice());
        return salesProductData;
    }

    public static BrandForm convertSalesReportFormtoBrandForm(SalesReportForm salesReportForm) {
        BrandForm brandForm = new BrandForm();
        brandForm.setBrand(salesReportForm.getBrand());
        brandForm.setCategory(salesReportForm.getCategory());
        return brandForm;
    }
}
