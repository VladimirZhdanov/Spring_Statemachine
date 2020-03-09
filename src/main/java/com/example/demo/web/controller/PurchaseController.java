package com.example.demo.web.controller;

import com.example.demo.domain.service.purchase.MeltService;
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

    private final MeltService meltService;

    public PurchaseController(MeltService meltService) {
        this.meltService = meltService;
    }

    @RequestMapping(path = "/chemical")
    public boolean chemical(final String meltId) {
        return meltService.chemicalAnalyze(meltId);
    }

    @RequestMapping(path = "/temperature")
    public boolean temperature(final String meltId) {
        return meltService.temperatureAnalyze(meltId);
    }

    @RequestMapping(path = "/pressure")
    public boolean pressure(final String meltId) {
        return meltService.pressureAnalyze(meltId);
    }

    @RequestMapping(path = "/input")
    public boolean input(final String meltId) {
        return meltService.bodyInput(meltId);
    }

    @RequestMapping(path = "/check")
    public boolean check(final String meltId) {
        return meltService.check(meltId);
    }

    @RequestMapping(path = "/end")
    public boolean end(final String meltId) {
        return meltService.endCalculation(meltId);
    }
}
