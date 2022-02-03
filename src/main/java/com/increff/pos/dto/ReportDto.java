package com.increff.pos.dto;

import com.increff.pos.model.*;
import com.increff.pos.pojo.*;
import com.increff.pos.service.*;
import com.increff.pos.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportDto {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;

    public List<BrandForm> getBrandReport(BrandForm form){
        List<BrandMasterPojo> list = brandService.searchBrandCategoryData(form);
        List<BrandForm> reqList = new ArrayList<BrandForm>();
        for(BrandMasterPojo p:list){
            reqList.add(ConvertUtil.convertBrandMastePojotoBrandForm(p));
        }
        return reqList;
    }

    public List<InventoryReportData> getInventoryReport(BrandForm brandForm) throws ApiException {
        List<BrandMasterPojo> brandMasterPojoList = brandService.searchBrandCategoryData(brandForm);
        List<Integer> brandIds = brandMasterPojoList.stream().map(o -> o.getId()).collect(Collectors.toList());
        List<ProductMasterPojo> list = productService.getAll();
        // filter using brandId list
        list = list.stream().filter(o -> (brandIds.contains(o.getBrandCategoryId()))).collect(Collectors.toList());
        // map to product id list
        List<Integer> productIds = list.stream().map(o -> o.getId()).collect(Collectors.toList());
        // filter using product id list
        List<InventoryPojo> inventoryPojoList = inventoryService.getAll().stream()
                .filter(o -> (productIds.contains(o.getProductId()))).collect(Collectors.toList());
        // map to inventory report data
        List<InventoryReportData> list2 = inventoryPojoList.stream()
                .map(o -> ConvertUtil.convertToInventoryReportData(o,
                        brandService.get(productService.get(o.getProductId()).getBrandCategoryId())))
                .collect(Collectors.toList());
        // Group list of InventoryReportData brand and category wise
        return reportService.groupDataForInventoryReport(list2);
    }

    public List<SalesReportData> getSalesReport(SalesReportForm salesReportForm) throws ParseException, ApiException {
        List<Integer> orderIds = getOrderIds(salesReportForm);
        BrandForm brandForm = ConvertUtil.convertSalesReportFormtoBrandForm(salesReportForm);
        List<BrandMasterPojo> brandMasterPojoList = brandService.searchBrandCategoryData(brandForm);
        List<Integer> brandIds = brandMasterPojoList.stream().map(o -> o.getId()).collect(Collectors.toList());
        // filter using brandId list and map to product id list
        List<Integer> productIds = productService.getAll().stream()
                .filter(o -> (brandIds.contains(o.getBrandCategoryId()))).map(o -> o.getId())
                .collect(Collectors.toList());
        // filter using product and order id list
        List<OrderItemPojo> listOfOrderItemPojos = orderItemService.getAll().stream()
                .filter(o -> (productIds.contains(o.getProductId()) && orderIds.contains(o.getOrderId())))
                .collect(Collectors.toList());
        // map to sales report data
        List<SalesReportData> salesReportData = listOfOrderItemPojos.stream()
                .map(o -> ConvertUtil.convertToSalesReportData(o,
                        brandService.get(productService.get(o.getProductId()).getBrandCategoryId())))
                .collect(Collectors.toList());

        return reportService.groupSalesReportDataCategoryWise(salesReportData);
    }

    public List<Integer> getOrderIds(SalesReportForm salesReportForm) throws ParseException, ApiException {
        List<OrderPojo> orderPojo = orderService.getAll();
        // Get list of order ids
        if (salesReportForm.startdate.isEmpty() && salesReportForm.enddate.isEmpty()) {
            return orderPojo.stream().map(o -> o.getId()).collect(Collectors.toList());
        }
        return reportService.getOrderIdList(orderPojo, salesReportForm.startdate, salesReportForm.enddate);
    }
}
