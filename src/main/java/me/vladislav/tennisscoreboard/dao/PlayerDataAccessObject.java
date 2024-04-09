package me.vladislav.tennisscoreboard.dao;

import me.vladislav.tennisscoreboard.models.Player;
import me.vladislav.tennisscoreboard.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PlayerDataAccessObject implements DataAccessObject<Player> {

    public Optional<Player> getByName(String name) {
        Player player;
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class);
            query.setParameter("name", name);
            player = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        } catch (Exception e){
            return Optional.empty();
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
