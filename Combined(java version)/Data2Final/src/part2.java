import java.util.*;
import java.lang.*;

public class part2 extends AbstractPart{

    static int N;
    static class cell {
        int x, y;
        cell previous; //filled with previous step to trace back after reaching the end

        public cell(int x, int y, cell previous) {
            this.x = x;this.y = y;
            this.previous=previous; }
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("please enter N:");
        N = s.nextInt();
        int[][] maze = new int[N][N];
        System.out.println("please enter values for maze:");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                maze[i][j] = s.nextInt();
            }

        cell f = solve(maze, 0, 0);
        System.out.println("Solution:");
        if (f == null)
            System.out.println("no path found");
        else
            printPathRec(f);

    }

    /*
    1-we use bfs to solve the maze we examine the cells all 4 directions
    2-go to the isSafe fn to check if they are available (not outside the maze or a wall)
    3-we then enqueue them , and repeat
    4-at the start of every loop check if we reached the end of the maze
     */
    public static cell solve(int[][] maze, int x, int y) {
        if (!isSafe(maze, N - 1, N - 1) && !isSafe(maze, 0, 0))
            return null; //if end is blocked then no solution

        Queue<cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];// marks visited cells to avoid revisiting
        visited[0][0] = true;
        cell start = new cell(0, 0, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            cell c = queue.peek();
            if (c.x == N - 1 && c.y == N - 1) {return c;} //check if we have reached the end
            queue.remove();
            int moves[] = {-1, 0, 1, 0};
            //possible moves (0,-1)down,(0.1)up,(1,0)right,(-1,0)left
            for (int i = 0; i < 4; i++) {
                int mx=c.x+moves[i];
                int my=c.y+moves[3-i];

                if (isSafe(maze, mx, my) && !visited[mx][my]) {
                    visited[mx][my] = true;
                    cell n = new cell(mx, my , c);
                    queue.add(n);
                }
            }
        }return null;
    }

    // this fn confirms is the cell the runner will move to is inside the graph and not a wall
    public static boolean isSafe(int[][] maze, int x, int y) {
        return (x<N && x>=0 && y<N && y>=0&& maze[x][y]==0);
    }


    // an fn to print the steps taken to finish the maze
    public static void printPathRec(cell c) {
        if(c.previous==null)
            System.out.print("(" + c.x + "," + c.y + ")");
        else{
            printPathRec(c.previous);
            System.out.print(",(" + c.x + "," + c.y + ")");}
    }


}
//test ex  4 0 1 1 0  0 0 1 0  0 0 0 0  0 1 1 0
//test ex 6 0 1 1 1 1 0    0 1 0 0 0 0   0 0 0 1 0 0  0 0 0 1 0 0    1 1 1 1 1 0   1 0 1 0 0 0