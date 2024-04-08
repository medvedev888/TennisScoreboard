package me.vladislav.tennisscoreboard.dao;

import me.vladislav.tennisscoreboard.Exception.DataAccessException;
import me.vladislav.tennisscoreboard.models.Player;
import me.vladislav.tennisscoreboard.utils.HibernateUtils;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDataAccessObject implements DataAccessObject<Player>{


    // maybe must be use the uniqueResultOptional() method for getting player
    public Player getByName(String name) throws DataAccessException {
        Player player;
        try (Session session = HibernateUtils.getSession()) {
            try {
                session.beginTransaction();
                Query<Player> query = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class);
                query.setParameter("name", name);
                player = query.uniqueResult();
                session.getTransaction().commit();
            } catch (NonUniqueResultException e){
                session.getTransaction().rollback();
                throw new DataAccessException("Error retrieving player", e);
            }
            return player;
        }
    }

    @Override
    public Player getById(int id) {
        return null;
    }

    @Override
    public List<Player> getList() {
        return List.of();
    }

    @Override
    public void add(Player player) {

    }
}
