package byog.Core;

import byog.TileEngine.TETile;

public interface Graph {
    void drawWall(TETile[][] wallWorld);
    void drawFloor(TETile[][] floorWorld);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    int[] getCenter();
}
