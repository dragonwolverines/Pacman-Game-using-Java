import java.io.*; 
import java.net.*; 
public class PacmanGame {
public static void main(String[] args) {
new PacmanGame().run();
    }
    int[][] graph = {
            //    0  1  2  3  4   5  6  7  8  9  10 11 12 13 14  15 16 17 18 19  20 21 22 23 24 
            { 0, 4, 0, 0, 0, -4, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  0 */
            { 4, 0, 4, 0, 0,  0,-4, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  1 */
            { 0, 4, 0, 4, 0,  0, 0,-4, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  2 */
            { 0, 0, 4, 0, 4,  0, 0, 0,-4, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  3 */
            { 0, 0, 0, 4, 0,  0, 0, 0, 0,-4,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  4 */

            {-4, 0, 0, 0, 0,  0, 4, 0, 0, 0, -4, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  5 */
            { 0,-4, 0, 0, 0,  4, 0, 4, 0, 0,  0,-4, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  6 */
            { 0, 0,-4, 0, 0,  0, 4, 0, 4, 0,  0, 0,-4, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  7 */
            { 0, 0, 0,-4, 0,  0, 0, 4, 0, 4,  0, 0, 0,-4, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  8 */
            { 0, 0, 0, 0,-4,  0, 0, 0, 4, 0,  0, 0, 0, 0,-4,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /*  9 */

            { 0, 0, 0, 0, 0, -4, 0, 0, 0, 0,  0, 4, 0, 0, 0, -4, 0, 0, 0, 0,  0, 0, 0, 0, 0 }, /* 10 */
            { 0, 0, 0, 0, 0,  0,-4, 0, 0, 0,  4, 0, 4, 0, 0,  0,-4, 0, 0, 0,  0, 0, 0, 0, 0 }, /* 11 */
            { 0, 0, 0, 0, 0,  0, 0,-4, 0, 0,  0, 4, 0, 4, 0,  0, 0,-4, 0, 0,  0, 0, 0, 0, 0 }, /* 12 */
            { 0, 0, 0, 0, 0,  0, 0, 0,-4, 0,  0, 0, 4, 0, 4,  0, 0, 0,-4, 0,  0, 0, 0, 0, 0 }, /* 13 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0,-4,  0, 0, 0, 4, 0,  0, 0, 0, 0,-4,  0, 0, 0, 0, 0 }, /* 14 */

            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0, -4, 0, 0, 0, 0,  0, 4, 0, 0, 0, -4, 0, 0, 0, 0 }, /* 15 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0,-4, 0, 0, 0,  4, 0, 4, 0, 0,  0,-4, 0, 0, 0 }, /* 16 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0,-4, 0, 0,  0, 4, 0, 4, 0,  0, 0,-4, 0, 0 }, /* 17 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0,-4, 0,  0, 0, 4, 0, 4,  0, 0, 0,-4, 0 }, /* 18 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0,-4,  0, 0, 0, 4, 0,  0, 0, 0, 0,-4 }, /* 19 */

            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0, -4, 0, 0, 0, 0,  0, 4, 0, 0, 0 }, /* 20 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0,-4, 0, 0, 0,  4, 0, 4, 0, 0 }, /* 21 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0,-4, 0, 0,  0, 4, 0, 4, 0 }, /* 22 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0,-4, 0,  0, 0, 4, 0, 4 }, /* 23 */
            { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  0, 0, 0, 0,-4,  0, 0, 0, 4, 0 }  /* 24 */
            //    0  1  2  3  4   5  6  7  8  9  10 11 12 13 14  15 16 17 18 19  20 21 22 23 24 
    };

    int[] PacmanStatus = new int[3];   // [0] = from
    // [1] = to
    // [2] = steps
    private int FROM;
private int TO;
private int STEPS;

private static final int INITIAL_FROM = 12;
private static final int INITIAL_TO = 13;
private static final int INITIAL_STEPS = 0;

public PacmanGame()
{
FROM = INITIAL_FROM;
TO = INITIAL_TO;
STEPS = INITIAL_STEPS;
}

    public void displayGraph()
{ 
    for (int i = 0; i < graph.length; ++i)
{
        for (int j = 0; j < graph[i].length; ++j)
{
            if (graph[i][j] > 0)
{
              System.out.print("+");
            }
            else if (graph[i][j] == 0)
{
              System.out.print(" ");
            }

            System.out.print(graph[i][j]);

            if (j < graph[i].length - 1)
{
              System.out.print(" ");
            }
        }
        System.out.println();
    }
}

    public void renderGraph() {
        char[][] renderPlane = new char[17][33]; //17 tall by 33 wide

        // clear image
        for (int i=0; i<17; ++i) {
            for (int j=0; j<33; ++j) {
                renderPlane[i][j] = ' ';
            }
        }

        // draw graph 
        //   - this should be a separate utility method
        int[] rows = new int[]{  0,  4,  8, 12, 16 };
        int[] cols = new int[]{  0,  8, 16, 24, 32 };

        for (int i : rows) {
            for (int j=0; j<33; ++j) renderPlane[i][j] = '-';
        }
        /*
       0       8       16      24      32
     0 ---------------------------------



     4 ---------------------------------



     8 ---------------------------------



    12 ---------------------------------



    16 ---------------------------------
         */

        for (int j : cols) {
            for (int i=0; i<17; ++i) renderPlane[i][j] = '|';
        } 
        /*
       0       8       16      24      32
     0 |-------|-------|-------|-------| 
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
     4 |-------|-------|-------|-------|
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
     8 |-------|-------|-------|-------|
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
    12 |-------|-------|-------|-------|
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
    16 |-------|-------|-------|-------|
         */

        for (int i : rows) {
            for (int j : cols) renderPlane[i][j] = '+';
        }
        /*
       0       8       16      24      32
     0 +-------+-------+-------+-------+ 
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
     4 +-------+-------+-------+-------+
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
     8 +-------+-------+-------+-------+
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
    12 +-------+-------+-------+-------+
       |       |       |       |       |
       |       |       |       |       |
       |       |       |       |       |
    16 +-------+-------+-------+-------+
         */

        // draw PacMan (and ghosts, and energy blobs)
        int from  = PacmanStatus[FROM]; //12
        int to    = PacmanStatus[TO];   //13
        int steps = PacmanStatus[STEPS];  //0

        // translate from abstract graph position to physical render plane position
        /* 
          0       1       2       3       4
          0       8       16      24      32
      0 0 0-------1-------2-------3-------4 
          |       |       |       |       |
          |       |       |       |       |
          |       |       |       |       |
      1 4 5-------6-------7-------8-------9
          |       |       |       |       |
          |       |       |       |       |
          |       |       |       |       |
      2 8 10------11------12------13------14
          |       |       |       |       |
          |       |       |       |       |
          |       |       |       |       |
     3 12 15------16------17------18------19
          |       |       |       |       |
          |       |       |       |       |
          |       |       |       |       |
     4 16 20------21------22------23------24
         */
        //changes the vertice's number to a position which can be recognized by the renderGraph() method. 

        int fromRow = (from / 5);  //= 2
        int fromCol = (from % 5); // = 2
        int toRow   = (  to / 5); // = 2
        int toCol   = (  to % 5); // = 3

        /* Example: 
             from(12)-->to(13),
             fromRow = 2, fromCol = 2
             toRow   = 2, toCol   = 3

             from(16)-->to(11),
             fromRow = 3, fromCol = 1
             toRow   = 2, toCol   = 1
         */

        if (fromRow == toRow) {
            if (fromCol < toCol) {            // move right
                renderPlane[4*fromRow][8*fromCol + steps] = 'Q';
            }
            else if (fromCol > toCol) {    // move left
                renderPlane[4*fromRow][8*fromCol - steps] = 'Q';
            }
            else {                                            // no move
                renderPlane[4*fromRow][8*fromCol] = 'Q';
            }
        }
        else if (fromCol == toCol) {
            if (fromRow < toRow) {            // move down
                renderPlane[4*fromRow + steps][8*fromCol] = 'Q';
            }
            else if (fromRow > toRow) {    // move up
                renderPlane[4*fromRow - steps][8*fromCol] = 'Q';
            }
            else {                                            // no move
            }
        }
        else {
            System.out.println( "Illegal move." );
            return;
        }

        // paint image on screen (this is really image.repaint() )
        for (int i=0; i<17; ++i) {
            for (int j=0; j<33; ++j) {
                System.out.print( renderPlane[i][j] );
            }
            System.out.println();
        }
        System.out.println();

    }

    public void run() {
        int n = 0;
        while (n++ < 1000) {

            int from    = PacmanStatus[FROM];
            int to      = PacmanStatus[TO];
            int steps   = PacmanStatus[STEPS];


            System.out.println("(from,to,steps) = ("+from+","+to+","+steps+")");
            System.out.println();



            renderGraph();



            try { Thread.sleep(100); }
            catch (InterruptedException e) { }

            // update PacMan state with random motion
            int edgeLen = Math.abs(graph[from][to]); //gets you the length of the edge between the two vertices of where Pacman is and wants to go. 

            if (steps < edgeLen) {
                ++PacmanStatus[STEPS];    //if Pacman hasn't gotten to the other side, his steps go up by one. 
            }

            else if (steps == edgeLen) {
                int randomizer = 0;
                for (int j=0; j<25; j=j+1) {
                    if (!(0 == graph[to][j]))  {
                        randomizer=randomizer+1;  
                    }
                }

                int choice = (int)(randomizer * Math.random()); //sets any vertex in the same row or column to the from vertex. 

                for (int j=0; j<25; j=j+1) {   
                    if (!(0 == graph[to][j])) {   
                        if (0 == (choice)) {
                            from  = to;
                            to    = j;
                            steps = 0;
                        }
                        choice=choice-1;
                    }
                }

                PacmanStatus[FROM]  = from;
                PacmanStatus[TO]    = to;
                PacmanStatus[STEPS] = steps;
            }
        }  
    }
}
