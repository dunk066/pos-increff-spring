package com.increff.pos.dao;
import com.increff.pos.pojo.InventoryPojo;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class InventoryDao extends AbstractDao {

    private static String select_By_ProductId = "select p from InventoryPojo p where productId=:productId";
    private static String select_All = "select p from InventoryPojo p";

//    @PersistenceContext
//    private EntityManager em;

    public InventoryPojo selectByProductId(int productId) {
        TypedQuery<InventoryPojo> query = getQuery(select_By_ProductId, InventoryPojo.class);
        query.setParameter("productId", productId);
        return getSingle(query);
    }

    public List<InventoryPojo> selectAll() {
        TypedQuery<InventoryPojo> query = getQuery(select_All, InventoryPojo.class);
        return query.getResultList();
    }
}