package com.dooditrol.snake;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import com.dooditrol.snake.game.Game;


public class Main extends Application {

    static private Game game;
    static private GameView gameView;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader gameSceneLoader = new FXMLLoader(getClass().
                getResource("..\\..\\..\\res\\GameScreenLayout.fxml"));        
        Scene gameScene = gameSceneLoader.load();
        ((GameController) gameSceneLoader.getController()).setGame(game);
        
        stage.setScene(gameScene);
        gameView = new GameView(stage, game);
    }
    
    public static void main(String[] args) {
        
        game = new Game(32, 22);
        Thread gameThread = new Thread(game, "GameThread");
        gameThread.setDaemon(true);
        gameThread.start();
        
        Application.launch(args);
    }
}