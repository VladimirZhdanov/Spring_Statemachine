package com.example.demo.domain.statemachine.persist;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class PurchaseStateMachinePersister implements StateMachinePersist<MeltState, MeltEvent, String> {

    private final HashMap<String, StateMachineContext<MeltState, MeltEvent>> contexts = new HashMap<>();

    @Override
    public void write(final StateMachineContext<MeltState, MeltEvent> context, final String contextObj) {
        contexts.put(contextObj, context);
    }

    @Override
    public StateMachineContext<MeltState, MeltEvent> read(final String contextObj) {
        return contexts.get(contextObj);
    }
}
