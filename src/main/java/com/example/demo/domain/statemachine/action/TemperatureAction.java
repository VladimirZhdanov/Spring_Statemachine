package com.example.demo.domain.statemachine.action;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.listener.StateMachineListener;

import java.util.Map;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class TemperatureAction implements Action<MeltState, MeltEvent> {
    @Override
    public void execute(StateContext<MeltState, MeltEvent> context) {
        System.out.println(context.getMessageHeaders().get("foo"));
        System.out.println(context.getStateMachine().getState());
        context.getStateMachine().getExtendedState().getVariables().put("TemperatureAnalyse", true);
        Map<Object, Object> map = context.getExtendedState().getVariables();
        map.forEach((x, y) -> System.out.println(x));
        System.out.println(context.getStateMachine().getExtendedState().getVariables().size());
        System.out.println("Temperature analyse");
    }
}
