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
public class ErrorAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(final StateContext<PurchaseState, PurchaseEvent> context) {
        System.out.println("Ошибка при переходе в статус " + context.getTarget().getId());
    }
}
