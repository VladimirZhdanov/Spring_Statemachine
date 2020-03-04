package com.example.demo.domain.statemachine.persist;

import com.example.demo.domain.statemachine.event.PurchaseEvent;
import com.example.demo.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class PurchaseStateMachinePersister implements StateMachinePersist<PurchaseState, PurchaseEvent, String> {

    private final HashMap<String, StateMachineContext<PurchaseState, PurchaseEvent>> contexts = new HashMap<>();

    @Override
    public void write(final StateMachineContext<PurchaseState, PurchaseEvent> context, final String contextObj) {
        contexts.put(contextObj, context);
    }

    @Override
    public StateMachineContext<PurchaseState, PurchaseEvent> read(final String contextObj) {
        return contexts.get(contextObj);
    }
}
