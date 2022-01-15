package com.increff.pos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.increff.pos.pojo.OrderItemPojo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao extends AbstractDao {

    private static String select_All = "select p from OrderItemPojo p";
    private static String select_By_OrderId = "select p from OrderItemPojo where p:=orderId";

    @PersistenceContext
    private EntityManager em;

    public List<OrderItemPojo> selectByOrderId(int orderId){
        TypedQuery<OrderItemPojo> query = getQuery(select_By_OrderId,OrderItemPojo.class);
        query.setParameter("orderId",orderId);
//        return getSingle(query);
        return query.getResultList();
    }

    public List<OrderItemPojo> selectAll() {
        TypedQuery<OrderItemPojo> query = getQuery(select_All, OrderItemPojo.class);
        return query.getResultList();
    }

}
