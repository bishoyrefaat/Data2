import java.util.*;
import java.lang.*;

public class part2 extends AbstractPart{
    private static int[][] maze;
    private static int N;

    //possible moves (0,-1)down,(0و 1)up,(1,0)right,(-1,0)left
    private int moves[] = {0,-1,0,1};

    static class cell {
        int x, y;
        // constructor
        public cell(int x, int y) {
            this.x = x;this.y = y; }
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("please enter N:");
        N = s.nextInt();
        maze = new int[N][N];
        System.out.println("please enter values for maze:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int in=s.nextInt();
                if(in==0||in==1) maze[i][j] = in;
                else{
                    --j;
                    System.out.println("Only input of 1 or 0 is allowed, please enter again");
                }

            }
        }
        boolean f = solve( );
        System.out.println("Solution:");
        if (f == false)
            System.out.println("no path found");
        else
            printPathRec(N-1,N-1);

    }

    /*
     * 1-we use bfs to solve the maze we visit the cells in all 4 directions
     * 2-visited cells are marked with -1*(i+1) to trace the route while printing
     * 3-go to the isSafe fn to check if they are available
     * 4-we then enqueue them , and repeat
     * 5-at the start of every loop check if we reached the end of the maze
     */
    private boolean solve() {
        if (!isSafe( N - 1, N - 1) || !isSafe( 0, 0))
            return false; // if end or start is blocked then no solution

        Queue<cell> queue = new LinkedList<>();
        queue.add(new cell(0, 0));

        while (!queue.isEmpty()) {
            cell c = queue.poll();
            if (c.x == N - 1 && c.y == N - 1) {return true;}

            for (int i = 0; i < 4; i++) {
                int mx=c.x+moves[i]; int my=c.y+moves[3-i];

                if (isSafe(mx,my) ) {
                    maze[my][mx]=-1*(i+1);

                    //leave trail to trace and print the steps later and mark visited
                    // if we move down we put value -1 , left put -2 ,up put -3 ,right put -4
                    queue.add(new cell(mx, my));
                }
            }
        }return false;
    }

    // this fn confirms is the cell the runner will move to is a clear cell
    private static boolean isSafe(int x, int y){return (x<N && x>=0 && y<N && y>=0&& maze[y][x]==0);}


    // an fn to print the steps taken to finish the maze
    private void printPathRec(int x , int y) {
        if(x==0&&y==0) {
            System.out.print("(" + x + "," + y + ")");
            return ;}
        int i = -1*(maze[y][x]+1);
        printPathRec(x-moves[i],y-moves[3-i]);
        System.out.print(",(" + x + "," + y + ")");
    }



}
/*test ex 4
0 1 1 0
0 0 1 0
0 0 0 0
0 1 1 0
test ex 6
 0 1 1 1 1 0
 0 1 0 0 0 0
 0 0 0 1 0 0
 0 0 0 1 0 0
 1 1 1 1 1 0
 1 0 1 0 0 0
 test ex 8
 0 1 1 1 0 0 0 0
 0 0 0 0 0 0 1 0
 0 0 0 1 1 1 1 0
 0 1 0 1 0 0 0 0
 1 1 1 1 0 0 0 1
 0 0 0 0 0 1 1 1
 0 0 1 1 1 1 0 0
 0 0 0 0 0 0 0 0
*/