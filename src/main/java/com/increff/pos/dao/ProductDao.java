package com.increff.pos.dao;

import com.increff.pos.pojo.ProductMasterPojo;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao{

    // <queries>
    // select by barcode
    private static String selectByBarcode = "select p from ProductMasterPojo p where barcode=:barcode";
    // select all
    private static String selectAll = "select p from ProductMasterPojo p";
    // search based on name and barcode
    private static String search = "select p from ProductMasterPojo p where barcode like :barcode";

    // <queryFunctions>
    // function to select by barcode
    public ProductMasterPojo selectByBarcode(String barcode){
        TypedQuery<ProductMasterPojo> query = getQuery(selectByBarcode,ProductMasterPojo.class);
        query.setParameter("barcode",barcode);
        return getSingle(query);
    }

    // function to select all
    public List<ProductMasterPojo> selectAll() {
        TypedQuery<ProductMasterPojo> query = getQuery(selectAll, ProductMasterPojo.class);
        return query.getResultList();
    }

    // function to search based on name and barcode
    public List<ProductMasterPojo> searchProductData(String barcode, String name) {
        TypedQuery<ProductMasterPojo> query = getQuery(search, ProductMasterPojo.class);
        query.setParameter("barcode", barcode);
//        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

}