package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.PlayerInstance;

public class VersusFightingResultAction extends AbstractVersusFightingTournamentAction {
    private static final Logger LOGGER = Logger.getLogger(VersusFightingResultAction.class);

    public VersusFightingResultAction(VersusFightingManager manager) {
        super(manager);
    }


    public PlayerInstance getWinner() {
        return getGameSession().getPlayer1();
    }
}
