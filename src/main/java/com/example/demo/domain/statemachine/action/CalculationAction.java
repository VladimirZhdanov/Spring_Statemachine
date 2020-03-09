package com.example.demo.domain.statemachine.action;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class CalculationAction implements Action<MeltState, MeltEvent> {
    @Override
    public void execute(StateContext<MeltState, MeltEvent> context) {
        //final String productId = context.getExtendedState().get("PRODUCT_ID", String.class);
        System.out.println("Calculation is started");
    }
}
