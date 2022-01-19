package com.increff.pos.dao;

import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao{
    private static String select_By_Barcode = "select p from ProductMasterPojo p where barcode=:barcode";
    private static String select_All = "select p from ProductMasterPojo p";
    private static String search = "select p from ProductMasterPojo p where name like :name and barcode like :barcode";

//    @PersistenceContext
//    private EntityManager em;

    public ProductMasterPojo selectByBarcode(String barcode){
        TypedQuery<ProductMasterPojo> query = getQuery(select_By_Barcode,ProductMasterPojo.class);
        query.setParameter("barcode",barcode);
        return getSingle(query);
    }

    public List<ProductMasterPojo> selectAll() {
        TypedQuery<ProductMasterPojo> query = getQuery(select_All, ProductMasterPojo.class);
        return query.getResultList();
    }

    public List<ProductMasterPojo> searchProductData(String barcode, String name) {
        TypedQuery<ProductMasterPojo> query = getQuery(search, ProductMasterPojo.class);
        query.setParameter("barcode", barcode + "%");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

}