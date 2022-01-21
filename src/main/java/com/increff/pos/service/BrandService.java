package com.increff.pos.service;

import com.increff.pos.dao.BrandDao;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.util.NormalizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {

	@Autowired
	private BrandDao dao;

	// todo check on rollbackon
	// readonly --> true
	// remove transaction and make functions private
	@Transactional
	public BrandMasterPojo add(BrandMasterPojo p) throws ApiException {
		NormalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrandCategoryExist(p.getBrand(),p.getCategory());
		dao.insert(p);
		return p;
	}

	@Transactional(readOnly = true)
	public BrandMasterPojo get(int id) {
		return dao.select(BrandMasterPojo.class,id);
	}

	@Transactional
	public List<BrandMasterPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional
	public BrandMasterPojo getByBrandCategory(BrandForm form) throws ApiException {
		NormalizeUtil.normalizeBrandForm(form);
		return getCheckForBrandCategory(form);
	}

	@Transactional
	public BrandMasterPojo update(int id, BrandMasterPojo p) throws ApiException {
		NormalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrandCategoryExist(p.getBrand(),p.getCategory());
		BrandMasterPojo ex = getCheck(id);
		ex.setBrand(p.getBrand());
		ex.setCategory(p.getCategory());
		dao.update(ex);
		return ex;
	}

	@Transactional
	public List<BrandMasterPojo> searchBrandCategoryData(BrandForm form) {
		NormalizeUtil.normalizeBrandForm(form);
		return dao.searchBrandData(form.getBrand(),form.getCategory());
	}

	private BrandMasterPojo getCheck(int id) throws ApiException {
		BrandMasterPojo p = dao.select(BrandMasterPojo.class,id);
		if (p == null) {
			throw new ApiException("Brand and Category with given ID does not exist - id: " + id);
		}
		return p;
	}

	private void getCheckBrandCategoryExist(String brand, String category) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(brand,category);
		if (p != null) {
			throw new ApiException("Brand and Category already exists");
		}
	}

	private BrandMasterPojo getCheckForBrandCategory(BrandForm form) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(form.getBrand(),form.getCategory());
		if (p == null) {
			throw new ApiException("Brand-Category doesn't exist");
		}
		return p;
	}

}
