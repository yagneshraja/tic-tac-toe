package org.example.models;

import org.example.strategies.botPlayingStrategy.BotPlayingStartegy;
import org.example.strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class Bot  extends Player{
    private BotPlayingDifficulty botPlayingDifficulty;
    private BotPlayingStartegy botPlayingStartegy = new RandomBotPlayingStrategy();

    public BotPlayingDifficulty getBotPlayingDifficulty() {
        return botPlayingDifficulty;
    }

    public void setBotPlayingDifficulty(BotPlayingDifficulty botPlayingDifficulty) {
        this.botPlayingDifficulty = botPlayingDifficulty;
    }


    public Bot(String name, String symbol, PlayerType playerType, BotPlayingDifficulty botPlayingDifficulty) {
        super(name, symbol, playerType);
        this.botPlayingDifficulty = botPlayingDifficulty;
    }

    public BotPlayingStartegy getBotPlayingStartegy() {
        return botPlayingStartegy;
    }

    public void setBotPlayingStartegy(BotPlayingStartegy botPlayingStartegy) {
        this.botPlayingStartegy = botPlayingStartegy;
    }
    @Override
    public Move decideMove(Board board) {
        return botPlayingStartegy.decideMove(this, board);
    }
}
