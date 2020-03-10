package com.example.demo.domain.service.purchase.impl;

import com.example.demo.domain.service.purchase.MeltService;
import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import static com.example.demo.domain.statemachine.event.MeltEvent.*;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
@Service
@SuppressWarnings("all")
public class DefaultMeltService implements MeltService {

    private final StateMachinePersister<MeltState, MeltEvent, String> persister;

    private final StateMachineFactory<MeltState, MeltEvent> stateMachineFactory;

    @Autowired
    public DefaultMeltService(
            StateMachinePersister<MeltState, MeltEvent, String> persister,
            StateMachineFactory<MeltState, MeltEvent> stateMachineFactory
    ) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }

    public boolean chemicalAnalyze(final String meltId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();
        //stateMachine.getExtendedState().getVariables().put("PRODUCT_ID", productId);
        stateMachine.sendEvent(CHEMICAL_ANALYZE);
        try {
            persister.persist(stateMachine, meltId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean temperatureAnalyze(final String meltId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, meltId);

            Message<MeltEvent> message = MessageBuilder
                    .withPayload(TEMPERATURE_ANALYZE)
                    .setHeader("foo", "bar")
                    .build();
            stateMachine.sendEvent(message);
            persister.persist(stateMachine, meltId);

            //stateMachine.sendEvent(TEMPERATURE_ANALYZE);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean pressureAnalyze(final String meltId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, meltId);
            stateMachine.sendEvent(PRESSURE_ANALYZE);
            persister.persist(stateMachine, meltId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean bodyInput(final String meltId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, meltId);
            stateMachine.sendEvent(BODY_INPUT);
            persister.persist(stateMachine, meltId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean check(String meltId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, meltId);
            stateMachine.sendEvent(CHECK_TO_SEND);
            persister.persist(stateMachine, meltId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean endCalculation(final String userId) {
        final StateMachine<MeltState, MeltEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(MACHINE_OUT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
