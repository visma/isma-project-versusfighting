package org.isma.web.versusfighting.form;

import org.isma.web.versusfighting.action.FighterSelectionGridsBean;
import org.isma.web.versusfighting.model.Player;

public class VersusFightingFightersChoiceForm {
    private Player choosedPlayer;
    private FighterSelectionGridsBean fighterSelectionGridsBean;

    public Player getChoosedPlayer() {
        return choosedPlayer;
    }

    public void setChoosedPlayer(Player choosedPlayer) {
        this.choosedPlayer = choosedPlayer;
    }

    public void setFighterSelectionGridsBean(FighterSelectionGridsBean fighterSelectionGridsBean) {
        this.fighterSelectionGridsBean = fighterSelectionGridsBean;
    }

    public FighterSelectionGridsBean getFighterSelectionGridsBean() {
        return fighterSelectionGridsBean;
    }
}
