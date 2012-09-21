package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.form.VGTournamentGameSettingsForm;
import org.isma.web.versusfighting.manager.VGTournamentManager;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.MatchesAmount;

import java.util.Collection;
import java.util.List;

public class VGTournamentGameSettingsAction extends AbstractVersusFightingTournamentAction {
    private static final Logger logger = Logger.getLogger(VGTournamentGameSettingsAction.class);

    private VGTournamentGameSettingsForm form = new VGTournamentGameSettingsForm();

    public VGTournamentGameSettingsAction(VGTournamentManager manager) {
        super(manager);
    }


    //-------------------------------------------------
    //  ACTIONS
    //-------------------------------------------------
    public String validateGameSettings() {
        logger.debug("fighters amount : " + form.getFightersAmount());
        logger.debug("matches amount : " + form.getMatchesAmount());
        logger.debug("game : " + form.getGameIdentifier());
        AbstractVersusFightingGame game = getManager().getVersusFightingGameMap().get(form.getGameIdentifier());
        getGameSession().applySettings(form.getFightersAmount(), form.getMatchesAmount(), game);
        return SUCCESS;
    }
    //-------------------------------------------------

    public Collection<AbstractVersusFightingGame> getGameList() {
        return getManager().getVersusFightingGameMap().values();
    }

    public List<MatchesAmount> getMatchesAmountList() {
        return MatchesAmount.getList();
    }

    public void setFightersAmount(int fightersAmount) {
        form.setFightersAmount(fightersAmount);
    }

    public void setMatchesAmount(int matchesAmount) {
        form.setMatchesAmount(matchesAmount);
    }

    public void setChoosedGame(String gameId) {
        form.setGameIdentifier(gameId);
    }
}
