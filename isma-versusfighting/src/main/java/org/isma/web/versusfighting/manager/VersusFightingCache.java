package org.isma.web.versusfighting.manager;

import org.isma.web.versusfighting.dao.VersusFightingDao;
import org.isma.web.versusfighting.model.Player;

import java.util.HashMap;
import java.util.Map;

public class VersusFightingCache {
    private VersusFightingDao versusFightingDao;

    private final Map<Integer, Player> playerMap = new HashMap<Integer, Player>();

    public VersusFightingCache(VersusFightingDao versusFightingDao) {
        this.versusFightingDao = versusFightingDao;
    }


    public Map<Integer, Player> getPlayerMap() {
        if (playerMap.isEmpty()) {
            playerMap.putAll(versusFightingDao.getAvailablePlayerMap());
        }
        return playerMap;
    }


    public void savePlayer(String newPlayer) {
        Player player = versusFightingDao.addPlayer(newPlayer);
        playerMap.put(player.getId(), player);
    }
}
