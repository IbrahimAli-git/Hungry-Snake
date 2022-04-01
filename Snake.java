package com.codegym.games.snake;

import com.codegym.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
    private static final String HEAD_SIGN = "\\uD83D\\uDC7E";
    private static final String BODY_SIGN = "\\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();


    public Snake(int x, int y){
        super(x, y);
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x+1, y);
        GameObject gameObject3 = new GameObject(x+2, y);
        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);
    }

    public void draw(Game game){
        for (GameObject gameObject : snakeParts) {
            if (snakeParts.indexOf(gameObject) == 0){
                game.setCellValue(gameObject.x, gameObject.y, HEAD_SIGN);
            } else {
                game.setCellValue(gameObject.x, gameObject.y, BODY_SIGN);
            }
        }
    }
}

