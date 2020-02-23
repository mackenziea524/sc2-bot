package com.amack.scii;

import com.github.ocraft.s2client.bot.S2Coordinator;
import com.github.ocraft.s2client.protocol.game.BattlenetMap;
import com.github.ocraft.s2client.protocol.game.Difficulty;
import com.github.ocraft.s2client.protocol.game.Race;

public class BotMain
{

    public static void main(String[] args)
    {
        args = new String[]{"-e", "E:/Games/Blizzard/StarCraft II/Versions/Base78285/SC2_x64.exe"};
        BotAgent botAgent = new BotAgent();
        S2Coordinator coordinator = S2Coordinator.setup()
                .loadSettings(args)
                .setParticipants(
                        S2Coordinator.createParticipant(Race.ZERG, botAgent),
                        S2Coordinator.createComputer(Race.TERRAN, Difficulty.VERY_EASY))
                .launchStarcraft()
                .startGame(BattlenetMap.of("Cloud Kingdom LE"));

        while (coordinator.update())
        {
        }

        coordinator.quit();
    }

}
