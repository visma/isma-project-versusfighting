package org.isma.web.versusfighting.manager;

import org.isma.web.versusfighting.dao.VGTournamentDao;
import org.isma.web.versusfighting.model.Player;

import java.util.HashMap;
import java.util.Map;

public class VGTournamentCache {
    private VGTournamentDao vgTournamentDao;

    private final Map<Integer, Player> playerMap = new HashMap<Integer, Player>();

    public VGTournamentCache(VGTournamentDao vgTournamentDao) {
        this.vgTournamentDao = vgTournamentDao;
    }


    public Map<Integer, Player> getPlayerMap() {
        if (playerMap.isEmpty()) {
            playerMap.putAll(vgTournamentDao.getAvailablePlayerMap());
        }
        return playerMap;
    }


    public void savePlayer(String newPlayer) {
        Player player = vgTournamentDao.addPlayer(newPlayer);
        playerMap.put(player.getId(), player);
    }
}
