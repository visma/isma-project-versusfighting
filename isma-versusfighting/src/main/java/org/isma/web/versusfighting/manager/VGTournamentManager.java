package org.isma.web.versusfighting.manager;

import org.isma.web.versusfighting.dao.VGTournamentDao;
import org.isma.web.versusfighting.dao.VGTournamentSchemaGenerationDao;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.Player;

import java.util.Map;

public class VGTournamentManager {
    private final Map<String, AbstractVersusFightingGame> versusFightingGameMap;
    private final VGTournamentCache vgTournamentCache;
    private GameSession gameSession = new GameSession();

    public VGTournamentManager(VGTournamentDao vgTournamentDao, VGTournamentSchemaGenerationDao vgTournamentSchemaGenerationDao) throws Exception {
        this.vgTournamentCache = new VGTournamentCache(vgTournamentDao);
        versusFightingGameMap = new VersusFightingGameFactory().buildGameMap();
        vgTournamentSchemaGenerationDao.generateSchema();
    }

    public Map<String, AbstractVersusFightingGame> getVersusFightingGameMap() {
        return versusFightingGameMap;
    }


    public Map<Integer, Player> getPlayerMap() {
        return vgTournamentCache.getPlayerMap();
    }


    public void savePlayer(String newPlayer) {
        vgTournamentCache.savePlayer(newPlayer);
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void resetGameSession() {
        gameSession = new GameSession();
    }

}
