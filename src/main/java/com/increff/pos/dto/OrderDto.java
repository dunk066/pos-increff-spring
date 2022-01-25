package com.increff.pos.dto;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.increff.pos.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.increff.pos.model.BillData;
import com.increff.pos.model.InfoData;
import com.increff.pos.model.OrderData;
import com.increff.pos.model.OrderItemData;
import com.increff.pos.model.OrderItemForm;
import com.increff.pos.model.OrderSearchForm;
import com.increff.pos.pojo.InventoryPojo;
import com.increff.pos.pojo.OrderItemPojo;
import com.increff.pos.pojo.OrderPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.InventoryService;
import com.increff.pos.service.OrderItemService;
import com.increff.pos.service.OrderService;
import com.increff.pos.service.ProductService;

@Component
public class OrderDto {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private OrderItemDto orderItemDto;
    @Autowired
    private InfoData info;

    @Transactional(rollbackFor = ApiException.class)
    public List<BillData> createOrder(OrderItemForm[] orderItemForms) throws ApiException {
        List<OrderItemForm> orderItems = new LinkedList<OrderItemForm>(Arrays.asList(orderItemForms));
        OrderPojo orderPojo = addOrder(orderItems);
        List<OrderItemPojo> list = orderItems.stream().map(o -> {
            try {
                return ConvertUtil.convertOrderItemFormToOrderItemPojo(o,
                        orderPojo, productService.getByBarcode(o.barcode));
            } catch (ApiException e) {
                e.printStackTrace();
                OrderItemPojo p = new OrderItemPojo();
                return p;
            }
        }).collect(Collectors.toList());
        orderService.updateInventory(list);
        orderItemService.add(list);
        return orderService.getBillData(list);
    }

    public OrderPojo addOrder(List<OrderItemForm> orderItems) throws ApiException {
        orderService.checkAvailabilityInventory(orderItems);
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setDatetime(ConvertUtil.getDateTime());
        orderService.add(orderPojo);
        return orderPojo;
    }

    @Transactional(rollbackFor = ApiException.class)
    public List<BillData> changeOrder(int id, OrderItemForm[] orderItemForms) throws ApiException {
        List<OrderItemData> orderItemDataList = orderItemDto.get(id);
        addInInventory(orderItemDataList);
        List<OrderItemForm> orderItems = new LinkedList<OrderItemForm>(Arrays.asList(orderItemForms));
        OrderPojo orderPojo = updateOrder(id, orderItems);
        List<OrderItemPojo> list = orderItems.stream().map(o -> {
            try {
                return ConvertUtil.convertOrderItemFormToOrderItemPojo(o,
                        orderPojo, productService.getByBarcode(o.barcode));
            } catch (ApiException e) {
                e.printStackTrace();
                OrderItemPojo p = new OrderItemPojo();
                return p;
            }
        }).collect(Collectors.toList());
        orderService.updateInventory(list);
        orderItemService.deleteByOrderId(id);
        orderItemService.add(list);
        return orderService.getBillData(list);
    }

    public OrderPojo updateOrder(int id, List<OrderItemForm> orderItems) throws ApiException {
        orderService.checkAvailabilityInventory(orderItems);
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setDatetime(ConvertUtil.getDateTime());
        orderService.update(id, orderPojo);
        orderPojo.setId(id);
        return orderPojo;
    }

    public void addInInventory(List<OrderItemData> orderItemDataList) throws ApiException {
        for (OrderItemData orderItemData : orderItemDataList) {
            ProductMasterPojo productMasterPojo = productService.getByBarcode(orderItemData.barcode);
            InventoryPojo inventoryPojo = inventoryService.getByProductId(productMasterPojo);
            InventoryPojo inventoryPojoFinal = new InventoryPojo();
            inventoryPojoFinal.setQuantity(orderItemData.quantity + inventoryPojo.getQuantity());
            inventoryService.update(inventoryPojo.getId(), inventoryPojoFinal);
        }
    }

    public OrderData get(int id) throws ApiException {
        OrderPojo orderPojo = orderService.get(id);
        return ConvertUtil.convertOrderPojotoOrderData(orderPojo, orderItemService.getByOrderId(orderPojo.getId()));
    }

    public List<OrderData> getAll() {
        List<OrderPojo> list = orderService.getAll();
        // map OrderPojo to OrderData
        return list.stream()
                .map(o -> {
                    try {
                        return ConvertUtil.convertOrderPojotoOrderData(o, orderItemService.getByOrderId(o.getId()));
                    } catch (ApiException e) {
                        e.printStackTrace();
                        OrderData p = new OrderData();
                        return p;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<OrderData> searchOrder(OrderSearchForm form) throws ApiException, ParseException {
        List<OrderPojo> orderPojo = orderService.searchOrder(form);
        // create list by date range
        if (!form.getStartDate().isEmpty() && !form.getEndDate().isEmpty()) {
            orderPojo = orderService.getListSearch(orderPojo, form.getStartDate(), form.getEndDate());
        }
        if (form.getId() == 0) {
            return orderPojo.stream()
                    .map(o -> {
                        try {
                            return ConvertUtil.convertOrderPojotoOrderData(o, orderItemService.getByOrderId(o.getId()));
                        } catch (ApiException e) {
                            e.printStackTrace();
                            OrderData p = new OrderData();
                            return p;
                        }
                    })
                    .collect(Collectors.toList());
        }
        // filter using orderId
        orderPojo = orderPojo.stream().filter(o -> (form.getId() == o.getId())).collect(Collectors.toList());
        // map OrderPojo to OrderData
        return orderPojo.stream()
                .map(o -> {
                    try {
                        return ConvertUtil.convertOrderPojotoOrderData(o, orderItemService.getByOrderId(o.getId()));
                    } catch (ApiException e) {
                        e.printStackTrace();
                        OrderData p = new OrderData();
                        return p;
                    }
                })
                .collect(Collectors.toList());
    }

}