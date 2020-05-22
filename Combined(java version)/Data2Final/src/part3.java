import java.util.*;

public class part3 extends AbstractPart{

    public class Route {
         int destination, weight;
         Route prev;
        public Route(int destination, int weight ,Route prev) {
            this.destination = destination;
            this.weight = weight;
            this.prev=prev;
        }}

    class RouteComparator implements Comparator<Route>{
        public int compare(Route s1, Route s2) {
            if (s1.weight < s2.weight)
                return 1;
            else if (s1.weight > s2.weight)
                return -1;
            return 0;
        }
    }


    int M;
    ArrayList<ArrayList<Route>> graph;
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
        for (int i = 0; i < nc+2; i++){graph.add(new ArrayList<Route>());}

        int src , dest ,time,cst,total;
        for (int i = 0; i < nr; i++){
            src=s.nextInt();dest=s.nextInt();time=s.nextInt();cst=s.nextInt();
            total=M*(1+time)+cst;
            graph.get(src).add(new Route(dest,total,null));
        }
        System.out.println("please enter source city:");
        int start=s.nextInt();
        System.out.println("please enter destination city:");
        int end=s.nextInt();

        dijkstra(start,end,nc,nr);

    }

    private void dijkstra(int start,int end,int nc,int nr) {
            int d[] = new int[nc+1];
            int shortest[]= new int[nc+1];
            int k=0;
            Route f=null;
            for (int i = 0; i < nc+1; i++) {d[i] = Integer.MAX_VALUE; }
            d[start]=0;
            PriorityQueue<Route> q = new PriorityQueue<Route>(nr+1,new RouteComparator());
            q.add(new Route(start,0,null));
            while(!q.isEmpty()){
                int u=q.poll().destination;
                Iterator<Route> iter = graph.get(u).iterator();
                while(iter.hasNext()){
                    Route n=iter.next();
                    f=n;
                    int city=n.destination;
                    int weight=n.weight;
                    if(d[city]>d[u]+weight){
                        d[city]=d[u]+weight;
                        q.add(new Route(city,d[city],n));
                        shortest[city]=u;
                    }
                }
            }

        System.out.print("The route with minimum cost is ");
        int no=printPathRec(shortest,end,start);
        System.out.println();
        System.out.print("Total time "+no+" hours \nTotal cost = "+(d[end]-1*M)+"$");
        // minus 1*M because no stoping for 1 hour in last point

    }


    public int printPathRec(int [] arr,int end,int start) {
        if(end==start) {System.out.print(start);return 1;
        } else {
            int i = printPathRec(arr, arr[end], start);
            System.out.print(" "+end);
            return i+1;
        }

    }

    }
//test ex   100  4  5    1 2 1 250   1 3 1 300   1 4 2 700   2 4 1 300   3 4 1 200   1  4