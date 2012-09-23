package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.form.VersusFightingGameSettingsForm;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.MatchesAmount;

import java.util.Collection;
import java.util.List;

public class VersusFightingGameSettingsAction extends AbstractVersusFightingTournamentAction {
    private static final Logger logger = Logger.getLogger(VersusFightingGameSettingsAction.class);

    private VersusFightingGameSettingsForm form = new VersusFightingGameSettingsForm();

    public VersusFightingGameSettingsAction(VersusFightingManager manager) {
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

    public Collection<AbstractVersusFightingGame> getGames() {
        return getManager().getVersusFightingGameMap().values();
    }

    public List<MatchesAmount> getMatchesAmounts() {
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
