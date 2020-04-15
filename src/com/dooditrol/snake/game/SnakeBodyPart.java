package com.dooditrol.snake.game;


public class SnakeBodyPart extends GameObject {

    public SnakeBodyPart(int x, int y) {
        
        super(x, y);
    }
    
    public SnakeBodyPart clone() {
        
        return new SnakeBodyPart(getX(), getY());
    }
    
    protected void move(MoveDirection direction) {
        
        switch (direction) {
            case LEFT:
                setX(getX() - 1);
                break;
            case RIGHT:
                setX(getX() + 1);
                break;
            case UP:
                setY(getY() - 1);
                break;
            case DOWN:
                setY(getY() + 1);
                break;
        }
    }
    
    protected boolean isCollision(Border border) {
        
        if ((border.getX() <= getX()) && (getX() <= (border.getX() + border.getWidth() - 1))
                && (border.getY() <= getY()) && (getY() <= (border.getY() + border.getHeight() - 1))) {
            
            return true;
        }
        else {
            return false;
        }
    }
    
    protected boolean isCollision(GameObject object) {
        
        if ((getX() == object.getX()) && (getY() == object.getY())) {
            return true;
        }
        else {
            return false;
        }
    }
}
