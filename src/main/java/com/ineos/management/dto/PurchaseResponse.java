package com.ineos.management.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {
    @NonNull
    private  String orderTrackingNumber;


}
