package org.isma.web.versusfighting.form;

public class VersusFightingGameSettingsForm {
    private int fightersAmount;
    private int matchesAmount;
    private String gameIdentifier;

    public int getFightersAmount() {
        return fightersAmount;
    }

    public void setFightersAmount(int fightersAmount) {
        this.fightersAmount = fightersAmount;
    }

    public int getMatchesAmount() {
        return matchesAmount;
    }

    public void setMatchesAmount(int matchesAmount) {
        this.matchesAmount = matchesAmount;
    }

    public String getGameIdentifier() {
        return gameIdentifier;
    }

    public void setGameIdentifier(String gameIdentifier) {
        this.gameIdentifier = gameIdentifier;
    }
}
