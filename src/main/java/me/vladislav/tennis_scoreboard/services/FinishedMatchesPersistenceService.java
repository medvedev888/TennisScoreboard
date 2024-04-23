package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dao.MatchDataAccessObject;
import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.dto.PaginationResultDTO;
import me.vladislav.tennis_scoreboard.models.Match;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinishedMatchesPersistenceService {

    public void saveMatch(CurrentMatchDTO currentMatchDTO, MatchDataAccessObject matchDataAccessObject) {
        if(currentMatchDTO.getMatchState() != null){
            Player winner = currentMatchDTO.getMatchState() == MatchState.PLAYER_1_WON ? currentMatchDTO.getPlayer1() : currentMatchDTO.getPlayer2();
            Match match = new Match(currentMatchDTO.getPlayer1(), currentMatchDTO.getPlayer2(), winner);
            matchDataAccessObject.add(match);
        } else {
            throw new RuntimeException("Winner is null");
        }
    }

    public PaginationResultDTO getListOfMatchesByPlayerNameForPage(int page, String playerName, MatchDataAccessObject matchDataAccessObject) {
        Optional<List<Match>> optionalListOfMatches = matchDataAccessObject.getMatchesByPlayerName(playerName);
        List<Match> matches = new ArrayList<>();
        PaginationResultDTO paginationResultDTO = new PaginationResultDTO(page, false, "");

        if (playerName == null || playerName.isEmpty()) {
            optionalListOfMatches = matchDataAccessObject.getList();
            paginationResultDTO.setPlayerName("");
        }
        if (optionalListOfMatches.isPresent()) {
            if (optionalListOfMatches.get().size() <= (page - 1) * 5) {
                page = 1;
                paginationResultDTO.setCurrentPage(1);
            }
            List<Match> listOfMatches = optionalListOfMatches.get();
            int startIndex = (page - 1) * 5;
            int endIndex = Math.min(startIndex + 5, listOfMatches.size());
            matches = listOfMatches.subList(startIndex, endIndex);
            paginationResultDTO.setHasNextPage(listOfMatches.size() > page * 5);
        } else {
            throw new RuntimeException("FinishedMatchesPersistenceService is not working properly");
        }

        paginationResultDTO.setListOfFinishedMatches(matches);
        return paginationResultDTO;
    }

}
