package com.dooditrol.snake;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;


import com.dooditrol.snake.game_logic.Game;


public class Main extends Application {

    private Game game;
    private GameView gameView;
    
    private AnimationTimer timer;

    
    @Override
    public void init() throws Exception {
        
        //game = new Game(8, 4);
        game = new Game(32, 22);
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    update();
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        };
        
        
        super.init();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loaderOfGameScene = new FXMLLoader(getClass().
                getResource("..\\..\\..\\res\\GameScreenLayout.fxml"));        
        Scene gameScene = loaderOfGameScene.load();

        GameController gameController = loaderOfGameScene.getController();
        gameController.setGame(game);
        
        
        Parent root = gameScene.getRoot();
        gameView = new GameView((Canvas)root.getChildrenUnmodifiable().get(0));
        
        stage.setScene(gameScene);
        stage.setTitle("Snake");
        stage.show();
        
        timer.start();
    }
    
    public static void main(String[] args) {
        
        Application.launch(args);
    }
    
    private void update() throws Exception {
        
        game.update();
        gameView.draw(game);
    }
}