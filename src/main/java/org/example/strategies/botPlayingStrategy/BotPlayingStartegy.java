package org.example.strategies.botPlayingStrategy;

import org.example.models.Board;
import org.example.models.Bot;
import org.example.models.Move;

public interface BotPlayingStartegy {
    Move decideMove(Bot bot, Board board);
}
