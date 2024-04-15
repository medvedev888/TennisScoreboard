package me.vladislav.tennis_scoreboard.dao;

import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PlayerDataAccessObject implements DataAccessObject<Player> {

    public Optional<Player> getByName(String name) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class);
            query.setParameter("name", name);
            Player player = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Player> getById(int id) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("SELECT p FROM Player p WHERE p.id = :id", Player.class);
            query.setParameter("id", id);
            Player player = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Player>> getList() {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            List<Player> listOfPlayers = session.createQuery("FROM Player", Player.class).getResultList();
            session.getTransaction().commit();
            return Optional.ofNullable(listOfPlayers);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public void add(Player player) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
