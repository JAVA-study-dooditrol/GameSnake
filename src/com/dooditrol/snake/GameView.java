package com.dooditrol.snake;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;


import java.util.ArrayList;


import com.dooditrol.snake.game_logic.Game;
import com.dooditrol.snake.game_logic.Border;
import com.dooditrol.snake.game_logic.Snake;
import com.dooditrol.snake.game_logic.SnakeBodyPart;
import com.dooditrol.snake.game_logic.Fruit;


public class GameView {
    
    private double canvasWidth;
    private double canvasHeight;
    
    private GraphicsContext gc;
    
    public GameView(Canvas canvas) {
        
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        
        gc = canvas.getGraphicsContext2D();
    }
    
    public void draw(Game game) {
        
        double unitSize = canvasWidth / game.getFieldWidth();
        ArrayList<Border> borders = game.getBorders();
        
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, 2 * unitSize);
        
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 1.5 * unitSize));
        gc.fillText("SCORE:", 2 * unitSize, unitSize);
                
        gc.setFill(Color.WHITE);
        gc.fillText(Integer.toString(game.getScore()), 6.5 * unitSize, unitSize);
        
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font("impact", unitSize));
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("Pause - ESC", canvasWidth - unitSize, unitSize);
        
        for (Border border : borders) {
            
            draw(border, unitSize);
        }
        
        switch (game.getGameState()) {
        
            case GAME_START:
                drawGameStart(game, unitSize);
                break;
            case GAME_PROCESS:
                drawGameProcess(game, unitSize);
                break;
            case PAUSE:
                drawPause(game, unitSize);
                break;
            case GAME_OVER:
                drawGameOver(game, unitSize);
                break;
            case GAME_END:
                drawGameEnd(game, unitSize);
                break;
                
        }
    }
    
    private void drawGameStart(Game game, double unitSize) {
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 3 * unitSize));
        gc.fillText("SNAKE", canvasWidth / 2, canvasHeight / 2);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", unitSize));
        gc.fillText("press Enter to start", canvasWidth / 2, canvasHeight / 2 + 0.5 * unitSize);
    }
    
    private void drawGameProcess(Game game, double unitSize) {
        
        draw(game.getSnake(), unitSize);
        draw(game.getFruits(), unitSize);

    }
    
    private void drawPause(Game game, double unitSize) {
        
        draw(game.getSnake(), unitSize);
        draw(game.getFruits(), unitSize);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 3 * unitSize));
        gc.fillText("PAUSE", canvasWidth / 2, canvasHeight / 2 - 4 * unitSize);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", 1.5 * unitSize));
        gc.fillText("control:", canvasWidth / 2, canvasHeight / 2 - 3 * unitSize);
        gc.setFont(new Font("impact", 0.7 * unitSize));
        gc.fillText("Up, Down, Left, Right - movement direction of the snake", 
                canvasWidth / 2, canvasHeight / 2 - 2.5 * unitSize);
        gc.fillText("'R' - restart the game", 
                canvasWidth / 2, canvasHeight / 2 - 1.8 * unitSize);
        gc.fillText("'ECS' - pause", 
                canvasWidth / 2, canvasHeight / 2 - 1.1 * unitSize);
    }
    
    private void drawGameOver(Game game, double unitSize) {
        
        draw(game.getSnake(), unitSize);
        draw(game.getFruits(), unitSize);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 3 * unitSize));
        gc.fillText("GAME OVER", canvasWidth / 2, canvasHeight / 2);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", 1.5 * unitSize));
        gc.fillText("YOUR SCORE", canvasWidth / 2 - 1.75 * unitSize, canvasHeight / 2 + unitSize);
        gc.fillText(Integer.toString(game.getScore()), canvasWidth / 2 + 4.5 * unitSize, 
                canvasHeight / 2 + unitSize);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", unitSize));
        gc.fillText("press Enter or R to restart", canvasWidth / 2, canvasHeight / 2 + 1.8 * unitSize);
    }
    
    private void drawGameEnd(Game game, double unitSize) {
        
        draw(game.getSnake(), unitSize);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 3 * unitSize));
        gc.fillText("YOU ARE INCREDIBLE!", canvasWidth / 2, canvasHeight / 2);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", 1.5 * unitSize));
        gc.fillText("YOUR SCORE", canvasWidth / 2 - 1.75 * unitSize, canvasHeight / 2 + unitSize);
        gc.fillText(Integer.toString(game.getScore()), canvasWidth / 2 + 4.5 * unitSize, 
                canvasHeight / 2 + unitSize);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", unitSize));
        gc.fillText("press Enter or R to restart", canvasWidth / 2, canvasHeight / 2 + 1.8 * unitSize);
    }
    
    private void draw(Border border, double unitSize) {
        
        gc.setFill(Color.GREY);
        gc.fillRect(border.getX() * unitSize, 2 * unitSize + border.getY() * unitSize,
                border.getWidth() * unitSize, border.getHeight() * unitSize);
    }
    
    private void draw(Snake snake, double unitSize) {
        
        gc.setFill(Color.GREEN);
        
        for (SnakeBodyPart snakePart : snake.getBody()) {
            gc.beginPath();
            /*gc.arc(snakePart.getX() * unitSize + unitSize / 2, 
                    snakePart.getY() * unitSize + unitSize / 2, 
                    unitSize / 2, unitSize / 2, 0, 360);*/
            gc.fillOval(snakePart.getX() * unitSize, 2 * unitSize + snakePart.getY() * unitSize,
                    unitSize, unitSize);
            gc.fill();
            gc.closePath();
        }
        
        SnakeBodyPart snakeHead = snake.getHead();
        
        gc.beginPath();
        gc.setFill(Color.DARKOLIVEGREEN);
       /* gc.arc(snakeHead.getX() * unitSize + unitSize / 2, 
                snakeHead.getY() * unitSize + unitSize / 2, 
                unitSize / 2, unitSize / 2, 0, 360);*/
        gc.fillOval(snakeHead.getX() * unitSize, 2 * unitSize + snakeHead.getY() * unitSize, 
                unitSize, unitSize);
        gc.fill();
        gc.closePath();        
    }
    
    private void draw(ArrayList<Fruit> fruits, double unitSize) {
        
        for (Fruit fruit : fruits) {
            
            switch (fruit.getType()) {
                
                case APPLE:
                    drawApple(fruit, unitSize);
                    break;
                case ORANGE:
                    drawOrange(fruit, unitSize);
                    break;
                case BANANA:
                    drawBanana(fruit, unitSize);
                    break;
            }
        }
    }
    
    private void drawApple(Fruit apple, double unitSize) {
        
        gc.beginPath();
        gc.setFill(Color.GREEN);
        gc.fillOval(apple.getX() * unitSize, 2 * unitSize + apple.getY() * unitSize,
                unitSize, unitSize);
        gc.fill();
        gc.closePath();
    }
    
    private void drawOrange(Fruit orange, double unitSize) {
        
        gc.beginPath();
        gc.setFill(Color.YELLOW);
        gc.fillOval(orange.getX() * unitSize, 2 * unitSize + orange.getY() * unitSize,
                unitSize, unitSize);
        gc.fill();
        gc.closePath();
    }
    
    private void drawBanana(Fruit banana, double unitSize) {
        
        gc.beginPath();
        gc.setFill(Color.BLUE);
        gc.fillOval(banana.getX() * unitSize, 2 * unitSize + banana.getY() * unitSize,
                unitSize, unitSize);
        gc.fill();
        gc.closePath();
    }
}
