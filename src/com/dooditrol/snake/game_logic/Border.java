package com.dooditrol.snake.game_logic;


public class Border extends GameObject {

    private int width;
    private int height;
    
    Border(int x, int y, int width, int height) {
        
        super(x, y);
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        
        return width;
    }
    
    public int getHeight() {
        
        return height;
    }
    
    public int getSquare() {
        
        return width * height;
    }
}

