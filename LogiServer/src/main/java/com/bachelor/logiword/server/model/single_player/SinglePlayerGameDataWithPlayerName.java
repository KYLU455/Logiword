package com.bachelor.logiword.server.model.single_player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SinglePlayerGameDataWithPlayerName {

        private String playerName;
        private String wordCreated;
        private int score;

        public SinglePlayerGameDataWithPlayerName(){}

        public SinglePlayerGameDataWithPlayerName(@JsonProperty("playerName") String playerName,
                                                  @JsonProperty("wordCreated") String wordCreated,
                                                  @JsonProperty("score") int score) {
            this.playerName = playerName;
            this.wordCreated = wordCreated;
            this.score = score;
        }

        public String getPlayerName(){return playerName;}

        public String getWordCreated() {
            return wordCreated;
        }

        public int getScore() {
            return score;
        }

    }
