package org.isma.web.versusfighting.manager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.isma.web.versusfighting.model.*;

import java.util.*;

public class GameSession {
    private final Map<Integer, Player> registeredPlayerMap = new HashMap<Integer, Player>();
    private AbstractVersusFightingGame game;
    private int fightersAmount;
    private int matchesAmount;

    private List<PlayerInstance> playerInstanceList = new ArrayList<PlayerInstance>();
    private LinkedList<PlayerInstance> playerInstanceQueue = new LinkedList<PlayerInstance>();
    private PlayerInstance player1;
    private PlayerInstance player2;
    private FighterInstance fighterP1;
    private FighterInstance fighterP2;

    private void activateCurrentPlayers() {
        player1.active();
        player2.active();
        fighterP1.active();
        fighterP2.active();
    }

    public void start() {
        Collections.shuffle(playerInstanceList);
        playerInstanceQueue.addAll(playerInstanceList);
        player1 = playerInstanceQueue.poll();
        player2 = playerInstanceQueue.poll();
        fighterP1 = nextFighter(player1);
        fighterP2 = nextFighter(player2);
        activateCurrentPlayers();
    }


    public void startNextRound() {
        if (player2.isAlive()) {
            playerInstanceQueue.add(player2);
        }
        player2 = playerInstanceQueue.poll();
        fighterP1 = nextFighter(player1);
        fighterP2 = nextFighter(player2);
        activateCurrentPlayers();
    }

    public void endRound(PlayerInstance winner) {
        if (winner == player1) {
            getFighterP2().kill();
        } else {
            getFighterP1().kill();
            PlayerInstance oldPlayer1 = player1;
            player1 = player2;
            player2 = oldPlayer1;
        }
        player2.unActive();
    }

    private int countPlayerInstanceAlive() {
        int count = 0;
        for (PlayerInstance playerInstance : playerInstanceList) {
            count += playerInstance.isAlive() ? 1 : 0;
        }
        return count;
    }

    public void applySettings(int fightersAmount, int matchesAmount, AbstractVersusFightingGame game) {
        this.fightersAmount = fightersAmount;
        this.matchesAmount = matchesAmount;
        this.game = game;
    }

    public Map<Integer, Player> getRegisteredPlayerMap() {
        return registeredPlayerMap;
    }

    public void registerPlayer(Player player) {
        registeredPlayerMap.put(player.getId(), player);
    }

    public void registerFighters(Player player, List<Fighter> fighterList) {
        List copyList = new ArrayList<Fighter>(fighterList);
        CollectionUtils.transform(copyList, new Transformer() {
            @Override
            public Object transform(Object input) {
                return new FighterInstance((Fighter) input, game);
            }
        });
        playerInstanceList.add(new PlayerInstance(player, (List<FighterInstance>) copyList));
    }

    public List<FighterInstance> getFighterInstanceList(Player player) {
        for (PlayerInstance playerInstance : playerInstanceList) {
            if (playerInstance.getObject() == player) {
                return playerInstance.getFighterInstanceList();
            }
        }
        throw new RuntimeException("player not found !");
    }

    private FighterInstance nextFighter(PlayerInstance playerInstance) {
        List<FighterInstance> copyList = new ArrayList<FighterInstance>(playerInstance.getFighterInstanceList());
        CollectionUtils.filter(copyList, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((FighterInstance) object).isAlive();
            }
        });
        Collections.shuffle(copyList);
        return copyList.isEmpty() ? null : copyList.get(0);
    }


    public List<PlayerInstance> getPlayerInstanceList() {
        return playerInstanceList;
    }

    public int getFightersAmount() {
        return fightersAmount;
    }

    public int getMatchesAmount() {
        return matchesAmount;
    }

    public AbstractVersusFightingGame getGame() {
        return game;
    }


    public boolean isOver() {
        return countPlayerInstanceAlive() == 1;
    }

    public List<PlayerInstance> getFightingPlayerList() {
        ArrayList<PlayerInstance> list = new ArrayList<PlayerInstance>();
        list.add(player1);
        list.add(player2);
        return list;
    }

    public PlayerInstance getPlayer1() {
        return player1;
    }

    public PlayerInstance getPlayer2() {
        return player2;
    }

    public FighterInstance getFighterP1() {
        return fighterP1;
    }

    public FighterInstance getFighterP2() {
        return fighterP2;
    }

}
