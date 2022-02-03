package com.increff.pos.service;

import com.increff.pos.dto.BrandDto;
import com.increff.pos.dto.InventoryDto;
import com.increff.pos.dto.ProductDto;
import com.increff.pos.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InventoryDtoTest extends AbstractUnitTest {
    @Autowired
    private InventoryDto inventoryDto;

    @Autowired
    private BrandDto brandDto;

    @Autowired
    private ProductDto productDto;

//    @Test
//    public void testAddInventory() throws ApiException {
//        ProductData productData = getProductData();
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto(productData.getBarcode(), 20);
//        inventoryDto.addInventory(inventoryForm);
//        InventorySearchForm inventorySearchForm = TestUtil.getInventorySearchFormDto(productData.getBarcode(), "");
//        List<InventoryData> inventoryDatas = inventoryDto.searchInventory(inventorySearchForm);
//        assertEquals(20, inventoryDatas.get(0).quantity);
//    }
//
//    @Test
//    public void testSearchInventory() throws ApiException {
//        // add
//        ProductData productData = getProductData();
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto(productData.getBarcode(), 20);
//        inventoryDto.addInventory(inventoryForm);
//        InventorySearchForm inventorySearchForm = TestUtil.getInventorySearchFormDto(productData.getBarcode(), "");
//        List<InventoryData> inventoryDatas = inventoryDto.searchInventory(inventorySearchForm);
//        assertEquals("munch", inventoryDatas.get(0).name);
//        assertEquals(20, inventoryDatas.get(0).quantity);
//    }
//
//    @Test
//    public void testGetInventoryData() throws ApiException {
//        // add
//        ProductData productData = getProductData();
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto(productData.getBarcode(), 20);
//        inventoryDto.addInventory(inventoryForm);
//        // search
//        InventorySearchForm inventorySearchForm = TestUtil.getInventorySearchFormDto(productData.getBarcode(), "");
//        List<InventoryData> inventoryDatas = inventoryDto.searchInventory(inventorySearchForm);
//        // get data
//        InventoryData inventoryData = inventoryDto.getInventoryData(inventoryDatas.get(0).id);
//        // test
//        assertEquals("munch", inventoryData.name);
//        assertEquals(20, inventoryData.quantity);
//    }
//
//    @Test
//    public void testUpdateInventory() throws ApiException {
//        // add
//        ProductData productData = getProductData();
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto(productData.getBarcode(), 20);
//        inventoryDto.addInventory(inventoryForm);
//        // search
//        InventorySearchForm inventorySearchForm = TestUtil.getInventorySearchFormDto(productData.getBarcode(), "");
//        List<InventoryData> inventoryDatas = inventoryDto.searchInventory(inventorySearchForm);
//        inventoryForm = TestUtil.getInventoryFormDto(inventoryDatas.get(0).barcode, 30);
//        // update
//        inventoryDto.updateInventory(inventoryDatas.get(0).id, inventoryForm);
//        InventoryData inventoryData = inventoryDto.getInventoryData(inventoryDatas.get(0).id);
//        // test updated data
//        assertEquals(30, inventoryData.quantity);
//    }
//
//    @Test
//    public void testGetAllInventory() throws ApiException {
//        // add
//        ProductData productData = getProductData();
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto(productData.getBarcode(), 20);
//        inventoryDto.addInventory(inventoryForm);
//        // get all data
//        List<InventoryData> inventoryDatas = inventoryDto.getAllInventory();
//        // test
//        assertEquals(1, inventoryDatas.size());
//    }
//
//    @Test(expected = ApiException.class)
//    public void testValidateData() throws ApiException {
//        InventoryForm inventoryForm = TestUtil.getInventoryFormDto("barcode", 10);
//        // validate
//        inventoryDto.validateData(inventoryForm);
//        inventoryForm = TestUtil.getInventoryFormDto("barcode", -5);
//        // throws exception
//        inventoryDto.validateData(inventoryForm);
//    }
//
//    private ProductData getProductData() throws ApiException {
//        BrandForm brandForm = TestUtil.getBrandFormDto("     nestLE        ", "DairY ");
//        brandDto.addBrand(brandForm);
//        ProductForm productForm = TestUtil.getProductFormDto("nestle", "dairy", "munch", 10.50);
//        productDto.add(productForm);
//        ProductSearchForm productSearchForm = TestUtil.getProductSearchFormDto("", "nestle", "", "");
//        List<ProductData> productDatas = productDto.searchProduct(productSearchForm);
//        return productDatas.get(0);
//    }
}