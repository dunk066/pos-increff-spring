package com.increff.pos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.increff.pos.pojo.BrandMasterPojo;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDao extends AbstractDao {

    private static String select_By_Brand_Category = "select p from BrandMasterPojo p where brand=:brand and category=:category";
    private static String select_All = "select p from BrandMasterPojo p";
    private static String select_Id = "select p from BrandMasterPojo p where id=:id";
    private static String search = "select p from BrandMasterPojo p where brand like :brand and category like :category";

    public BrandMasterPojo selectByBrandCategory(String brand,String category) {
        TypedQuery<BrandMasterPojo> query = getQuery(select_By_Brand_Category, BrandMasterPojo.class);
        query.setParameter("brand", brand);
        query.setParameter("category", category);
        return getSingle(query);
    }

    public BrandMasterPojo selectById(int id) {
        TypedQuery<BrandMasterPojo> query = getQuery(select_Id, BrandMasterPojo.class);
        query.setParameter("id", id);
        return getSingle(query);
    }

    public List<BrandMasterPojo> selectAll() {
        TypedQuery<BrandMasterPojo> query = getQuery(select_All, BrandMasterPojo.class);
        return query.getResultList();
    }

    public List<BrandMasterPojo> searchBrandData(String brand, String category) {
        TypedQuery<BrandMasterPojo> query = getQuery(search, BrandMasterPojo.class);
        query.setParameter("brand", brand+"%");
        query.setParameter("category", category+"%");
        return query.getResultList();
    }
}
