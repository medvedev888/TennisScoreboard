package me.vladislav.tennis_scoreboard.dto;

import lombok.Data;
import lombok.NonNull;
import me.vladislav.tennis_scoreboard.models.Match;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationResultDataAccessObject {
    private final List<Match> listOfFinishedMatches = new ArrayList<Match>(5);
    @NonNull
    private int currentPage;
    @NonNull
    private boolean hasNextPage;
}
