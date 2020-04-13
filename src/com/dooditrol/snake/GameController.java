package com.dooditrol.snake;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


import com.dooditrol.snake.game_logic.MoveDirection;
import com.dooditrol.snake.game_logic.Game;


public class GameController {
    
    private Game game;
    
    @FXML
    private Canvas canvas;
    
    
    public void setGame(Game game) {
        
        this.game = game;
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
}
