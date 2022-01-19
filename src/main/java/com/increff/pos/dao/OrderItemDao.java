package com.increff.pos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.increff.pos.pojo.OrderItemPojo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao extends AbstractDao {

    private static String select_All = "select p from OrderItemPojo p";
    private static String select_By_OrderId = "select p from OrderItemPojo where p:=orderId";
    private static String delete_By_OrderId = "delete from OrderItemPojo p where orderId=:orderId";
    private static String select_By_OrderId_List = "select p from OrderItemPojo p where orderId IN:orderIds";

    // todo https://storage.googleapis.com/www.increff.com/img/favicon.png?v=2
//    @PersistenceContext
//    private EntityManager em;

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

    public int deleteByOrderId(int orderId) {
        Query query = em().createQuery(delete_By_OrderId);
        query.setParameter("orderId", orderId);
        return query.executeUpdate();
    }

    public List<OrderItemPojo> selectByOrderIdList(List<Integer> orderIds){
        TypedQuery<OrderItemPojo> query = getQuery(select_By_OrderId_List,OrderItemPojo.class);
        query.setParameter("orderIds" , orderIds);
        return query.getResultList();
    }

}
