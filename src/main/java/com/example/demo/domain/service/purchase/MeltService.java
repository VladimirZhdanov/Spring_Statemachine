package com.example.demo.domain.service.purchase;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public interface MeltService {

    boolean chemicalAnalyze(final String meltId);

    boolean temperatureAnalyze(final String meltId);

    boolean pressureAnalyze(final String meltId);

    boolean bodyInput(final String meltId);

    boolean check(final String meltId);

    boolean endCalculation(final String userId);
}
