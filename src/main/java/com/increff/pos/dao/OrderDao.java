package com.increff.pos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.increff.pos.pojo.OrderPojo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AbstractDao {

    private static String select_All = "select p from OrderPojo p";
    private static String search = "select p from OrderPojo p where orderUser like :orderUser";
//    @PersistenceContext
//    private EntityManager em;

    public List<OrderPojo> selectAll() {
        TypedQuery<OrderPojo> query = getQuery(select_All, OrderPojo.class);
        return query.getResultList();
    }

    public List<OrderPojo> searchOrderData(String orderUser) {
        TypedQuery<OrderPojo> query = getQuery(search, OrderPojo.class);
        query.setParameter("orderCreater", orderUser + "%");
        return query.getResultList();
    }


}
