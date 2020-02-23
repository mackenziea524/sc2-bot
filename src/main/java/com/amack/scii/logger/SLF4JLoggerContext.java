package com.amack.scii.logger;

import com.github.ocraft.s2client.bot.S2Agent;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public class SLF4JLoggerContext implements LoggerContext
{
	private final Map<String, String> context = new HashMap<>();

	public void update(S2Agent agent)
	{
		context.put("gameLoop", ""+agent.observation().getGameLoop());
		MDC.setContextMap(context);
	}

}
