package com.ineos.management.service;

import com.ineos.management.dto.PaymentInfo;
import com.ineos.management.dto.Purchase;
import com.ineos.management.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
