package com.example.demo.domain.service.purchase.impl;

import com.example.demo.domain.service.purchase.PurchaseService;
import com.example.demo.domain.statemachine.event.PurchaseEvent;
import com.example.demo.domain.statemachine.state.PurchaseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import static com.example.demo.domain.statemachine.event.PurchaseEvent.*;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
@Service
@SuppressWarnings("all")
public class DefaultPurchaseService implements PurchaseService {

    private final StateMachinePersister<PurchaseState, PurchaseEvent, String> persister;

    private final StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory;

    @Autowired
    public DefaultPurchaseService(
            StateMachinePersister<PurchaseState, PurchaseEvent, String> persister,
            StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory
    ) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public boolean reserved(final String userId, final String productId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getExtendedState().getVariables().put("PRODUCT_ID", productId);
        stateMachine.sendEvent(RESERVE);
        try {
            persister.persist(stateMachine, userId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean cancelReserve(final String userId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(RESERVE_DECLINE);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean buy(final String userId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(BUY);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
