package me.vladislav.tennisscoreboard.services;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;

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

    public void addCurrentMatch(CurrentMatch currentMatch) {
        currentMatches.put(currentMatch.getId(), currentMatch);
    }

    public void removeCurrentMatch(UUID id) {
        currentMatches.remove(id);
    }

    public CurrentMatch getCurrentMatch(UUID id) {
        return currentMatches.get(id);
    }

}
