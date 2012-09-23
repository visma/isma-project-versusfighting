package org.isma.web.versusfighting.action;

import com.opensymphony.xwork2.ActionSupport;
import org.isma.web.versusfighting.manager.GameSession;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;

public abstract class AbstractVersusFightingTournamentAction extends ActionSupport {
    private VersusFightingManager manager;

    protected AbstractVersusFightingTournamentAction(VersusFightingManager manager) {
        this.manager = manager;
    }

    protected VersusFightingManager getManager() {
        return manager;
    }

    protected GameSession getGameSession() {
        return manager.getGameSession();
    }

    public AbstractVersusFightingGame getGame() {
        return manager.getGameSession().getGame();
    }
}
