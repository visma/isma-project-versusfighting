package org.isma.web.versusfighting.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.isma.web.versusfighting.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VGTournamentHibernateDao implements VGTournamentDao {
    private SessionFactory sessionFactory;

    public VGTournamentHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Map<Integer, Player> getAvailablePlayerMap() {
        Map<Integer, Player> map = new HashMap<Integer, Player>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Player");
        List<Player> list = (List<Player>) query.list();
        for (Player player : list) {
            map.put(player.getId(), player);
        }
        transaction.commit();
        //TODO savoir quand ouvrir et fermer la session le plus optimalement possible
        session.close();
        return map;
    }

    @Override
    public Player addPlayer(String playerName) {
        Player player = new Player(playerName);
        Session session = sessionFactory.openSession();
        player.setId((Integer) session.save(player));
        //TODO savoir quand ouvrir et fermer la session le plus optimalement possible
        session.close();
        return player;
    }
}
