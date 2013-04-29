package org.isma.web.versusfighting.dao;

import org.isma.web.versusfighting.model.Player;

import java.util.Map;

public interface VersusFightingDao {
    Map<Integer, Player> getAvailablePlayerMap();

    Player addPlayer(String player);
}
