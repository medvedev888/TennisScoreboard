package me.vladislav.tennisscoreboard.dto;

import lombok.*;
import me.vladislav.tennisscoreboard.models.Player;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CurrentMatch {
    private UUID id;
    private Player player1;
    private Player player2;
}
