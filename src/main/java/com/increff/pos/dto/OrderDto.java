package com.increff.pos.dto;

import com.increff.pos.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDto {

    @Autowired
    private BrandService brandService;


}
