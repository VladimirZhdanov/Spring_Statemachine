package com.example.demo.domain.statemachine.listener;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class MeltStateMachineApplicationListener implements StateMachineListener<MeltState, MeltEvent> {
    @Override
    public void stateChanged(final State<MeltState, MeltEvent> from, final State<MeltState, MeltEvent> to) {
        if (from.getId() != null) {
            System.out.println("Переход из статуса " + from.getId() + " в статус " + to.getId());
        }
    }

    @Override
    public void stateEntered(final State<MeltState, MeltEvent> state) {

    }

    @Override
    public void stateExited(final State<MeltState, MeltEvent> state) {

    }

    @Override
    public void eventNotAccepted(final Message<MeltEvent> event) {
        System.out.println("Евент не принят " + event);
    }

    @Override
    public void transition(final Transition<MeltState, MeltEvent> transition) {

    }

    @Override
    public void transitionStarted(final Transition<MeltState, MeltEvent> transition) {

    }

    @Override
    public void transitionEnded(final Transition<MeltState, MeltEvent> transition) {

    }

    @Override
    public void stateMachineStarted(final StateMachine<MeltState, MeltEvent> stateMachine) {
        System.out.println("Machine started");
    }

    @Override
    public void stateMachineStopped(final StateMachine<MeltState, MeltEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(final StateMachine<MeltState, MeltEvent> stateMachine, Exception exception) {
    }

    @Override
    public void extendedStateChanged(final Object key, final Object value) {

    }

    @Override
    public void stateContext(final StateContext<MeltState, MeltEvent> stateContext) {

    }
}
