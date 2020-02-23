package com.amack.scii;

import com.amack.scii.logger.LoggerContext;
import com.amack.scii.logger.PerformantLoggerContext;
import com.amack.scii.logger.SLF4JLoggerContext;
import com.amack.scii.units.IdleUnitStrategy;
import com.amack.scii.units.SimpleDroneStrategy;
import com.amack.scii.units.SimpleLarvaStrategy;
import com.amack.scii.units.UnitCommander;
import com.github.ocraft.s2client.bot.S2Agent;
import com.github.ocraft.s2client.bot.gateway.UnitInPool;
import com.github.ocraft.s2client.protocol.data.Units;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class BotAgent extends S2Agent
{
    private static final XLogger logger = XLoggerFactory.getXLogger(BotAgent.class);
    private final IdleUnitStrategy idleUnitStrategy;
    private final LoggerContext loggerContext;

    public BotAgent()
    {
    	this.idleUnitStrategy = new UnitCommander()
                .register(Units.ZERG_LARVA, new SimpleLarvaStrategy(this))
                .register(Units.ZERG_DRONE, new SimpleDroneStrategy(this));
    	this.loggerContext = new PerformantLoggerContext(new SLF4JLoggerContext(), 1000);
    }

    @Override
    public void onGameStart() {
    }

    @Override
    public void onStep() {
        this.loggerContext.update(this);
    }

    @Override
    public void onUnitIdle(UnitInPool unitInPool)
    {
        idleUnitStrategy.command(unitInPool.unit());
    }

}
