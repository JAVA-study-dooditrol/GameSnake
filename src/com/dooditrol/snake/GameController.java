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
    private boolean escapePressed;
    private boolean rPressed;
    
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
    
    
    {
        escapePressed = false;
        rPressed = false;
    }
    
    public void setGame(Game game) {
        
        this.game = game;
    }
    
    @FXML
    private void playerPressedKey(KeyEvent event) {
        
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
                        if (!rPressed) {
                            rPressed = true;
                            game.restart();
                        }
                        break;
                    case ESCAPE:
                        if (!escapePressed) {
                            escapePressed = true;
                            game.pause();
                        }
                        break;
                    default:
                        break;
                }
                break;
            case PAUSE:
                switch (event.getCode()) {
                    case ESCAPE:
                        if (!escapePressed) {
                            escapePressed = true;
                            game.exitPause();
                        }
                        break;
                    case R:
                        if (!rPressed) {
                            rPressed = true;
                            game.start();
                        }
                        break;
                    default:
                        break;
                }
                break;
            case GAME_OVER:
            case GAME_END:
                switch (event.getCode()) {
                    case R:
                        rPressed = true;
                    case ENTER:
                        game.start();
                    default:
                        break;
                }
                break;
        }            
    }
    
    @FXML
    private void playerReleasedKey(KeyEvent event) {
        
        if (inputRecord.isVisible()) {
            return;
        }
        
        switch (game.getGameState()) {
            case GAME_PROCESS:
                switch (event.getCode()) {
                    case R:
                        rPressed = false;
                        break;
                    case ESCAPE:
                        escapePressed = false;
                        break;
                    default:
                        break;
                }
                break;
            case PAUSE:
                switch (event.getCode()) {
                    case ESCAPE:
                        escapePressed = false;
                        break;
                    case R:
                        rPressed = false;
                        break;
                    default:
                        break;
                }
                break;
            case GAME_OVER:
            case GAME_END:
                switch (event.getCode()) {
                    case R:
                        rPressed = false;
                    default:
                        break;
                }
                break;
            default:
                break;
        } 
    }
    
    @FXML
    private void clickEnterButton(ActionEvent e) {
        
        String name = field.getCharacters().toString();
        
        if (name.length() > 0) {
            game.addNewRecord(name);
            inputRecord.setVisible(false);
            field.clear();
        }
    }
    
    @FXML
    private void clickRefuseButton(ActionEvent e) {
        
        inputRecord.setVisible(false);
        field.clear();
    }
}
