package com.example.demo.domain.statemachine.guard;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import java.util.Map;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class HideGuard implements Guard<MeltState, MeltEvent> {

    @Override
    public boolean evaluate(StateContext<MeltState, MeltEvent> context) {
        System.out.println(context.getMessageHeaders().get("foo"));
        System.out.println(context.getStateMachine().getState());
        System.out.println(context.getExtendedState().getVariables().size());
        Map<Object, Object> map = context.getExtendedState().getVariables();
        map.forEach((x, y) -> System.out.println(x));
        boolean check =  map.containsKey("PressureAnalyse")
                && map.containsKey("TemperatureAnalyse")
                && map.containsKey("BodyInput")
                && map.containsKey("ChemicalAnalyse");

        System.out.println(check);
        return check;
    }
}
