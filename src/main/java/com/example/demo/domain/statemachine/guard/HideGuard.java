package com.example.demo.domain.statemachine.guard;

import com.example.demo.domain.statemachine.event.PurchaseEvent;
import com.example.demo.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class HideGuard implements Guard<PurchaseState, PurchaseEvent> {

    @Override
    public boolean evaluate(StateContext<PurchaseState, PurchaseEvent> context) {
        return false;
    }
}
