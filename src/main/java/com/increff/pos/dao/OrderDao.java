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

//    @PersistenceContext
//    private EntityManager em;

    public List<OrderPojo> selectAll() {
        TypedQuery<OrderPojo> query = getQuery(select_All, OrderPojo.class);
        return query.getResultList();
    }

}
