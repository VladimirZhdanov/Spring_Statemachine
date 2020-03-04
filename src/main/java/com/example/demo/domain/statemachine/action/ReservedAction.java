package com.example.demo.domain.statemachine.action;

import com.example.demo.domain.statemachine.event.PurchaseEvent;
import com.example.demo.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class ReservedAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(final StateContext<PurchaseState, PurchaseEvent> context) {
        final String productId = context.getExtendedState().get("PRODUCT_ID", String.class);
        System.out.println("Товар с номером " + productId + " зарезервирован.");
    }
}
