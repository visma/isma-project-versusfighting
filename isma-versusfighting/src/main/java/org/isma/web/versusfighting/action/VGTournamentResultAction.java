package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.manager.VGTournamentManager;
import org.isma.web.versusfighting.model.PlayerInstance;

public class VGTournamentResultAction extends AbstractVersusFightingTournamentAction {
    private static final Logger logger = Logger.getLogger(VGTournamentResultAction.class);

    public VGTournamentResultAction(VGTournamentManager manager) {
        super(manager);
    }

    @Override
    public String execute() throws Exception {
        return super.execute();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public PlayerInstance getWinner() {
        return getGameSession().getPlayer1();
    }
}
