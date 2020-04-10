package com.dooditrol.snake.game_logic;


import java.util.ArrayList;


public class Snake {

    ArrayList<SnakeBodyPart> body;
    MoveDirection direction;
    
    public Snake(int x, int y) {
        
        direction = MoveDirection.RIGHT;
        body = new ArrayList<SnakeBodyPart>();
        body.add(new SnakeBodyPart(x, y));
        body.add(new SnakeBodyPart(x - 1, y));
        body.add(new SnakeBodyPart(x - 2, y));        
    }
    
    public void ChangeDirection(MoveDirection newDirection) {
        
        if (((newDirection == MoveDirection.UP) && (direction != MoveDirection.DOWN))
                || ((newDirection == MoveDirection.DOWN) && (direction != MoveDirection.UP))
                || ((newDirection == MoveDirection.LEFT) && (direction != MoveDirection.RIGHT))
                || ((newDirection == MoveDirection.RIGHT) && (direction != MoveDirection.LEFT))) {
            
            direction = newDirection;
        }
    }
    
    public MoveDirection getDirection() {
        
        return direction;
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
    
    protected void move() {
        
        SnakeBodyPart tail = body.remove(body.size() - 1);
        
        switch (direction) {
            case UP:
                tail.setX(body.get(0).getX());
                tail.setY(body.get(0).getY() - 1);
                break;
            case DOWN:
                tail.setX(body.get(0).getX());
                tail.setY(body.get(0).getY() + 1);
                break;
            case LEFT:
                tail.setX(body.get(0).getX() - 1);
                tail.setY(body.get(0).getY());
                break;
            case RIGHT:
                tail.setX(body.get(0).getX() + 1);
                tail.setY(body.get(0).getY());
                break;
        }
        
        body.add(0, tail);
    }
    
    protected void eat() {
        
        SnakeBodyPart newHead = new SnakeBodyPart(0, 0);
        
        switch (direction) {
            case UP:
                newHead.setX(body.get(0).getX());
                newHead.setY(body.get(0).getY() - 1);
                break;
            case DOWN:
                newHead.setX(body.get(0).getX());
                newHead.setY(body.get(0).getY() + 1);
                break;
            case LEFT:
                newHead.setX(body.get(0).getX() - 1);
                newHead.setY(body.get(0).getY());
                break;
            case RIGHT:
                newHead.setX(body.get(0).getX() + 1);
                newHead.setY(body.get(0).getY());
                break;
        }
        
        body.add(0, newHead);
    }
}
