package com.dooditrol.snake;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;

import com.dooditrol.snake.game.Game;
import com.dooditrol.snake.game.MoveDirection;


public class GameController {
    
    private Game game;
    
    @FXML
    private Canvas canvas;
    @FXML
    private VBox inputRecord;
    @FXML
    private Button enterButton;
    @FXML
    private Button refuseButton;
    @FXML
    private TextField field;
    
    public void setGame(Game game) {
        
        this.game = game;
    }
    
    @FXML
    private void playerReleasedKey(KeyEvent event) {
        
        if (inputRecord.isVisible()) {
            return;
        }
        
        switch (game.getGameState()) {
            case GAME_START:
                switch (event.getCode()) {
                    case ENTER:
                        game.start();
                    default:
                        break;
                }
                break;
            case GAME_PROCESS:
                switch (event.getCode()) {
                    case UP:
                        game.ChangeDirectionSnake(MoveDirection.UP);
                        break;
                    case DOWN:
                        game.ChangeDirectionSnake(MoveDirection.DOWN);
                        break;
                    case LEFT:
                        game.ChangeDirectionSnake(MoveDirection.LEFT);
                        break;
                    case RIGHT:
                        game.ChangeDirectionSnake(MoveDirection.RIGHT);
                        break;
                    case R:
                        game.restart();
                        break;
                    case ESCAPE:
                        game.pause();
                        break;
                    default:
                        break;
                }
                break;
            case PAUSE:
                switch (event.getCode()) {
                    case ESCAPE:
                        game.exitPause();
                        break;
                    case R:
                        game.start();
                        break;
                    default:
                        break;
                }
                break;
            case GAME_OVER:
            case GAME_END:
                switch (event.getCode()) {
                    case R:
                    case ENTER:
                        game.start();
                    default:
                        break;
                }
                break;
        }            
    }
    
    @FXML
    private void clickEnterButton(ActionEvent e) {
        
        game.addNewRecord(field.getCharacters().toString());
        inputRecord.setVisible(false);
    }
    
    @FXML
    private void clickRefuseButton(ActionEvent e) {
        
        inputRecord.setVisible(false);
    }
}
