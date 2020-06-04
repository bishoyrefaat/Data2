import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class part3 extends AbstractPart{

    static class Route {
        int point, weight,time ;
        public Route(int point, int weight,int time) {
            this.point = point;
            this.weight = weight;
            this.time = time;
        }
    }

    class RouteComparator implements Comparator<Route>{
        public int compare(Route s1, Route s2) {
            if (s1.weight<s2.weight)return 1;
            else if (s1.weight>s2.weight)return -1;
            return 0;
        }
    }

    private int M;
    private ArrayList<ArrayList<Route>> graph;
    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("please enter amount M:");
        M = s.nextInt();

        // number of cities, number of routes
        int numCities,numRoutes;
        System.out.println("please enter number of cities: ");
        numCities=s.nextInt();

        System.out.println("please enter number of routes: ");
        numRoutes=s.nextInt();

        System.out.println("please enter source , destination time and cost for each route: ");
        graph=new ArrayList<ArrayList<Route>> (numRoutes+1);
        for (int i = 0; i < numCities+1; i++) {
            graph.add(new ArrayList<Route>(numCities+1));
        }

        int src , dest ,time,cost,total;
        for (int i = 0; i < numRoutes; i++){
            src=s.nextInt();
            dest=s.nextInt();
            time=s.nextInt();
            cost=s.nextInt();

            //get total cost by adding the hour cost (with the 1 hour stay included)
            total=M*(1+time)+cost;
            graph.get(src).add(new Route(dest,total,time));
        }
        System.out.println("please enter source city:");
        int start=s.nextInt();
        System.out.println("please enter destination city:");
        int end=s.nextInt();

        dijkstra(start,end,numCities,numRoutes);

    }

    private void dijkstra(int start,int end,int numCities,int numRoutes) {
        //array to contain the least cost for each point relative to the start
        int d[] = new int[numCities+1];

        //array to record the time for the shortest path for each point relative to the start
        int t[]= new int[numCities+1];

        int shortest[]= new int[numCities+1];
        /* this will contain the previous point for each shortest path.
         * for example in the case provided with the assignment  shortest[4]=3 , and shortest[3]=1
         * this is used to trace the path taken
         */

        //initial values for all points
        for (int i = 0; i < numCities+1; i++) {
            d[i] = -1;
        }

        //start is intialized with 0 distance and time
        d[start]=0;

        PriorityQueue<Route> q = new PriorityQueue<Route>(numRoutes+1,new RouteComparator());
        q.add(new Route(start,0,0));
        while(!q.isEmpty()&&end!=start){
            int cur=q.poll().point;

            //iterate from each point to its available routes
            Iterator<Route> iter = graph.get(cur).iterator();
            while(iter.hasNext()) {
                Route n=iter.next();
                int nxt=n.point,weight=n.weight,time=n.time;

                //check if a route through the current point creates a cheaper path, and update d[]
                if(d[nxt]>d[cur]+weight||d[nxt]==-1){
                    d[nxt]=d[cur]+weight;

                    //added 1 to time to account for the 1 hour wait in each city
                    t[nxt]=time+t[cur]+1;

                    //enqueue the point after the update
                    q.add(new Route(nxt,d[nxt],t[nxt]));
                    shortest[nxt]=cur;
                }
            }
        }

        if(d[end]!=-1) {
            System.out.print("The route with minimum cost is ");
            printPathRec(shortest, end, start);
            System.out.println();
            System.out.print("Total time " + Math.max(0,(t[end]-1))+ " hours \nTotal cost = " + Math.max(0, (d[end] - 1 * M)) + "$");
            // minus 1*M because no stoping for 1 hour in last point
        }
        else
            System.out.println("no path available");
    }


    private void printPathRec(int [] shortest,int b,int a) {
        if(b==a) {
            System.out.print(b);
        }
        else {
            printPathRec(shortest, shortest[b], a);
            System.out.print(" "+b);
        }
    }
}
//test ex   100  4  5    1 2 1 250   1 3 1 300   1 4 2 700   2 4 1 300   3 4 1 200   1  4
//test ex   30   10  3  1 10 3 10000   1 2 2 40   2 10 3 50   1    10