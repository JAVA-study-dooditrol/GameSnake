package com.dooditrol.snake.game;

public class Fruit extends GameObject{

    private TypeFruit type;
    
    public Fruit(int x, int y, TypeFruit type) {
        
        super(x, y);
        this.type = type;
    }
    
    public TypeFruit getType() {
        
        return type;
    }
    
    public int getPrice() {
        
        return type.getPrice();
    }
}