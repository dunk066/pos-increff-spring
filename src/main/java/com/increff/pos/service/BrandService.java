package com.increff.pos.service;

import com.increff.pos.dao.BrandDao;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.util.normalizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService {

	@Autowired
	private BrandDao dao;

	@Transactional(rollbackOn = ApiException.class)
	public BrandMasterPojo add(BrandMasterPojo p) throws ApiException {
		normalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrand(p.getBrand(),p.getCategory());
		dao.insert(p);
		return p;
	}

	//how to implement this
	@Transactional(rollbackOn = ApiException.class)
	public BrandMasterPojo get(int id) throws ApiException {
		return getCheck(id);
	}

	@Transactional
	public List<BrandMasterPojo> getAll() {
		return dao.selectAll();
	}

	public BrandMasterPojo getByBrandCategory(BrandForm brandForm) throws ApiException {
		normalizeUtil.normalizeBrandForm(brandForm);
		return getCheckForBrand(brandForm);
	}

	@Transactional(rollbackOn  = ApiException.class)
	public BrandMasterPojo update(int id, BrandMasterPojo p) throws ApiException {
		normalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrand(p.getBrand(),p.getCategory());
		BrandMasterPojo ex = getCheck(id);
		ex.setBrand(p.getBrand());
		ex.setCategory(p.getCategory());
		dao.update(ex);
		return ex;
	}

	@Transactional
	public BrandMasterPojo getCheck(int id) throws ApiException {
		BrandMasterPojo p = dao.selectById(id);
		if (p == null) {
			throw new ApiException("brand and category with given ID does not exit, id: " + id);
		}
		return p;
	}

	@Transactional
	public void getCheckBrand(String brand,String category) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(brand,category);
		if (p == null) {
			throw new ApiException("brand and category already exists");
		}
	}

	@Transactional
	public BrandMasterPojo getCheckForBrand(BrandForm brandForm) throws ApiException {
		BrandMasterPojo brandMasterPojo = dao.selectByBrandCategory(brandForm.brand, brandForm.category);
		if (brandMasterPojo == null) {
			throw new ApiException("Brand-Category dosen't exist");
		}
		return brandMasterPojo;
	}

}
