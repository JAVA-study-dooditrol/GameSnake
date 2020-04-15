package com.dooditrol.snake;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.VPos;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

import com.dooditrol.snake.game.Border;
import com.dooditrol.snake.game.Fruit;
import com.dooditrol.snake.game.Game;
import com.dooditrol.snake.game.Snake;
import com.dooditrol.snake.game.SnakeBodyPart;
import com.dooditrol.snake.game.Record;


public class GameView {
    
    private double canvasWidth;
    private double canvasHeight;
    private double unitSize;
    
    private Game game;
    private Stage stage;
    private GraphicsContext gc;
    private AnimationTimer timer;
    
    private boolean fieldInputRecordIsDisplaing;
    
    {
        fieldInputRecordIsDisplaing = false;
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    draw();
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        };
    }
    
    public GameView(Stage initStage, Game initGame) throws Exception {
        
        this.game = initGame;
        this.stage = initStage;
        
        Canvas canvas = (Canvas) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();  
        unitSize = canvasWidth / game.getFieldWidth();
        
        gc = canvas.getGraphicsContext2D();

        timer.start();
        
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Snake");
        stage.show();
    }
    
    private void draw() {
        
        ArrayList<Border> borders = game.getBorders();
        
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
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
            
            draw(border);
        }
        
        switch (game.getGameState()) {
        
            case GAME_START:
                drawGameStart();
                break;
            case GAME_PROCESS:
                drawGameProcess();
                break;
            case PAUSE:
                drawPause();
                break;
            case GAME_OVER:
                drawGameOver();
                break;
            case GAME_END:
                drawGameEnd();
                break;
        }
    }
    
    private void drawGameStart() {
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFill(Color.RED);
        gc.setFont(new Font("impact", 3 * unitSize));
        gc.fillText("SNAKE", canvasWidth / 2, canvasHeight / 2);
        
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("impact", unitSize));
        gc.fillText("press Enter to start", canvasWidth / 2, canvasHeight / 2 + 0.5 * unitSize);
    }
    
    private void drawGameProcess() {
        
        fieldInputRecordIsDisplaing = false;

        draw(game.getSnake());
        draw(game.getFruits());
    }
    
    private void drawPause() {
        
        draw(game.getSnake());
        draw(game.getFruits());
        
        gc.setFill(Color.color(0.21, 0.18, 0.14, 0.05));
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
        gc.setFill(Color.color(0.144, 0.144, 0.144, 0.8));
        gc.fillRect(canvasWidth / 2 - 8 * unitSize, canvasHeight / 2 - 4.5 * unitSize,
                canvasWidth / 2, 3.5 * unitSize);
        
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
        
        draw(game.getRecords(), canvasWidth / 2, canvasHeight / 2 - 0.5 *unitSize);
    }
    
    private void drawGameOver() {
        
        draw(game.getSnake());
        draw(game.getFruits());
        
        gc.setFill(Color.color(0.21, 0.18, 0.14, 0.1));
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
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
        
        if (game.isNewRecord() && !fieldInputRecordIsDisplaing) {
            
            ((VBox) stage.getScene().getRoot().getChildrenUnmodifiable().get(1)).setVisible(true);
            fieldInputRecordIsDisplaing = true;
        }
    }
    
    private void drawGameEnd() {
        
        draw(game.getSnake());
        
        gc.setFill(Color.color(0.21, 0.18, 0.14, 0.1));
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
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
        
        if (game.isNewRecord() && !fieldInputRecordIsDisplaing) {
            
            ((VBox) stage.getScene().getRoot().getChildrenUnmodifiable().get(1)).setVisible(true);
            fieldInputRecordIsDisplaing = true;
        }
    }
    
    private void draw(Border border) {

        gc.setFill(Color.GREY);
        gc.fillRect(border.getX() * unitSize, 2 * unitSize + border.getY() * unitSize,
                border.getWidth() * unitSize, border.getHeight() * unitSize);
    }
    
    private void draw(Snake snake) {
        
        gc.setFill(Color.OLIVEDRAB);
        
        for (SnakeBodyPart snakePart : snake.getBody()) {
            /*gc.arc(snakePart.getX() * unitSize + unitSize / 2, 
                    snakePart.getY() * unitSize + unitSize / 2, 
                    unitSize / 2, unitSize / 2, 0, 360);*/
            gc.fillOval(snakePart.getX() * unitSize, 2 * unitSize + snakePart.getY() * unitSize,
                    unitSize, unitSize);
        }
        
        SnakeBodyPart snakeHead = snake.getHead();
        
        gc.setFill(Color.OLIVEDRAB);
       /* gc.arc(snakeHead.getX() * unitSize + unitSize / 2, 
                snakeHead.getY() * unitSize + unitSize / 2, 
                unitSize / 2, unitSize / 2, 0, 360);*/
        gc.fillOval(snakeHead.getX() * unitSize, 2 * unitSize + snakeHead.getY() * unitSize, 
                unitSize, unitSize);
    }
    
    private void draw(ArrayList<Fruit> fruits) {
        
        for (Fruit fruit : fruits) {
            
            switch (fruit.getType()) {
                
                case APPLE:
                    drawApple(fruit);
                    break;
                case ORANGE:
                    drawOrange(fruit);
                    break;
                case PLUM:
                    drawPlum(fruit);
                    break;
            }
        }
    }
    
    private void drawApple(Fruit apple) {
        
        gc.setFill(Color.GREEN);
        gc.fillOval(apple.getX() * unitSize, 2 * unitSize + apple.getY() * unitSize,
                unitSize, unitSize);
    }
    
    private void drawOrange(Fruit orange) {
        
        gc.setFill(Color.YELLOW);
        gc.fillOval(orange.getX() * unitSize, 2 * unitSize + orange.getY() * unitSize,
                unitSize, unitSize);
    }
    
    private void drawPlum(Fruit banana) {
        
        gc.setFill(Color.NAVY);
        gc.fillOval(banana.getX() * unitSize, 2 * unitSize + banana.getY() * unitSize,
                unitSize, unitSize);
    }
    
    private void draw(ArrayList<Record> records, double x, double y) {
        
        double heightOfTable = records.size() * (0.8 * unitSize) + unitSize;
        double widthOfTable = 8 * unitSize;
        
        gc.setFill(Color.color(0.144, 0.144, 0.144, 0.8));
        gc.fillRect(x - widthOfTable / 2, y, widthOfTable, heightOfTable);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.TOP);
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("impact", unitSize));
        gc.fillText("HIGH SCORE TABLE", x, y + 0.2 * unitSize);
        
        double leftX = x - widthOfTable / 2 +  0.2 * unitSize;;
        double rightX = x + widthOfTable / 2 -  0.2 * unitSize;;
        double curY = y + 1.2 * unitSize;
        int number = 1;
        
        gc.setFont(new Font("impact", 0.7 * unitSize));
        
        for (Record record : records) {
            
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFill(Color.BLUE);
            gc.fillText(Integer.toString(number) + ".", leftX, curY);
            
            if (record.getScore() != 0) {
                gc.setFill(Color.FIREBRICK);
                gc.setTextAlign(TextAlignment.LEFT);
                gc.fillText(record.getName(), leftX + unitSize, curY);
                
                gc.setFill(Color.RED);
                gc.setTextAlign(TextAlignment.RIGHT);
                gc.fillText(Integer.toString(record.getScore()), rightX, curY);
            }
            
            curY += 0.7 * unitSize;
            number++;
        }   
    }
}
