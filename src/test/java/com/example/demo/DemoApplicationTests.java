package com.example.demo;

import com.example.demo.domain.statemachine.event.MeltEvent;
import com.example.demo.domain.statemachine.state.MeltState;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StateMachineFactory<MeltState, MeltEvent> factory;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWhenReservedCancel() throws Exception {
		StateMachine<MeltState, MeltEvent> machine = factory.getStateMachine();
		StateMachineTestPlan<MeltState, MeltEvent> plan =
				StateMachineTestPlanBuilder.<MeltState, MeltEvent>builder()
						.defaultAwaitTime(2)
						.stateMachine(machine)
						.step()
						.expectStates(NEW)
						.expectStateChanged(0)
						.and()
						.step()
						.sendEvent(RESERVE)
						.expectState(RESERVED)
						.expectStateChanged(1)
						.and()
						.step()
						.sendEvent(RESERVE_DECLINE)
						.expectState(CANCEL_RESERVED)
						.expectStateChanged(1)
						.and()
						.build();
		plan.test();
	}

	@Test
	public void testWhenPurchaseComplete() throws Exception {
		StateMachine<MeltState, MeltEvent> machine = factory.getStateMachine();
		StateMachineTestPlan<MeltState, MeltEvent> plan =
				StateMachineTestPlanBuilder.<MeltState, MeltEvent>builder()
						.defaultAwaitTime(2)
						.stateMachine(machine)
						.step()
						.expectStates(NEW)
						.expectStateChanged(0)
						.and()
						.step()
						.sendEvent(RESERVE)
						.expectState(RESERVED)
						.expectStateChanged(1)
						.and()
						.step()
						.sendEvent(BUY)
						.expectState(PURCHASE_COMPLETE)
						.expectStateChanged(1)
						.and()
						.build();
		plan.test();
	}

}
