import java.util.*;
import java.lang.*;

public class part2 extends AbstractPart{

    static int N;
    static class cell {
        int x, y;
        cell previous;

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

    public static cell solve(int[][] maze, int x, int y) {
        if (!isSafe(maze, N - 1, N - 1) && !isSafe(maze, 0, 0)) return null;
        Queue<cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        cell start = new cell(0, 0, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            cell pt = queue.peek();
            if (pt.x == N - 1 && pt.y == N - 1) {System.out.println("check");return pt;}
            queue.remove();
            int moves[] = {-1, 0, 1, 0};
            //possible moves (0,-1)down,(0.1)up,(1,0)right,(-1,0)left
            for (int i = 0; i < 4; i++) {
                int mx=pt.x+moves[i];
                int my=pt.y+moves[3-i];

                if (isSafe(maze, mx, my) && !visited[mx][my]) {
                    visited[mx][my] = true;
                    cell n = new cell(mx, my , pt);
                    queue.add(n);
                }
            }
        }return null;
    }

    public static boolean isSafe(int[][] maze, int x, int y) {
        // confirms is the cell the runner will move to is inside the graph and not a wall
        return (x<N && x>=0 && y<N && y>=0&& maze[x][y]==0);
    }

    public static void printPathRec(cell pt) {
        if(pt.previous==null)
            System.out.print("(" + pt.x + "," + pt.y + ")");
        else{
            printPathRec(pt.previous);
            System.out.print(",(" + pt.x + "," + pt.y + ")");}
    }


}
