package com.increff.pos.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class OrderItemPojo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private double sellingPrice;

}
