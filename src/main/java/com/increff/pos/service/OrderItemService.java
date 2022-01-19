package com.increff.pos.service;

import com.increff.pos.dao.OrderItemDao;
import com.increff.pos.pojo.OrderItemPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemDao dao;

    @Transactional
    public void add(List<OrderItemPojo> l) {
        for(OrderItemPojo p : l) {
            dao.insert(p);
        }
    }

    @Transactional
    public List<OrderItemPojo> getByOrderId(int orderId) throws ApiException {
        return check(orderId);
    }

    @Transactional
    public List<OrderItemPojo> getList(List<Integer> orderIds) {
        return dao.selectByOrderIdList(orderIds);
    }

    @Transactional
    public List<OrderItemPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional
    public void deleteByOrderId(int orderId) {
        dao.deleteByOrderId(orderId);
    }

    @Transactional
    public List<OrderItemPojo> check(int orderId) throws ApiException {
        List<OrderItemPojo> newP = dao.selectByOrderId(orderId);
        if (newP.isEmpty()) {
            throw new ApiException("Order Items don't exist - orderId : " + orderId);
        }
        return newP;
    }
}
