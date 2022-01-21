package com.increff.pos.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ProductMasterPojo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String barcode;
	@Column(nullable = false)
	private int brandCategoryId;
	private String name;
	private double mrp;
    // todo use non primitive data types
	// todo !null --> (@Column(....))
}
