package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static class OngoingMatchesServiceHolder {
        private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    }

    private static final ConcurrentHashMap<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    public static OngoingMatchesService getInstance(){
        return OngoingMatchesServiceHolder.INSTANCE;
    }

    public synchronized void addCurrentMatch(CurrentMatch currentMatch) {
        currentMatches.put(currentMatch.getId(), currentMatch);
    }

    public synchronized void removeCurrentMatch(UUID id) {
        currentMatches.remove(id);
    }

    public synchronized CurrentMatch getCurrentMatch(UUID id) {
        return currentMatches.get(id);
    }

    public synchronized void update(UUID id, CurrentMatch currentMatch) {
        removeCurrentMatch(id);
        addCurrentMatch(currentMatch);
    }

}
