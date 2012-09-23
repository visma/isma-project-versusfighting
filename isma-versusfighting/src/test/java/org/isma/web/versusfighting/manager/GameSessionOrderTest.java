package org.isma.web.versusfighting.manager;

import junit.framework.TestCase;
import org.isma.web.versusfighting.model.Fighter;
import org.isma.web.versusfighting.model.Player;
import org.isma.web.versusfighting.model.PlayerInstance;
import org.isma.web.versusfighting.model.impl.SuperStreetFighter4ArcadeEditionGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class GameSessionOrderTest extends TestCase {
    public static final Player PLAYER_A = new Player(0, "isma");
    public static final Player PLAYER_B = new Player(1, "skand");
    public static final Player PLAYER_C = new Player(2, "yann");
    public static final Player PLAYER_D = new Player(3, "will");

    public static final Fighter FIGHTER_A = new Fighter("ken", null);
    public static final Fighter FIGHTER_B = new Fighter("ryu", null);
    public static final Fighter FIGHTER_C = new Fighter("toto", null);
    public static final Fighter FIGHTER_D = new Fighter("tata", null);

    private GameSession gameSession;

    @Override
    public void setUp() throws Exception {
        Fighter[] fighters = {FIGHTER_A, FIGHTER_B, FIGHTER_C, FIGHTER_D};
        gameSession = new GameSession();
        gameSession.applySettings(3, 1, new SuperStreetFighter4ArcadeEditionGame());
        gameSession.registerPlayer(PLAYER_A);
        gameSession.registerPlayer(PLAYER_B);
        gameSession.registerPlayer(PLAYER_C);
        gameSession.registerPlayer(PLAYER_D);
        gameSession.registerFighters(PLAYER_A, asList(fighters));
        gameSession.registerFighters(PLAYER_B, asList(fighters));
        gameSession.registerFighters(PLAYER_C, asList(fighters));
        gameSession.registerFighters(PLAYER_D, asList(fighters));
    }

    public void testPlayerOrderP1WinAlways() {
        gameSession.start();
        List<PlayerInstance> playerInstances = gameSession.getPlayerInstances();
        Map<Integer, PlayerInstance> playerOrderMap = new HashMap<Integer, PlayerInstance>();
        playerOrderMap.put(0, playerInstances.get(0));
        playerOrderMap.put(1, playerInstances.get(1));
        playerOrderMap.put(2, playerInstances.get(2));
        playerOrderMap.put(3, playerInstances.get(3));

        //Round 1 [Player1 vs Player2] => P1 Win
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round 2 [Player1 vs Player3] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round 3 [Player1 vs Player4] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(3), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round 4 [Player1 vs Player2] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer2());
    }

    public void testPlayerOrderP1WinThenLose() {
        gameSession.start();
        List<PlayerInstance> playerInstances = gameSession.getPlayerInstances();
        Map<Integer, PlayerInstance> playerOrderMap = new HashMap<Integer, PlayerInstance>();
        playerOrderMap.put(0, playerInstances.get(0));
        playerOrderMap.put(1, playerInstances.get(1));
        playerOrderMap.put(2, playerInstances.get(2));
        playerOrderMap.put(3, playerInstances.get(3));

        //Round 1 [Player1 vs Player2] => P1 Win
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round 2 [Player1 vs Player3] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer2());

        //Round 3 [Player3 vs Player4] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(3), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round 4 [Player3 vs Player2] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer2());
    }
}
