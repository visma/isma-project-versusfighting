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

public class GameSessionEliminationTest extends TestCase {
    public static final Player PLAYER_A = new Player(0, "isma");
    public static final Player PLAYER_B = new Player(1, "skand");
    public static final Player PLAYER_C = new Player(2, "yann");

    public static final Fighter FIGHTER_A = new Fighter("ken", null);
    public static final Fighter FIGHTER_B = new Fighter("ryu", null);
    public static final Fighter FIGHTER_C = new Fighter("toto", null);

    private GameSession gameSession;

    @Override
    public void setUp() throws Exception {
        Fighter[] fighters = {FIGHTER_A, FIGHTER_B, FIGHTER_C};
        gameSession = new GameSession();
        gameSession.applySettings(3, 1, new SuperStreetFighter4ArcadeEditionGame());
        gameSession.registerPlayer(PLAYER_A);
        gameSession.registerPlayer(PLAYER_B);
        gameSession.registerPlayer(PLAYER_C);
        gameSession.registerFighters(PLAYER_A, asList(fighters));
        gameSession.registerFighters(PLAYER_B, asList(fighters));
        gameSession.registerFighters(PLAYER_C, asList(fighters));
    }

    public void testEliminateAPlayer() {
        gameSession.start();
        List<PlayerInstance> playerInstanceList = gameSession.getPlayerInstanceList();
        Map<Integer, PlayerInstance> playerOrderMap = new HashMap<Integer, PlayerInstance>();
        playerOrderMap.put(0, playerInstanceList.get(0));
        playerOrderMap.put(1, playerInstanceList.get(1));
        playerOrderMap.put(2, playerInstanceList.get(2));

        assertEquals(3, playerOrderMap.get(1).getRemainingFightersCount());
        //Round  [Player1 vs Player2] => P1 Win (Player2 fighters remaining
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        assertEquals(2, playerOrderMap.get(1).getRemainingFightersCount());

        //Round  [Player1 vs Player3] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());

        //Round  [Player1 vs Player2] => P1 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        assertTrue(playerOrderMap.get(1).isAlive());
        assertEquals(1, playerOrderMap.get(1).getRemainingFightersCount());

        //Round  [Player1 vs Player3] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer2());

        //Round  [Player3 vs Player2] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(1), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        assertEquals(0, playerOrderMap.get(1).getRemainingFightersCount());
        assertFalse(playerOrderMap.get(1).isAlive());

        //Round  [Player3 vs Player1] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer2());
        gameSession.endRound(gameSession.getPlayer1());
        assertFalse(gameSession.isOver());

        //Again versus Player1 cause Player2 was eliminated => Round  [Player3 vs Player1] => P3 Win
        gameSession.startNextRound();
        assertEquals(playerOrderMap.get(2), gameSession.getPlayer1());
        assertEquals(playerOrderMap.get(0), gameSession.getPlayer2());
        //Player 1 eliminated
        gameSession.endRound(gameSession.getPlayer1());
        assertTrue(gameSession.isOver());

    }

}
