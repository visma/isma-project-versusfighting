package org.isma.web.versusfighting.action;

import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.isma.utils.collections.CollectionHelper;
import org.isma.web.versusfighting.form.VersusFightingFightersChoiceForm;
import org.isma.web.versusfighting.manager.VersusFightingManager;
import org.isma.web.versusfighting.model.Fighter;
import org.isma.web.versusfighting.model.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class VersusFightingFightersChoiceAction extends AbstractVersusFightingTournamentAction {
    private static final Logger LOGGER = Logger.getLogger(VersusFightingFightersChoiceAction.class);
    private VersusFightingFightersChoiceForm form;

    public VersusFightingFightersChoiceAction(VersusFightingManager manager) {
        super(manager);
    }


    //-------------------------------------------------
    //  ACTIONS
    //-------------------------------------------------
    @Override
    public String execute() throws Exception {
        form = new VersusFightingFightersChoiceForm();
        form.setFighterSelectionGridsBean(new FighterSelectionGridsBean(getPlayers(), getGame()));
        return SUCCESS;
    }

    public String validateFightersChoice() {
        return SUCCESS;
    }

    public String changePlayerForFightersChoice() {
        LOGGER.trace(format("changePlayerForFightersChoice(choosedPlayer=%s)\n", form.getChoosedPlayer()));
        return SUCCESS;
    }

    public String validateFighters() {
        for (Player player : getPlayers()) {
            List<Fighter> fighters = form.getFighterSelectionGridsBean().getSelectedFighters(player);
            getGameSession().registerFighters(player, fighters);
        }
        getGameSession().start();
        return SUCCESS;
    }

    //-------------------------------------------------
    public Collection<Player> getPlayers() {
        return getGameSession().getRegisteredPlayerMap().values();
    }

    public void setChoosedPlayer(int[] players) {
        Player choosedPlayer = getGameSession().getRegisteredPlayerMap().get(players[0]);
        LOGGER.trace(format("setChoosedPlayer(%s)\n", choosedPlayer == null ? "" : choosedPlayer.getLabel()));
        form.setChoosedPlayer(choosedPlayer);
    }


    public int getDefaultChoosedPlayerId() {
        if (form.getChoosedPlayer() == null) {
            return -1;
        }
        return form.getChoosedPlayer().getId();
    }
    //-------------------------------------------------

    public List<List<FighterBean>> getData() {
        Player player = form.getChoosedPlayer();
        return form.getFighterSelectionGridsBean().getData(player);
    }


    public int getFightersRemaining() {
        Player choosedPlayer = form.getChoosedPlayer();
        if (choosedPlayer == null) {
            return 0;
        }
        return form.getFighterSelectionGridsBean().getFightersRemaining(choosedPlayer, getGameSession().getFightersAmount());
    }

    public boolean isSelectionOverForPlayers() {
        return form.getFighterSelectionGridsBean().isSelectionOverForPlayers(form.getChoosedPlayer(), getGameSession().getFightersAmount());
    }


    private List<List<FighterBean>> buildFightersBeansNewInstance() {
        List gridData = getGame().getCasting().buildGridData();
        Transformer transformer = new Transformer() {
            @Override
            public Object transform(Object input) {
                return new FighterBean(getGame(), (Fighter) input, false);
            }
        };
        CollectionHelper.transformBiDimensionalList(gridData, transformer);
        return (List<List<FighterBean>>) gridData;
    }


}
