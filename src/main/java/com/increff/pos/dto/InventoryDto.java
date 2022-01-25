package com.increff.pos.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.increff.pos.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.increff.pos.model.InventoryData;
import com.increff.pos.model.InventoryForm;
import com.increff.pos.model.InventorySearchForm;
import com.increff.pos.model.ProductSearchForm;
import com.increff.pos.pojo.InventoryPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.InventoryService;
import com.increff.pos.service.ProductService;

@Component
public class InventoryDto {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;

    public InventoryPojo addInventory(InventoryForm form) throws ApiException {
        validateData(form);
        ProductMasterPojo productMasterPojo = productService.getByBarcode(form.getBarcode());
        InventoryPojo inventoryPojo = inventoryService.getByProductId(productMasterPojo);
        inventoryPojo.setQuantity(form.quantity + inventoryPojo.getQuantity());
        return inventoryService.update(inventoryPojo.getId(), inventoryPojo);
    }

    public List<InventoryData> searchInventory(InventorySearchForm form) throws ApiException {
        ProductSearchForm productSearchForm = ConvertUtil.convertInventorySearchFormtoProductSearchForm(form);
        List<ProductMasterPojo> productMasterPojoList = productService.searchProductData(productSearchForm);
        List<Integer> productIds = productMasterPojoList.stream().map(o -> o.getId()).collect(Collectors.toList());
        // filter according to product id list
        List<InventoryPojo> list = inventoryService.getAll().stream()
                .filter(o -> (productIds.contains(o.getProductId()))).collect(Collectors.toList());
        // map InventoryPojo to InventoryData
        return list.stream()
                .map(o -> ConvertUtil.convertInventoryPojotoInventoryData(o, productService.get(o.getProductId())))
                .collect(Collectors.toList());
    }

    public InventoryData getInventoryData(int id) throws ApiException {
        InventoryPojo inventoryPojo = inventoryService.get(id);
        ProductMasterPojo productMasterPojo = productService.get(inventoryPojo.getProductId());
        return ConvertUtil.convertInventoryPojotoInventoryData(inventoryPojo, productMasterPojo);
    }

    public InventoryPojo updateInventory(int id, InventoryForm form) throws ApiException {
        validateData(form);
        // get product
        ProductMasterPojo productMasterPojo = productService.getByBarcode(form.barcode);
        InventoryPojo inventoryPojo = ConvertUtil.convertInventoryFormtoInventoryPojo(form, productMasterPojo);
        return inventoryService.update(id, inventoryPojo);
    }

    public List<InventoryData> getAllInventory() {
        List<InventoryPojo> list = inventoryService.getAll();
        // map InventoryPojo to InventoryData
        return list.stream()
                .map(o -> ConvertUtil.convertInventoryPojotoInventoryData(o, productService.get(o.getProductId())))
                .collect(Collectors.toList());
    }

    public void validateData(InventoryForm inventoryForm) throws ApiException {
        if (inventoryForm.quantity < 0) {
            throw new ApiException("Quantity can not be negative for product : " + inventoryForm.barcode + " !!");
        }
    }

}