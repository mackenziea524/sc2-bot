package com.amack.scii.units;

import com.github.ocraft.s2client.bot.S2Agent;
import com.github.ocraft.s2client.bot.gateway.UnitInPool;
import com.github.ocraft.s2client.protocol.data.Abilities;
import com.github.ocraft.s2client.protocol.data.Units;
import com.github.ocraft.s2client.protocol.unit.Alliance;
import com.github.ocraft.s2client.protocol.unit.Unit;

import java.util.List;

public class SimpleLarvaStrategy implements IdleUnitStrategy
{
	private final S2Agent agent;

	public SimpleLarvaStrategy(S2Agent agent)
	{
		this.agent = agent;
	}

	@Override
	public void command(Unit unit)
	{
		// Get the amount of larvae creating overlords
		List<UnitInPool> overlordLarvae = agent.observation().getUnits(Alliance.SELF, unitInPool ->
			unitInPool.unit().getOrders().stream().anyMatch(order -> Abilities.TRAIN_OVERLORD.equals(order.getAbility()))
		);

		// Current amount of drones
		List<UnitInPool> drones = agent.observation().getUnits(Alliance.SELF, unitInPool ->
				Units.ZERG_DRONE.equals(unitInPool.unit().getType()) || unitInPool.unit().getOrders().stream().anyMatch(order -> Abilities.TRAIN_DRONE.equals(order.getAbility()))
		);

		if (agent.observation().getFoodUsed() >= agent.observation().getFoodCap() - 3 && overlordLarvae.isEmpty())
		{
			agent.actions().unitCommand(unit, Abilities.TRAIN_OVERLORD, false);
		}
		else if (drones.size() < 20)
		{
			agent.actions().unitCommand(unit, Abilities.TRAIN_DRONE, false);
		}
		else
		{
			agent.actions().unitCommand(unit, Abilities.TRAIN_ZERGLING, false);
		}
	}

}
