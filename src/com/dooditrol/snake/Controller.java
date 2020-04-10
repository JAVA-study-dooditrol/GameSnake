package com.dooditrol.snake;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;


import com.dooditrol.snake.game_logic.MoveDirection;
import com.dooditrol.snake.game_logic.Game;


public class Controller implements Initializable {
    
    private View view;
    private Game game;
    
    private AnimationTimer timer;
    
    @FXML
    private Canvas canvas;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //game = new Game(8, 4);
        game = new Game(32, 22);
        view = new View(canvas);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
    
    @FXML
    private void playerPressedKey(KeyEvent event) {
        
        if (event.getCode() == KeyCode.LEFT) {
            
        }
        else if (event.getCode() == KeyCode.RIGHT) {

        }
    }
    
    @FXML
    private void playerReleasedKey(KeyEvent event) {
        
        switch (event.getCode()) {
            case UP:
                game.ChangeDirection(MoveDirection.UP);
                break;
            case DOWN:
                game.ChangeDirection(MoveDirection.DOWN);
                break;
            case LEFT:
                game.ChangeDirection(MoveDirection.LEFT);
                break;
            case RIGHT:
                game.ChangeDirection(MoveDirection.RIGHT);
                break;
            case R:
                game.restart();
                break;
            case ESCAPE:
                game.pause();
                break;
            case ENTER:
                game.start();
            default:
                
                break;
        }
            
    }
    
    private void update() {
        
        game.update();
        view.draw(game);
    }
}
