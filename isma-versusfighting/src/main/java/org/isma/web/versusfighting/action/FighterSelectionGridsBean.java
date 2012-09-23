package org.isma.web.versusfighting.action;

import org.apache.commons.collections.Transformer;
import org.isma.utils.collections.CollectionHelper;
import org.isma.web.versusfighting.model.AbstractVersusFightingGame;
import org.isma.web.versusfighting.model.Fighter;
import org.isma.web.versusfighting.model.Player;

import java.util.*;

public class FighterSelectionGridsBean {
    private Map<Player, List<List<FighterBean>>> fighterBeansMap;


    public FighterSelectionGridsBean(Collection<Player> players, AbstractVersusFightingGame game) {
        fighterBeansMap = buildFighterBeansMap(players, game);
    }

    private Map<Player, List<List<FighterBean>>> buildFighterBeansMap(Collection<Player> players, AbstractVersusFightingGame game) {
        Map<Player, List<List<FighterBean>>> map = new HashMap<Player, List<List<FighterBean>>>();
        map.put(null, buildFightersBeansNewInstance(game));
        for (Player player : players) {
            map.put(player, buildFightersBeansNewInstance(game));
        }
        return map;
    }

    private List<List<FighterBean>> buildFightersBeansNewInstance(final AbstractVersusFightingGame game) {
        List gridData = game.getCasting().buildGridData();
        Transformer transformer = new Transformer() {
            @Override
            public Object transform(Object input) {
                return new FighterBean(game, (Fighter) input, false);
            }
        };
        CollectionHelper.transformBiDimensionalList(gridData, transformer);
        return (List<List<FighterBean>>) gridData;
    }

    public List<List<FighterBean>> getData(Player player) {
        return fighterBeansMap.get(player);
    }

    public int getFightersRemaining(Player player, int fightersAmount) {
        if (player == null) {
            return 0;
        }
        List<List<FighterBean>> data = fighterBeansMap.get(player);
        int selected = 0;
        for (List<FighterBean> line : data) {
            for (FighterBean fighterBean : line) {
                selected += fighterBean.isSelected() ? 1 : 0;
            }
        }
        return fightersAmount - selected;
    }

    public void printData(Player player) {
        List<List<FighterBean>> data = fighterBeansMap.get(player);
        int x = 0;
        System.out.println("==================================================================");
        for (List<FighterBean> fighterBeans : data) {
            int y = 0;
            for (FighterBean fighterBean : fighterBeans) {
                System.out.printf("%s => getData(x=%s, y=%s, name=%s)=%s\n",
                        player == null ? "null" : player.getLabel(),
                        x,
                        y,
                        fighterBean.getFighter().getLabel(),
                        fighterBean.isSelected());
                y++;
            }
            x++;
        }
    }


    public boolean isSelectionOverForPlayers(Player currentPlayer, int fightersAmount) {
        for (Player otherPlayer : fighterBeansMap.keySet()) {
            if (otherPlayer == null) {
                continue;
            }
            int fightersSelected = 0;
            List<List<FighterBean>> grid = fighterBeansMap.get(otherPlayer);
            for (List<FighterBean> line : grid) {
                for (FighterBean fighterBean : line) {
                    fightersSelected += fighterBean.isSelected() ? 1 : 0;
                }
            }
            if (fightersSelected < fightersAmount) {
                System.out.printf("player[%s] selection : %s/%s\n", otherPlayer.getLabel(), fightersSelected, fightersAmount);
                return false;
            }
        }
        return true;
    }

    public List<Fighter> getSelectedFighters(Player player) {
        List<Fighter> selectedFighters = new ArrayList<Fighter>();
        List<List<FighterBean>> data = fighterBeansMap.get(player);
        for (List<FighterBean> line : data) {
            for (FighterBean fighterBean : line) {
                if (fighterBean.isSelected()) {
                    selectedFighters.add(fighterBean.getFighter());
                }
            }
        }
        return selectedFighters;
    }
}
