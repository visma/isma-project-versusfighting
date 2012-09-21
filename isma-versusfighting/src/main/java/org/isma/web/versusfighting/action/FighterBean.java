package org.isma.web.versusfighting.action;

import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.Fighter;

public class FighterBean {
    //TODO variable game a virer quand trouver un autre moyen de lire getGame() depuis l'action dans la jsp
    private final AbstractVersusFightingGame game;
    private final Fighter fighter;
    private boolean selected;

    public FighterBean(AbstractVersusFightingGame game, Fighter fighter, boolean selected) {
        this.game = game;
        this.fighter = fighter;
    }


    public Fighter getFighter() {
        return fighter;
    }

    public AbstractVersusFightingGame getGame() {
        return game;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
