package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Hallway implements Graph{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int orientation;
    private final int WIDTH = 80;
    private final int HEIGHT = 30;
    private final int horizontal = 0;
    private final int vertical = 1;
    public Hallway(Random r){
        orientation = RandomUtils.uniform(r, 0, 2);
        if(orientation == horizontal){
            height = 3;
            xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
            yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
            width = RandomUtils.uniform(r, 0, WIDTH / 2);
            while(xPos + width >= WIDTH || yPos + height >= HEIGHT){
                xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
                yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
                width = RandomUtils.uniform(r, 0, WIDTH / 3);
            }
        }
        if(orientation == vertical){
            width = 3;
            xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
            yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
            height = RandomUtils.uniform(r, 0, HEIGHT / 2);
            while(xPos + width >= WIDTH || yPos + height >= HEIGHT){
                xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
                yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
                height = RandomUtils.uniform(r, 0, HEIGHT / 3);
            }
        }
    }

    public void drawWall(TETile[][] wallWorld){
        for(int x = 0; x < width; x += 1){
            wallWorld[xPos + x][yPos] = Tileset.WALL;
            wallWorld[xPos + x][yPos + height - 1] = Tileset.WALL;
        }
        for(int y = 0; y < height; y += 1){
            wallWorld[xPos][yPos + y] = Tileset.WALL;
            wallWorld[xPos + width - 1][yPos + y] = Tileset.WALL;
        }


    }

    public void drawFloor(TETile[][] floorWorld){
        // fill the inner part of the hallway
        for(int x = 1; x < width - 1; x += 1){
            for(int y = 1; y < height - 1; y += 1){
                floorWorld[xPos + x][yPos + y] = Tileset.FLOOR;
            }
        }
    }
}
