package com.ineos.management.dto;

import com.ineos.management.entity.Address;
import com.ineos.management.entity.Customer;
import com.ineos.management.entity.Order;
import com.ineos.management.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;


}
