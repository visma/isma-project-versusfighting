package org.isma.web.versusfighting.action;

import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.isma.utils.collections.CollectionHelper;
import org.isma.web.versusfighting.form.VGTournamentFightersChoiceForm;
import org.isma.web.versusfighting.manager.VGTournamentManager;
import org.isma.web.versusfighting.model.Fighter;
import org.isma.web.versusfighting.model.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class VGTournamentFightersChoiceAction extends AbstractVersusFightingTournamentAction {
    private static final Logger logger = Logger.getLogger(VGTournamentFightersChoiceAction.class);
    private VGTournamentFightersChoiceForm form;

    public VGTournamentFightersChoiceAction(VGTournamentManager manager) {
        super(manager);
    }


    //-------------------------------------------------
    //  ACTIONS
    //-------------------------------------------------
    @Override
    public String execute() throws Exception {
        form = new VGTournamentFightersChoiceForm();
        form.setFighterSelectionGridsBean(new FighterSelectionGridsBean(getPlayerList(), getGame()));
        return SUCCESS;
    }

    public String validateFightersChoice() {
        return SUCCESS;
    }

    public String changePlayerForFightersChoice() {
        logger.trace(format("changePlayerForFightersChoice(choosedPlayer=%s)\n", form.getChoosedPlayer()));
        //form.getFighterSelectionGridsBean().printData(form.getChoosedPlayer());
        return SUCCESS;
    }

    public String validateFighters() {
        for (Player player : getPlayerList()) {
            List<Fighter> fighterList = form.getFighterSelectionGridsBean().getSelectedFighterList(player);
            getGameSession().registerFighters(player, fighterList);
        }
        getGameSession().start();
        return SUCCESS;
    }

    //-------------------------------------------------
    public Collection<Player> getPlayerList() {
        return getGameSession().getRegisteredPlayerMap().values();
    }

    public void setChoosedPlayer(int[] players) {
        Player choosedPlayer = getGameSession().getRegisteredPlayerMap().get(players[0]);
        logger.trace(format("setChoosedPlayer(%s)\n", choosedPlayer == null ? "" : choosedPlayer.getLabel()));
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


    private Map<Player, List<List<FighterBean>>> buildFighterBeansMap() {
        Map<Player, List<List<FighterBean>>> map = new HashMap<Player, List<List<FighterBean>>>();
        map.put(null, buildFightersBeansNewInstance());
        for (Player player : getPlayerList()) {
            map.put(player, buildFightersBeansNewInstance());
        }
        return map;
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
