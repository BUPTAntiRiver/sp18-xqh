package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room implements Graph{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private final int WIDTH = 80;
    private final int HEIGHT = 30;

    public Room(Random r){
        xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
        yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
        width = RandomUtils.uniform(r, 0, WIDTH / 4);
        height = RandomUtils.uniform(r, 0, HEIGHT / 4);
        while(xPos + width >= WIDTH || yPos + height >= HEIGHT){
            xPos = RandomUtils.uniform(r, 0, WIDTH / 2);
            yPos = RandomUtils.uniform(r, 0, HEIGHT / 2);
            width = RandomUtils.uniform(r, 0, WIDTH / 4);
            height = RandomUtils.uniform(r, 0, HEIGHT / 4);
        }
    }

    public Room(Random r, Graph g){
        int[] xy = g.getCenter();
        xPos = xy[0];
        yPos = xy[1];
        width = RandomUtils.uniform(r, 0, WIDTH / 4);
        height = RandomUtils.uniform(r, 0, HEIGHT / 4);
        while(xPos + width >= WIDTH || yPos + height >= HEIGHT){
            width = RandomUtils.uniform(r, 0, WIDTH / 4);
            height = RandomUtils.uniform(r, 0, HEIGHT / 4);
        }
    }

    @Override
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

    @Override
    public void drawFloor(TETile[][] floorWorld){
        // fill the inner part of the room
        for(int x = 1; x < width - 1; x += 1){
            for(int y = 1; y < height - 1; y += 1){
                floorWorld[xPos + x][yPos + y] = Tileset.FLOOR;
            }
        }
    }

    @Override
    public int getX(){return xPos;};

    @Override
    public int getY(){return yPos;};

    @Override
    public int getWidth(){return width;}

    @Override
    public int getHeight(){return height;}

    @Override
    public int[] getCenter() {
        int[] returnArr = new int[2];
        returnArr[0] = (xPos + width) / 2;
        returnArr[1] = (yPos + height) / 2;
        return returnArr;
    }
}
