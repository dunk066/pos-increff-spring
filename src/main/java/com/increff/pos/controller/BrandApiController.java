package com.increff.pos.controller;

import com.increff.pos.dto.BrandDto;
import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.service.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api
@RestController
@RequestMapping(value = "/api/brand")
public class BrandApiController {

	@Autowired
	private BrandDto brandDto;

	@ApiOperation(value = "Adds a brand-category")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public BrandMasterPojo add(@RequestBody BrandForm form) throws ApiException{
		return brandDto.addBrand(form);
	}

	@ApiOperation(value = "Gets a brand by ID")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public BrandData get(@PathVariable int id) throws ApiException, ApiException {
		return brandDto.getBrandData(id);
	}

	@ApiOperation(value = "Gets list of all brands")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<BrandData> getAll() {
		return brandDto.getAllBrand();
	}

	@ApiOperation(value = "Updates a brand")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public BrandMasterPojo update(@PathVariable int id, @RequestBody BrandForm f) throws ApiException {
		return brandDto.updateBrand(id,f);
	}

}
