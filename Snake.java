package com.codegym.games.snake;

import com.codegym.engine.cell.*;


import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
    private static final String HEAD_SIGN = "\\uD83D\\uDC7E";
    private static final String BODY_SIGN = "\\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x, y);
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x + 1, y);
        GameObject gameObject3 = new GameObject(x + 2, y);
        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);
    }

    public void setDirection(Direction direction) {
        boolean isValidDirection = (this.direction != Direction.UP || direction != Direction.DOWN) &&
                                   (this.direction != Direction.LEFT || direction != Direction.RIGHT) &&
                                   (this.direction != Direction.RIGHT || direction != Direction.LEFT) &&
                                   (this.direction != Direction.DOWN || direction != Direction.UP);
        if (isValidDirection) {
            this.direction = direction;
        }
    }

    public void move() {
        GameObject gameObject = createNewHead();

        if (gameObject.x < 0 || gameObject.x >= SnakeGame.WIDTH || gameObject.y < 0 || gameObject.y >= SnakeGame.HEIGHT){
            isAlive = false;
        } else {
            snakeParts.add(0, gameObject);
            removeTail();
        }
    }

    public GameObject createNewHead(){
        GameObject newHead = snakeParts.get(0);
        switch (direction){
            case UP: return new GameObject(newHead.x, newHead.y-1);
            case RIGHT: return new GameObject(newHead.x+1, newHead.y);
            case DOWN: return new GameObject(newHead.x, newHead.y+1);
            case LEFT: return new GameObject(newHead.x-1, newHead.y);
        }
        return newHead;
    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size()-1);
    }

    public void draw(Game game) {
        for (GameObject gameObject : snakeParts) {
            if (snakeParts.indexOf(gameObject) == 0) {
                if (isAlive) {
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                }
            } else {
                if (isAlive) {
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, BODY_SIGN, Color.RED, 75);
                }
            }
        }
    }
}
