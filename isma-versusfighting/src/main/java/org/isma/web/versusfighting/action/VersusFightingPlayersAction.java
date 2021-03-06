package org.isma.web.versusfighting.action;

import org.apache.log4j.Logger;
import org.isma.web.versusfighting.form.VersusFightingPlayersForm;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VersusFightingPlayersAction extends AbstractVersusFightingTournamentAction {
    private static final Logger logger = Logger.getLogger(VersusFightingPlayersAction.class);

    private VersusFightingPlayersForm form = new VersusFightingPlayersForm();

    public VersusFightingPlayersAction(VersusFightingManager manager) {
        super(manager);
    }


    //-------------------------------------------------
    //  ACTIONS
    //-------------------------------------------------
    public String menu() {
        return SUCCESS;
    }

    public String resetGameSession() {
        getManager().resetGameSession();
        return SUCCESS;
    }

    public String savePlayer() {
        getManager().savePlayer(form.getNewPlayer());
        return SUCCESS;
    }

    public String registerPlayer() {
        Player player = getManager().getPlayerMap().get(form.getChoosedPlayer());
        getGameSession().registerPlayer(player);
        return SUCCESS;
    }


    //-------------------------------------------------

    public Collection<Player> getAvailablePlayers() {
        Collection<Player> playerCollection = new ArrayList<Player>(getManager().getPlayerMap().values());
        List<Player> registeredPlayers = getRegisteredPlayers();
        playerCollection.removeAll(registeredPlayers);
        return playerCollection;
    }

    public List<Player> getRegisteredPlayers() {
        //From Session
        return new ArrayList<Player>(getGameSession().getRegisteredPlayerMap().values());
    }

    public void setNewPlayer(String newPlayer) {
        form.setNewPlayer(newPlayer);
    }

    public void setChoosedPlayer(int choosedPlayer) {
        form.setChoosedPlayer(choosedPlayer);
    }
}
