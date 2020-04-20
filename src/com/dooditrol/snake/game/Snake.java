package com.dooditrol.snake.game;


import java.util.ArrayList;


public class Snake {

    private ArrayList<SnakeBodyPart> body;
    private MoveDirection direction;
    private int widthField;
    private int heightField;
    
    
    public Snake(int x, int y, int width, int height) {
        
        widthField = width;
        heightField = height;
        direction = MoveDirection.RIGHT;
        body = new ArrayList<SnakeBodyPart>();
        body.add(new SnakeBodyPart(x, y, widthField, heightField));
        body.add(new SnakeBodyPart(x - 1, y, widthField, heightField));
        body.add(new SnakeBodyPart(x - 2, y, widthField, heightField));        
    }
    
    public SnakeBodyPart getHead() {
        
        return body.get(0).clone();
    }
    
    public ArrayList<SnakeBodyPart> getBody() {
        
        return body;
    }
    
    public int getLenght() {
        
        return body.size();
    }
    
    public boolean isSelfBitten() {
        
        SnakeBodyPart snakeHead = body.get(0);
        for (SnakeBodyPart snakePart : body) {
            
            if (snakeHead.isCollision(snakePart) && !snakeHead.equals(snakePart)) {
                return true;
            }
        }
        return false;
    }
    
    public MoveDirection getDirection() {
        
        return direction;
    }
    
    protected void ChangeDirection(MoveDirection newDirection) {
        
        if (((newDirection == MoveDirection.UP) && (direction != MoveDirection.DOWN))
                || ((newDirection == MoveDirection.DOWN) && (direction != MoveDirection.UP))
                || ((newDirection == MoveDirection.LEFT) && (direction != MoveDirection.RIGHT))
                || ((newDirection == MoveDirection.RIGHT) && (direction != MoveDirection.LEFT))) {
            
            direction = newDirection;
        }
    }
    
    protected void move() {
        
        body.remove(body.size() - 1);
        SnakeBodyPart newHead = body.get(0).clone();
        newHead.move(direction);        
        body.add(0, newHead);
    }
    
    protected void eat() {
        
        SnakeBodyPart newHead = body.get(0).clone();
        newHead.move(direction);
        body.add(0, newHead);
    }
}
