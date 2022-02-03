package com.increff.pos.controller;

import com.increff.pos.dto.ReportDto;
import com.increff.pos.model.*;
import com.increff.pos.service.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Api
@RestController
@RequestMapping(value = "/api")
public class ReportApiController {
    @Autowired
    private ReportDto reportDto;

    @ApiOperation(value = "Gets Brand report")
    @RequestMapping(value = "/brand-report",method = RequestMethod.POST)
    public List<BrandForm> searchBrandReport(@RequestBody BrandForm form){
        return reportDto.getBrandReport(form);
    }

    @ApiOperation(value = "Gets Inventory Report")
    @RequestMapping(value = "/inventory-report", method = RequestMethod.POST)
    public List<InventoryReportData> searchInventoryReport(@RequestBody BrandForm brandForm) throws ApiException {
        return reportDto.getInventoryReport(brandForm);
    }

    @ApiOperation(value = "Gets Sales Report")
    @RequestMapping(value = "/sales-report", method = RequestMethod.POST)
    public List<SalesReportData> getSalesReport(@RequestBody SalesReportForm salesReportForm)
            throws ParseException, ApiException {
        return reportDto.getSalesReport(salesReportForm);
    }
}
