package com.dooditrol.snake.game;


public abstract class GameObject {
    
    private int x;
    private int y;
    
    GameObject(int x, int y) {
        
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        
        return x;
    }
    
    public int getY() {
        
        return y;
    }
    
    protected void setX(int x) {
        
        this.x = x;
    }
    
    protected void setY(int y) {
        
        this.y = y;
    }
}
