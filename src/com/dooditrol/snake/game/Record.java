package com.dooditrol.snake.game;


import java.io.Serializable;

public class Record implements Serializable {
    
    private static final long serialVersionUID = 10256L;
    
    private String name;
    private int score;
    
    
    public Record(String name, int score) {
        
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        
        return name;
    }
    
    public int getScore() {
        
        return score;
    }
}
