package com.dooditrol.snake.game;


public class SnakeBodyPart extends GameObject {

    private int widthField;
    private int heightField;
    
    
    public SnakeBodyPart(int x, int y, int width, int height) {
        
        super(x, y);
        widthField = width;
        heightField = height;
    }
    
    public SnakeBodyPart clone() {
        
        return new SnakeBodyPart(getX(), getY(), widthField, heightField);
    }
    
    protected void move(MoveDirection direction) {
        
        switch (direction) {
            case LEFT:
                setX(getX() - 1);
                
                if (getX() < 0) {
                    setX(widthField - 1);
                }
                break;
            case RIGHT:
                setX(getX() + 1);
                
                if (getX() > (widthField - 1)) {
                    setX(0);
                }
                break;
            case UP:
                setY(getY() - 1);
                
                if (getY() < 0) {
                    setY(heightField - 1);
                }
                break;
            case DOWN:
                setY(getY() + 1);
                
                if (getY() > (heightField - 1)) {
                    setY(0);
                }
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
