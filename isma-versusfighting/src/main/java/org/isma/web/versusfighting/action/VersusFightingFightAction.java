package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.FighterInstance;
import org.isma.web.versusfighting.model.PlayerInstance;

import java.util.List;

import static java.lang.String.format;

public class VersusFightingFightAction extends AbstractVersusFightingTournamentAction {
    private static final Logger LOGGER = Logger.getLogger(VersusFightingFightAction.class);
    public static final String FINAL_RESULT = "finalResult";

    private PlayerInstance winner;

    public VersusFightingFightAction(VersusFightingManager manager) {
        super(manager);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    //TODO le F5 mène à cette action, ce serait mieux qu'elle mene a execute()
    public String nextRound() {
        //Resolution

        getGameSession().endRound(winner);
        if (getGameSession().isOver()) {
            return FINAL_RESULT;
        }
        getGameSession().startNextRound();
        PlayerInstance player1 = getGameSession().getPlayer1();
        PlayerInstance player2 = getGameSession().getPlayer2();
        FighterInstance fighter1 = getGameSession().getFighterP1();
        FighterInstance fighter2 = getGameSession().getFighterP2();
        LOGGER.info(format("player1=[%s (%s)], player2=[%s (%s)]\n",
                player1.getLabel(), fighter1.getLabel(), player2.getLabel(), fighter2.getLabel()));

        return SUCCESS;
    }

    public void setWinner(int[] winnerIds) {
        getGameSession().getPlayerInstances();
        for (PlayerInstance playerInstance : getGameSession().getPlayerInstances()) {
            if (playerInstance.getObject().getId() == winnerIds[0]) {
                winner = playerInstance;
            }
        }
    }

    public PlayerInstance getPlayer1() {
        return getGameSession().getPlayer1();
    }

    public PlayerInstance getPlayer2() {
        return getGameSession().getPlayer2();
    }

    public FighterInstance getFighterP1() {
        return getGameSession().getFighterP1();
    }

    public FighterInstance getFighterP2() {
        return getGameSession().getFighterP2();
    }

    public List<PlayerInstance> getPlayers() {
        return getGameSession().getPlayerInstances();
    }

    public List<PlayerInstance> getFightingPlayers() {
        return getGameSession().getFightingPlayers();
    }


}
