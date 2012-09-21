package org.isma.web.versusfighting.dao;

import org.isma.web.versusfighting.model.Player;

import java.util.HashMap;
import java.util.Map;

public class VGTournamentMockDao implements VGTournamentDao {
    private int playerIdCurrent = 1;
    private HashMap<Integer, Player> playerMap = new HashMap<Integer, Player>();

    public VGTournamentMockDao() {
        playerMap.put(1, new Player(1, "isma"));
        playerMap.put(2, new Player(2, "skand"));
        playerMap.put(3, new Player(3, "will"));
        playerMap.put(4, new Player(4, "yann"));
    }

    @Override
    public Map<Integer, Player> getAvailablePlayerMap() {
        return playerMap;
    }

    @Override
    public Player addPlayer(String playerName) {
        Player player = new Player(playerName);
        player.setId(playerIdCurrent++);
        playerMap.put(player.getId(), player);
        return player;
    }
}
