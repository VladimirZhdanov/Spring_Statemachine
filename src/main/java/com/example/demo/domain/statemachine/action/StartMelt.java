package com.example.demo.domain.statemachine.action;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
public class StartMelt implements Action<MeltState, MeltEvent> {
    @Override
    public void execute(StateContext<MeltState, MeltEvent> context) {
        System.out.println(context.getStateMachine().getState());
        context.getExtendedState().getVariables().put("ChemicalAnalyse", true);
        System.out.println("Melt is started");
    }
}
