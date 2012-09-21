package org.isma.web.versusfighting.model.impl;

import org.isma.web.versusfighting.model.AbstractVersusFightingGame;

public class SuperStreetFighter4ArcadeEditionGame extends AbstractVersusFightingGame {
    public static final String GAME_ID = "SSFIVAE";
    private static final String GAME_LABEL = "Super Street Fighter IV Arcade Edition";

    public SuperStreetFighter4ArcadeEditionGame() {
        super(GAME_ID, GAME_LABEL, new SuperStreetFighter4ArcadeEditionCasting());
    }
}
