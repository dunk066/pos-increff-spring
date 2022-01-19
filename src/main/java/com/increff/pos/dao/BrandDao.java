package com.increff.pos.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.increff.pos.pojo.BrandMasterPojo;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDao extends AbstractDao {

    // <queries>
    // select by brand and category
    private static String selectByBrandCategory = "select p from BrandMasterPojo p where brand=:brand and category=:category";
    // select all brands
    private static String selectAll = "select p from BrandMasterPojo p";
    // search based on brand and category
    private static String search = "select p from BrandMasterPojo p where brand like :brand and category like :category";


    // <queryFunctions>
    // function to select by brand and category
    public BrandMasterPojo selectByBrandCategory(String brand,String category) {
        TypedQuery<BrandMasterPojo> query = getQuery(selectByBrandCategory, BrandMasterPojo.class);
        query.setParameter("brand", brand);
        query.setParameter("category", category);
        return getSingle(query);
    }

    // function to select all brands
    public List<BrandMasterPojo> selectAll() {
        TypedQuery<BrandMasterPojo> query = getQuery(selectAll, BrandMasterPojo.class);
        return query.getResultList();
    }

    // function to search based on brand and category
    public List<BrandMasterPojo> searchBrandData(String brand, String category) {
        TypedQuery<BrandMasterPojo> query = getQuery(search, BrandMasterPojo.class);
        query.setParameter("brand", brand+"%");
        query.setParameter("category", category+"%");
        return query.getResultList();
    }
}
