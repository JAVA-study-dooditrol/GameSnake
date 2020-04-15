package com.dooditrol.snake.game;


import java.util.ArrayList;
import java.util.Random;


public class Game implements Runnable{
     
    private int fieldWidth;
    private int fieldHeight;
    
    private Snake snake;
    private ArrayList<Border> borders;
    private int squareOfBorders;
    private ArrayList<Fruit> fruits;
    
    private GameState gameState;  
    private int score;
    private boolean newRecord;
    private Random random;
    
    private final String fileNameOfRecordTable = "recordTable.txt";
    private TableOfRecords tableOfRecords;
    
    {
        random = new Random();
        tableOfRecords = new TableOfRecords(fileNameOfRecordTable);
        newRecord = false;
        score = 0;
        gameState = GameState.GAME_START;
    }
    
    public Game(int width, int height) {
        
        this.fieldHeight = height;
        this.fieldWidth = width;
        
        borders = new ArrayList<Border>();
        
        borders.add(new Border(0, 0, fieldWidth, 1));
        borders.add(new Border(0, fieldHeight - 1, fieldWidth, 1));
        borders.add(new Border(0, 1, 1, fieldHeight - 2));
        borders.add(new Border(fieldWidth - 1, 1, 1, fieldHeight - 2));
        
        squareOfBorders = 0;
        
        for (Border border : borders) {
            squareOfBorders += border.getSquare();
        }
    }
    
    public int getFieldHeight() {
        
        return fieldHeight;
    }

    public int getFieldWidth() {
        
        return fieldWidth;
    }
    
    public ArrayList<Border> getBorders() {
        
        return borders;
    }
    
    public Snake getSnake() {
        
        return snake;
    }
    
    public ArrayList<Fruit> getFruits() {
        
        return fruits;
    }
    
    public GameState getGameState() {
        
        return gameState;
    }
    
    public int getScore() {
        
        return score;
    }
    
    public boolean isNewRecord() {
        
        return newRecord;
    }
    
    public ArrayList<Record> getRecords() {
        
        return tableOfRecords.getRecords();
    }
    
    public void ChangeDirectionSnake(MoveDirection newDirection) {
        
        snake.ChangeDirection(newDirection);
    }
    
    public synchronized void start() {
        
        toStartGame();
        notify();
    }
    
    public void restart() {
        
        toStartGame(); 
    }
    
    public void pause() {
        
        gameState = GameState.PAUSE;
    }
    
    public synchronized void exitPause() {
        
        gameState = GameState.GAME_PROCESS;
        notify();
    }
    
    public void addNewRecord(String name) {
        
        tableOfRecords.addNewRecord(name, score);
    }
    
    @Override
    public synchronized void run() {
        
        while (true) {
            
            try {
                switch (gameState) {
                    case GAME_START:
                    case PAUSE:
                        wait();
                        break;
                    case GAME_OVER:
                    case GAME_END:
                        if (tableOfRecords.isNewRecord(score)) {
                            newRecord = true;
                        }
                        wait();
                        break;
                    case GAME_PROCESS:
                        updateGameField();
                        Thread.sleep(180);
                        break;
                }
            }
            catch (InterruptedException ex) {
                System.out.println("Thread has been interrupted");
            }
        }

    }
        
    private void updateGameField() {
        
        SnakeBodyPart snakeHead = snake.getHead();
        
        switch (snake.getDirection()) {
            case UP:
                snakeHead.setY(snakeHead.getY() - 1);
                break;
            case DOWN:
                snakeHead.setY(snakeHead.getY() + 1);
                break;
            case LEFT:
                snakeHead.setX(snakeHead.getX() - 1);
                break;
            case RIGHT:
                snakeHead.setX(snakeHead.getX() + 1);
                break;
        }
        
        for (Border border : borders) {
            
            if (snakeHead.isCollision(border)) {
                gameState = GameState.GAME_OVER;
                return;
            }
        }
        
        boolean snakeAte = false;
        
        for (int i = 0; i < fruits.size(); ++i) {
            
            if (snakeHead.isCollision(fruits.get(i))) {
                snake.eat();
                Fruit removedFruits = fruits.remove(i);
                score += removedFruits.getPrice();
                
                if (fruits.size() == 0) {
                    gameState = GameState.GAME_END;
 
                }
                else if (0 < (fieldWidth * fieldHeight 
                        - (squareOfBorders + snake.getLenght() + fruits.size()))) {
                    fruits.add(toPlaceNewFruit(removedFruits.getType()));
                }
                
                snakeAte = true;
                break;
            }
        }
 
        if (!snakeAte) {
            snake.move();
        }
        
        if (snake.isSelfBitten()) {
            gameState = GameState.GAME_OVER;
            return;
        }
    }
    
    private Fruit toPlaceNewFruit(TypeFruit typeFruit) {
        
        boolean[][] gameField = new boolean[fieldHeight][fieldWidth];
        
        for (int y = 0; y < fieldHeight; ++y) {
            for (int x = 0; x < fieldWidth; ++x) {
                
                gameField[y][x] = false;
            }
        }
        
        for (SnakeBodyPart snakePart : snake.getBody()) {
            
            gameField[snakePart.getY()][snakePart.getX()] = true;
        }
        
        for (Border border : borders) {
            
            for (int y = border.getY(); y < border.getY() + border.getHeight(); ++y) {
                for (int x = border.getX(); x < border.getX() + border.getWidth(); ++x) {
                    
                    gameField[y][x] = true;
                }
            }
            
        }
        
        for (Fruit fruit : fruits) {
            
            gameField[fruit.getY()][fruit.getX()] = true;
        }
        
        int newFruitX = random.nextInt(fieldWidth);
        int newFruitY = random.nextInt(fieldHeight);
        
        while (true) {
            
            if (!gameField[newFruitY][newFruitX]) {
                break;
            }
            else {
                newFruitX = random.nextInt(fieldWidth);
                newFruitY = random.nextInt(fieldHeight);
            }
        }
        return new Fruit(newFruitX, newFruitY, typeFruit);
    }
    
    private void toStartGame() {
        
        snake = new Snake(fieldWidth / 2, fieldHeight / 2);
        fruits = new ArrayList<Fruit>();
        
        fruits.add(toPlaceNewFruit(TypeFruit.APPLE));
        fruits.add(toPlaceNewFruit(TypeFruit.ORANGE));
        fruits.add(toPlaceNewFruit(TypeFruit.PLUM));
        
        score = 0;
        newRecord = false;
        gameState = GameState.GAME_PROCESS;
    }
}