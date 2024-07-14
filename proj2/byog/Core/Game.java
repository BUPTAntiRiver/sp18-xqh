package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;


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
        StdDraw.enableDoubleBuffering();
        drawMenu();
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

        int graphNumber = RandomUtils.uniform(r, 15, 30);
        int roomNumber = RandomUtils.uniform(r, 8, graphNumber);
        int hallwayNumber = graphNumber - roomNumber;
        int currentX = 0;
        int currentY = 0;

        Graph startGraph = new Room(r);
        currentY = startGraph.getY();
        currentX = startGraph.getX();

        while(roomNumber > 0 || hallwayNumber > 0){
            int s = RandomUtils.uniform(r, 0, 2);
            if(s == 0 && roomNumber > 0){
                Room temp = new Room(r, startGraph);
                temp.drawWall(wallWorldFrame);
                temp.drawFloor(floorWorldFrame);
                roomNumber -= 1;
                startGraph = temp;
            }
            if(s == 1 && hallwayNumber > 0){
                Hallway temp = new Hallway(r, startGraph);
                temp.drawWall(wallWorldFrame);
                temp.drawFloor(floorWorldFrame);
                hallwayNumber -= 1;
                startGraph = temp;
            }
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

    public static void drawMenu(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.8, "CS61B GAME");
        StdDraw.text(0.5, 0.55, "New Game [N]");
        StdDraw.text(0.5, 0.5, "Load Game [L]");
        StdDraw.text(0.5, 0.45, "Quit Game [Q]");
        StdDraw.show();
        StdDraw.pause(100);
    }
    public static void render(TETile[][] world){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
    }
}
