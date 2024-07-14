package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


// import java.lang.classfile.instruction.BranchInstruction;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        /* Traverse input string to get selection and seed*/
        int seed = 0;
        switch (input.charAt(0)){
            case 'N':break;
            case 'Q':return null;
            case 'L':return null;
            default:return null;
        }
        for(int i = 1; i < input.length(); i += 1){
            if(input.charAt(i) == 'S'){
                break;
            }
            seed = seed * 10;
            seed += input.charAt(i) - '0';
        }

        Random r = new Random(seed);

        /**
         * @var finalWorldFrame is used to return
         * @var floorWorldFrame stores the floor information
         * @var wallWorldFrame stores the wall information
         * the floor and wall will merge to get the final frame.
         */
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        TETile[][] floorWorldFrame = new TETile[WIDTH][HEIGHT];
        TETile[][] wallWorldFrame = new TETile[WIDTH][HEIGHT];

        initTETile(finalWorldFrame);
        initTETile(floorWorldFrame);
        initTETile(wallWorldFrame);

        int roomNumber = RandomUtils.uniform(r, 0, 30);
        int hallwayNumber = RandomUtils.uniform(r, 0, 10);

        /*for(int i = 0; i < roomNumber; i += 1){
            Room room = new Room(r);
            room.drawFloor(floorWorldFrame);
            room.drawWall(wallWorldFrame);
        }*/

        for(int i = 0; i < hallwayNumber; i += 1){
            Hallway hallway = new Hallway(r);
            hallway.drawFloor(floorWorldFrame);
            hallway.drawWall(wallWorldFrame);
        }

        mergeFrame(finalWorldFrame, wallWorldFrame);
        mergeFrame(finalWorldFrame, floorWorldFrame);

        return finalWorldFrame;
    }

    public void initTETile(TETile[][] t){
        for(int x = 0; x < WIDTH; x += 1){
            for(int y = 0; y < HEIGHT; y += 1){
                t[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Create a random sized room on specific position.
     */
    public void makeRoom(TETile[][] floorWorld, TETile[][] wallWorld, Random r){
        int width = RandomUtils.uniform(r, 5, 10);
        int height = RandomUtils.uniform(r, 5, 10);
        int xPos = RandomUtils.uniform(r, 0, 80);
        int yPos = RandomUtils.uniform(r, 0, 30);
        if(xPos + width >= WIDTH || yPos + height >= HEIGHT){
            return;
        }
        for(int x = 0; x < width; x += 1){
            wallWorld[xPos + x][yPos] = Tileset.WALL;
            wallWorld[xPos + x][yPos + height - 1] = Tileset.WALL;
        }
        for(int y = 0; y < height; y += 1){
            wallWorld[xPos][yPos + y] = Tileset.WALL;
            wallWorld[xPos + width - 1][yPos + y] = Tileset.WALL;
        }

        // fill the inner part of the room
        for(int x = 1; x < width - 1; x += 1){
            for(int y = 1; y < height - 1; y += 1){
                floorWorld[xPos + x][yPos + y] = Tileset.FLOOR;
            }
        }
    }

    public void makeHallway(TETile[][] floorWorld, TETile[][] wallWorld, Random r){
        int orientation = RandomUtils.uniform(r, 0, 2);
        switch (orientation){
            // vertical
            case 0:
                int length = RandomUtils.uniform(r, 5, 30);
                int xPos = RandomUtils.uniform(r, 0, 77);
                int yPos = RandomUtils.uniform(r, 0, 30);
                if(yPos + length > HEIGHT){
                    return;
                }

                for(int i = 0; i < 3; i += 1){
                    wallWorld[xPos + i][yPos] = Tileset.WALL;
                    wallWorld[xPos + i][yPos + length - 1] = Tileset.WALL;
                }
                for(int i = 0; i < length; i += 1){
                    wallWorld[xPos][yPos + i] = Tileset.WALL;
                    wallWorld[xPos + 2][yPos + i] = Tileset.WALL;
                }

                for(int i = 1; i < length - 1; i += 1){
                    floorWorld[xPos + 1][yPos + i] = Tileset.FLOOR;
                }
                break;
            // horizontal
            case 1:
                length = RandomUtils.uniform(r, 5, 30);
                xPos = RandomUtils.uniform(r, 0, 80);
                yPos = RandomUtils.uniform(r, 0, 27);
                if(xPos + length > WIDTH){
                    return;
                }

                for(int i = 0; i < 3; i += 1){
                    wallWorld[xPos][yPos + i] = Tileset.WALL;
                    wallWorld[xPos + length - 1][yPos + i] = Tileset.WALL;
                }
                for(int i = 0; i < length; i += 1){
                    wallWorld[xPos + i][yPos] = Tileset.WALL;
                    wallWorld[xPos + i][yPos + 2] = Tileset.WALL;
                }

                for(int i = 1; i < length - 1; i += 1){
                    floorWorld[xPos + i][yPos + 1] = Tileset.FLOOR;
                }
                break;
            default:break;
        }
    }
    /**
     * Merge f2 to f1 which means f2 will overwrite f1 when comes to conflict.
     * @param f1 the frame on the bottom
     * @param f2 the frame above
     */
    public void mergeFrame(TETile[][] f1, TETile[][] f2){
        for(int x = 0; x < WIDTH; x += 1){
            for(int y = 0; y < HEIGHT; y += 1){
                if(f2[x][y] != Tileset.NOTHING){
                    f1[x][y] = f2[x][y];
                }
            }
        }
    }
}
