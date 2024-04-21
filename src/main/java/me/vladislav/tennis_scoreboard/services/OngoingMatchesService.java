package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static class OngoingMatchesServiceHolder {
        private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    }

    private static final ConcurrentHashMap<UUID, CurrentMatchDTO> currentMatches = new ConcurrentHashMap<>();

    public static OngoingMatchesService getInstance(){
        return OngoingMatchesServiceHolder.INSTANCE;
    }

    public synchronized void addCurrentMatch(CurrentMatchDTO currentMatchDTO) {
        currentMatches.put(currentMatchDTO.getId(), currentMatchDTO);
    }

    public synchronized void removeCurrentMatch(UUID id) {
        currentMatches.remove(id);
    }

    public synchronized CurrentMatchDTO getCurrentMatch(UUID id) {
        return currentMatches.get(id);
    }

    public synchronized void update(UUID id, CurrentMatchDTO currentMatchDTO) {
        removeCurrentMatch(id);
        addCurrentMatch(currentMatchDTO);
    }

}
