package byog.lab5;
import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    /*
    * @param s size of hexagon
    * @param t type of tile
    * */
    public static void addHexagon(TETile world[][], int xPos, int yPos, int s, TETile t){
        if(s < 2){
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        for(int i = 1; i <= s * 2; i += 1){
            hexagonDrawRow(world, i, s, xPos, yPos, t);
        }
    }

    public static int hexagonRowLength(int s, int p){
        if(p > s){
            return s + 2 * (2 * s - p);
        }else{
            return s + 2 * (p - 1);
        }
    }
    /*
    * @param p which row are we drawing
    * @param s size of hexagon
    * @param t type of tile
    * */
    public static void hexagonDrawRow(TETile world[][], int p, int s, int xPos, int yPos, TETile t){
        for(int i = 0; i < hexagonRowLength(s, p); i += 1){
            if(p <= s){
                world[xPos + i - p + 1][yPos + p - 1] = t;
            }else{
                world[xPos + i + p - 2 * s][yPos + p - 1] = t;
            }

        }
    }

    @Test
    public void testHexagonRowLength(){
        assertEquals(4, hexagonRowLength(2, 3));
        assertEquals(7, hexagonRowLength(3, 3));
        assertEquals(7, hexagonRowLength(3, 4));
    }

    public static void main(String args[]){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for(int x = 0; x < WIDTH; x += 1){
            for(int y = 0; y < HEIGHT; y += 1){
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(world, 50, 50, 3, Tileset.FLOWER);
        addHexagon(world, 40, 40, 4, Tileset.WALL);

        ter.renderFrame(world);
    }
}


