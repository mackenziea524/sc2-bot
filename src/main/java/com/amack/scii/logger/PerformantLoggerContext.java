package com.amack.scii.logger;

import com.github.ocraft.s2client.bot.S2Agent;

public class PerformantLoggerContext implements LoggerContext
{
	private final LoggerContext context;
	private final int gameLoopUpdateRate;

	public PerformantLoggerContext(LoggerContext context, int gameLoopUpdateRate)
	{
		this.context = context;
		this.gameLoopUpdateRate = gameLoopUpdateRate;
	}

	@Override
	public void update(S2Agent agent)
	{
		if (agent.observation().getGameLoop() % gameLoopUpdateRate == 0)
		{
			context.update(agent);
		}
	}

}
