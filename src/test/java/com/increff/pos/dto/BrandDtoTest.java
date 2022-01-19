package com.increff.pos.dto;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.service.AbstractUnitTest;
import com.increff.pos.service.ApiException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BrandDtoTest extends AbstractUnitTest {
    @Autowired
    private BrandDto brandDto;

    @Test
    public void addBrandTest() throws ApiException {
        // All data should be stored in a small case, trimmed.
        BrandForm f = new BrandForm();
        f.brand = "aLtErNaTe";f.category = "AlTeR";
        BrandMasterPojo p = brandDto.addBrand(f);
        assertEquals("alternate",p.getBrand());
        assertEquals("alter",p.getCategory());
        f.brand = " altErNaTe  ";f.category = " alteR ";
        assertEquals("alternate",p.getBrand());
        assertEquals("alter",p.getCategory());
    }

    @Test
    public void getBrandDataTest() throws ApiException {
        BrandForm f = new BrandForm();
        f.brand = "aLtErNaTe";f.category = "AlTeR";
        BrandMasterPojo p = brandDto.addBrand(f);
        BrandData d = brandDto.getBrandData(p.getId());
        assertEquals("alternate",d.brand);
        assertEquals("alter",d.category);
    }

    @Test
    public void getAllBrandTest() throws ApiException {
        BrandForm f = new BrandForm();
        f.brand = "aLtErNaTe";
        f.category = "AlTeR";
        BrandMasterPojo p = brandDto.addBrand(f);
        f.brand = "bLtErNaTe";f.category = "BlTeR";
        p = brandDto.addBrand(f);
        f.brand = "cLtErNaTe";f.category = "ClTeR";
        p = brandDto.addBrand(f);
        f.brand = "dLtErNaTe";f.category = "DlTeR";
        p = brandDto.addBrand(f);
        f.brand = "eLtErNaTe";f.category = "ElTeR";
        p = brandDto.addBrand(f);
        List<BrandData> reqList = brandDto.getAllBrand();
        assertEquals(5,reqList.size());
    }

    @Test
    public void updateBrandTest() throws ApiException {
        BrandForm f = new BrandForm();
        f.brand = "aLtErNaTe";f.category = "AlTeR";
        BrandMasterPojo p = brandDto.addBrand(f);
        p = brandDto.getByBrandCategory(f);
        f.category = "   BlteR";
        p = brandDto.updateBrand(p.getId(),f);
        assertEquals("alternate",p.getBrand());
        assertEquals("alter",p.getCategory());
    }

    @Test
    public void getByBrandCategoryTest() throws ApiException {
        BrandForm f = new BrandForm();
        f.brand = "aLtErNaTe";f.category = "AlTeR";
        BrandMasterPojo p = brandDto.getByBrandCategory(f);
        assertEquals("alternate",p.getBrand());
        assertEquals("alter",p.getBrand());
    }

    @Test(expected = ApiException.class)
    public void checkFormTest() throws ApiException {
        BrandForm f = new BrandForm();
        f.brand = " altern";f.category = "";
        brandDto.checkForm(f);
    }

}
