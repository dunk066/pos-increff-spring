package com.increff.pos.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import lombok.Setter;
import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.increff.pos.dto.OrderDto;
import com.increff.pos.model.BillData;
import com.increff.pos.model.OrderData;
import com.increff.pos.model.OrderItemForm;
import com.increff.pos.model.OrderSearchForm;
import com.increff.pos.service.ApiException;
import com.increff.pos.util.GeneratePDF;
import com.increff.pos.util.GenerateXML;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@Setter
@RequestMapping(value = "/api/order")
public class OrderApiController {
    @Autowired
    private OrderDto orderDto;

    @ApiOperation(value = "Gets a Order")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderData get(@PathVariable int id) throws ApiException {
        return orderDto.get(id);
    }

    @ApiOperation(value = "Updates invoice status")
    @RequestMapping(value = "/invoicedisable/{id}", method = RequestMethod.POST)
    public void updateInvoice(@PathVariable int id) throws ApiException {
        orderDto.updateInvoice(id);
    }

    @ApiOperation(value = "Gets list of all Orders")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<OrderData> getAll() {
        return orderDto.getAll();
    }

    @ApiOperation(value = "Generates invoice")
    @RequestMapping(value = "/invoice",method = RequestMethod.POST)
    public void generateInvoice(@RequestBody OrderItemForm[] orderItemForms, HttpServletResponse response)
            throws ApiException, ParserConfigurationException, TransformerException, FOPException, IOException {
        List<BillData> list = orderDto.generateInvoice(orderItemForms);
        GenerateXML.createXml(list);
        byte[] encodedBytes = GeneratePDF.createPDF();
        GeneratePDF.createResponse(response, encodedBytes);
    }

    @ApiOperation(value = "Adds Order")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@RequestBody OrderItemForm[] orderItemForms, HttpServletResponse response)
            throws ApiException, ParserConfigurationException, TransformerException, FOPException, IOException {
        List<BillData> list = orderDto.createOrder(orderItemForms);
        GenerateXML.createXml(list);
        byte[] encodedBytes = GeneratePDF.createPDF();
        GeneratePDF.createResponse(response, encodedBytes);
    }

    @ApiOperation(value = "Search Orders")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<OrderData> search(@RequestBody OrderSearchForm form) throws ApiException, ParseException {
        return orderDto.searchOrder(form);
    }

    @ApiOperation(value = "Update Order")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody OrderItemForm[] orderItemForms, HttpServletResponse response)
            throws ApiException, ParserConfigurationException, TransformerException, FOPException, IOException {
        List<BillData> billItemList = orderDto.changeOrder(id, orderItemForms);
        GenerateXML.createXml(billItemList);
        byte[] encodedBytes = GeneratePDF.createPDF();
        GeneratePDF.createResponse(response, encodedBytes);
    }

}