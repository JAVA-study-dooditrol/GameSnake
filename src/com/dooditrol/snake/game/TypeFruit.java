package com.dooditrol.snake.game;

public enum TypeFruit {
    
    APPLE(10), ORANGE(20), PLUM(30);
    
    private int price;
    
    TypeFruit(int price) {
        
        this.price = price;
    }
    
    public int getPrice() {
        
        return price;
    }
}
