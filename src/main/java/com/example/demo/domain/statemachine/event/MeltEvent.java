package com.example.demo.domain.statemachine.event;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public enum MeltEvent {
    CHEMICAL_ANALYZE, TEMPERATURE_ANALYZE, PRESSURE_ANALYZE, BODY_INPUT, MACHINE_OUT,
    CHECK_TO_SEND
}
