package com.ineos.management.service;

import com.ineos.management.dao.CustomerRepository;
import com.ineos.management.dto.PaymentInfo;
import com.ineos.management.dto.Purchase;
import com.ineos.management.dto.PurchaseResponse;
import com.ineos.management.entity.Customer;
import com.ineos.management.entity.Order;
import com.ineos.management.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey){

        this.customerRepository = customerRepository;
        // initialize API

        Stripe.apiKey = secretKey;
    }


    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve the order info from dto

        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        // populate order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item-> order.add(item));
        // populate order billing address and shipping address
         order.setBillingAddress(purchase.getBillingAddress());
         order.setShippingAddress(purchase.getShippingAddress());
        // populate customer with order
        Customer customer = purchase.getCustomer();
        String email = customer.getEmail();

        Customer existingCustomer = customerRepository.findByEmail(email);

        if(existingCustomer != null) {
          customer=existingCustomer;
        }
        customer.add(order);
        // save to database
         customerRepository.save(customer);
        // return response

        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency",paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "IneosQ Purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());
        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        // generate random UUID number(version -4)

        return UUID.randomUUID().toString();


    }
}
