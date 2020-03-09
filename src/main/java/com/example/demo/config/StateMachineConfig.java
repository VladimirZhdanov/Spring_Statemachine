package com.example.demo.config;

import com.example.demo.domain.statemachine.action.*;
import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.guard.HideGuard;
import com.example.demo.domain.statemachine.listener.MeltStateMachineApplicationListener;
import com.example.demo.domain.statemachine.persist.PurchaseStateMachinePersister;
import com.example.demo.domain.statemachine.state.MeltState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

import static com.example.demo.domain.statemachine.event.MeltEvent.*;
import static com.example.demo.domain.statemachine.state.MeltState.*;

/**
 * @author v.zhdanov
 * @version 1.0
 * @since 1.0.0
 */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<MeltState, MeltEvent> {

    @Override
    public void configure(final StateMachineConfigurationConfigurer<MeltState, MeltEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new MeltStateMachineApplicationListener());
    }

    @Override
    public void configure(final StateMachineStateConfigurer<MeltState, MeltEvent> states) throws Exception {
        states
                .withStates()
                .initial(START)
                .end(END)
                .states(EnumSet.allOf(MeltState.class));

    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<MeltState, MeltEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(START)
                .target(MELT)
                .event(CHEMICAL_ANALYZE)
                .action(startAction(), errorAction())

                .and()
                .withInternal()
                .source(MELT)
                .event(PRESSURE_ANALYZE)
                .action(pressureAction(), errorAction())

                .and()
                .withInternal()
                .source(MELT)
                .event(TEMPERATURE_ANALYZE)
                .action(temperatureAction(), errorAction())

                .and()
                .withInternal()
                .source(MELT)
                .event(BODY_INPUT)
                .action(inputAction(), errorAction())



                .and()
                .withExternal()
                .source(MELT)
                .target(CALCULATION)
                .event(CHECK_TO_SEND)
                .guard(hideGuard())
                .action(calculationAction(), errorAction())

                .and()
                .withExternal()
                .source(CALCULATION)
                .target(END)
                .event(MACHINE_OUT)
                //.guard(hideGuard())
                .action(endAction(), errorAction());
    }

    @Bean
    public Action<MeltState, MeltEvent> temperatureAction() {
        return new TemperatureAction();
    }

    @Bean
    public Action<MeltState, MeltEvent> pressureAction() {
        return new PressureAction();
    }

    @Bean
    public Action<MeltState, MeltEvent> inputAction() {
        return new BodyInputAction();
    }

    @Bean
    public Action<MeltState, MeltEvent> startAction() {
        return new StartMelt();
    }

    @Bean
    public Action<MeltState, MeltEvent> calculationAction() {
        return new CalculationAction();
    }

    @Bean
    public Action<MeltState, MeltEvent> endAction() {
        return new EndAction();
    }

    @Bean
    public Action<MeltState, MeltEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Guard<MeltState, MeltEvent> hideGuard() {
        return new HideGuard();
    }

    @Bean
    public StateMachinePersister<MeltState, MeltEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseStateMachinePersister());
    }
}
