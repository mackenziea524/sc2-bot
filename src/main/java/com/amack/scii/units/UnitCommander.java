package com.amack.scii.units;

import com.github.ocraft.s2client.protocol.data.UnitType;
import com.github.ocraft.s2client.protocol.unit.Unit;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UnitCommander implements IdleUnitStrategy
{
	private static final XLogger logger = XLoggerFactory.getXLogger(UnitCommander.class);
	private final Map<UnitType, IdleUnitStrategy> strategyMap = new HashMap<>();

	public UnitCommander register(UnitType type, IdleUnitStrategy strategy)
	{
		this.strategyMap.put(type, strategy);
		return this;
	}

	@Override
	public void command(Unit unit)
	{
		Optional.ofNullable(strategyMap.get(unit.getType())).ifPresentOrElse(strategy -> strategy.command(unit), () -> {
			logger.error("No strategy for unit type '{}'", unit.getType().toString());
		});
	}

}
