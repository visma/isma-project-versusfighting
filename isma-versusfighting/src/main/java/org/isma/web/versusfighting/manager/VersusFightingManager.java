package org.isma.web.versusfighting.manager;

import org.isma.web.versusfighting.dao.VersusFightingDao;
import org.isma.web.versusfighting.dao.VersusFightingSchemaGenerationDao;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.Player;

import java.util.Map;

public class VersusFightingManager {
    private final Map<String, AbstractVersusFightingGame> versusFightingGameMap;
    private final VersusFightingCache versusFightingCache;
    private GameSession gameSession = new GameSession();

    public VersusFightingManager(VersusFightingDao versusFightingDao, VersusFightingSchemaGenerationDao versusFightingSchemaGenerationDao) throws Exception {
        this.versusFightingCache = new VersusFightingCache(versusFightingDao);
        versusFightingGameMap = new VersusFightingGameFactory().buildGameMap();
        versusFightingSchemaGenerationDao.generateSchema();
    }

    public Map<String, AbstractVersusFightingGame> getVersusFightingGameMap() {
        return versusFightingGameMap;
    }


    public Map<Integer, Player> getPlayerMap() {
        return versusFightingCache.getPlayerMap();
    }


    public void savePlayer(String newPlayer) {
        versusFightingCache.savePlayer(newPlayer);
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void resetGameSession() {
        gameSession = new GameSession();
    }

}
