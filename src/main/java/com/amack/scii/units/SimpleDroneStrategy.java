package com.amack.scii.units;

import com.github.ocraft.s2client.bot.S2Agent;
import com.github.ocraft.s2client.protocol.data.Abilities;
import com.github.ocraft.s2client.protocol.unit.Unit;

public class SimpleDroneStrategy implements IdleUnitStrategy
{
	private final S2Agent agent;

	public SimpleDroneStrategy(S2Agent agent)
	{
		this.agent = agent;
	}

	@Override
	public void command(Unit unit)
	{
		agent.actions().unitCommand(unit, Abilities.HARVEST_GATHER, false);
	}

}
