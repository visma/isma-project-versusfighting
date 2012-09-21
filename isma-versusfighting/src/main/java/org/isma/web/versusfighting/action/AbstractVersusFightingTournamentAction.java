package org.isma.web.versusfighting.action;

import com.opensymphony.xwork2.ActionSupport;
import org.isma.web.versusfighting.manager.GameSession;
import org.isma.web.versusfighting.manager.VGTournamentManager;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;

public abstract class AbstractVersusFightingTournamentAction extends ActionSupport {
    private VGTournamentManager manager;

    protected AbstractVersusFightingTournamentAction(VGTournamentManager manager) {
        this.manager = manager;
    }

    protected VGTournamentManager getManager() {
        return manager;
    }

    protected GameSession getGameSession() {
        return manager.getGameSession();
    }

    public AbstractVersusFightingGame getGame() {
        return manager.getGameSession().getGame();
    }
}
