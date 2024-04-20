package me.vladislav.tennis_scoreboard.dao;

import me.vladislav.tennis_scoreboard.models.Match;
import me.vladislav.tennis_scoreboard.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchDataAccessObject implements DataAccessObject<Match> {
    @Override
    public Optional<Match> getById(int id) {
        try (Session session = HibernateUtils.getSession()){
            session.beginTransaction();
            Query<Match> query = session.createQuery("SELECT m FROM Match m WHERE m.id = :id");
            query.setParameter("id", id);
            Match match = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(match);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Match>> getList() {
        try (Session session = HibernateUtils.getSession()){
            session.beginTransaction();
            Query<Match> query = session.createQuery("FROM Match m", Match.class);
            List<Match> matches = query.getResultList();
            session.getTransaction().commit();
            return Optional.ofNullable(matches);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void add(Match match) {
        try (Session session = HibernateUtils.getSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
