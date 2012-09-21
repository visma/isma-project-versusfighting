package org.isma.web.versusfighting.model;

public class FighterInstance extends AbstractAliveInstance<Fighter> {
    //TODO embarquer le game, c une bidouille pour facilier la vie coté jsp... à dégager quand on arrive a s'en passer
    private AbstractVersusFightingGame game;
    private boolean hidden = true;

    public FighterInstance(Fighter fighter, AbstractVersusFightingGame game) {
        super(fighter);
        this.game = game;
    }

    public AbstractVersusFightingGame getGame() {
        return game;
    }

    @Override
    public void active() {
        super.active();
        show();
    }

    private void show() {
        hidden = false;
    }

    public boolean isHidden() {
        return hidden;
    }

}
