package org.isma.web.versusfighting.dao;

import org.isma.web.versusfighting.model.Player;

import java.util.Map;

public interface VGTournamentDao {
    public Map<Integer, Player> getAvailablePlayerMap();

    public Player addPlayer(String player);
}
