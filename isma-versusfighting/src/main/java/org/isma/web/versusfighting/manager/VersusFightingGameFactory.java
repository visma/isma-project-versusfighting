package org.isma.web.versusfighting.manager;

import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.impl.SuperStreetFighter4ArcadeEditionGame;

import java.util.HashMap;
import java.util.Map;

public class VersusFightingGameFactory {
    VersusFightingGameFactory() {
    }


    public Map<String, AbstractVersusFightingGame> buildGameMap() {
        Map<String, AbstractVersusFightingGame> map = new HashMap<String, AbstractVersusFightingGame>();
        map.put(SuperStreetFighter4ArcadeEditionGame.GAME_ID, new SuperStreetFighter4ArcadeEditionGame());
        return map;
    }
}
