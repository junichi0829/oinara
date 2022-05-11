package com.oinara.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격

    private int count; //주문 수량

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}