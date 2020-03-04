package com.example.demo.web.controller;

import com.example.demo.domain.service.purchase.PurchaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@SuppressWarnings("unused")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping(path = "/reserve")
    public boolean reserve(final String userId, final String productId) {
        return purchaseService.reserved(userId, productId);
    }

    @RequestMapping(path = "/cancel")
    public boolean cancelReserve(final String userId) {
        return purchaseService.cancelReserve(userId);
    }

    @RequestMapping(path = "/buy")
    public boolean buyReserve(final String userId) {
        return purchaseService.buy(userId);
    }

}
