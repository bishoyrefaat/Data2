import java.util.*;

public class part3 extends AbstractPart{

    static class Route {
         int point, weight;
        public Route(int point, int weight) {
            this.point = point;
            this.weight = weight;
        }}

    class RouteComparator implements Comparator<Route>{
        public int compare(Route s1, Route s2) {
            if (s1.weight < s2.weight) return 1;
            else if (s1.weight > s2.weight) return -1;
            return 0;
        }
    }

    int M;
    ArrayList<ArrayList<Route>> graph;
    int[][] g;
    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("please enter amount M:");
        M = s.nextInt();
        int nc,nr;// number of cities, number of routes
        System.out.println("please enter number of cities: ");
        nc=s.nextInt();
        System.out.println("please enter number of routes: ");
        nr=s.nextInt();
        System.out.println("please enter source , destination time and cost for each route: ");
        graph=new ArrayList<ArrayList<Route>> (nr+1);
        g=new int[nc+1][nc+1];
        for (int i = 0; i < nc+1; i++){graph.add(new ArrayList<Route>(nc+1));}
        int src , dest ,time,cst,total;
        for (int i = 0; i < nr; i++){
            src=s.nextInt();dest=s.nextInt();time=s.nextInt();cst=s.nextInt();
            total=M*(1+time)+cst; //get total cost by adding the hour cost (with the 1 hour stay included)
            graph.get(src).add(new Route(dest,total));
            g[src][dest]=time;
        }
        System.out.println("please enter source city:");
        int start=s.nextInt();
        System.out.println("please enter destination city:");
        int end=s.nextInt();

        dijkstra(start,end,nc,nr);

    }

    private void dijkstra(int start,int end,int nc,int nr) {
            int d[] = new int[nc+1];// this array is to contain the shortest distance for each point from to the start
            int shortest[]= new int[nc+1];
            /* this will contain the previous point for each shortest path.
            for example in the case provided with the assigment  d[4]=3 , and d[3]=1
            this is used to trace the path taken  */
            for (int i = 0; i < nc+1; i++) {d[i] = -1; }//initial values for all points
            d[start]=0;//start is intialized with 0
            PriorityQueue<Route> q = new PriorityQueue<Route>(nr+1,new RouteComparator());
            q.add(new Route(start,0));
            while(!q.isEmpty()&&end!=start){
                int cur=q.poll().point;
                Iterator<Route> iter = graph.get(cur).iterator(); //iterate from each point to its available routes
                while(iter.hasNext()){
                    Route n=iter.next();
                    int nxt=n.point;
                    int weight=n.weight;
                    //check if a route through the current point creates a shorter path, and update d[]
                    if(d[nxt]>d[cur]+weight||d[nxt]==-1){
                        d[nxt]=d[cur]+weight;
                        q.add(new Route(nxt,d[nxt]));//enqueue the point after the update
                        shortest[nxt]=cur;
                    }
                }
            }
            if(d[end]!=-1) {

                System.out.print("The route with minimum cost is ");
                int hours = printPathRec(shortest, end, start);
                System.out.println();
                System.out.print("Total time " + (hours - 1) + " hours \nTotal cost = " + Math.max(0, (d[end] - 1 * M)) + "$");
                // minus 1*M because no stoping for 1 hour in last point
            }else
                System.out.println("no path available");
    }


    public int printPathRec(int [] d,int end,int start) {
        if(end==start){ System.out.print(start);return 0;}
        else {
             int i = printPathRec(d, d[end], start);
            System.out.print(" "+end);
        return i+g[d[end]][(end)]+1;}
    }

    }
//test ex   100  4  5    1 2 1 250   1 3 1 300   1 4 2 700   2 4 1 300   3 4 1 200   1  4
//test ex   30   10  3 1 10 3 10000   1 2 2 40   2 10 3 50   1    10