import java.util.*;

public class part1 extends AbstractPart{

    private LinkedList<Integer> list[];
    private LinkedList<Integer> level[];
    private boolean[] vis;

    static class pair {
        int x, level;
        public pair(int x, int level) {
            this.x = x;this.level= level; }
    }


    public void start() {
        Scanner s = new Scanner(System.in);
        int u,v,n,e,k,source;
        System.out.println("please enter number of vertices:");
        n=s.nextInt();
        System.out.println("please enter number of edges:");
        e=s.nextInt();

        vis = new boolean[n+1]; //array to record visited points
        list=new LinkedList[n+1];
        level= new LinkedList[n+1];
        for (int i = 0; i < n+1; ++i){ list[i] = new LinkedList();}

        System.out.println("please enter edges in the form (u  v):");
        for(int i =0;i<e;i++)
        {
            u=s.nextInt();
            v=s.nextInt();

            //undirected graph so we add both ways
            list[v].add(u);
            list[u].add(v);
        }
        System.out.println("please enter starting vertex:");
        source=s.nextInt(); //source is starting vertex
        System.out.println("please enter value k :");
        k=s.nextInt();
        for (int i = 0; i < k+1; ++i)level[i] = new LinkedList();

        bfs(source,k);

        //printing answer
        /*for(int i=k-1;i>=0;i--) { //print every level until the required one
            System.out.println("level "+(k-i)+":\t");
            for(int j=0;j<level[i].size();j++)
            {
                System.out.print(" "+level[i].get(j));
            }
            System.out.println();
        }*/
        System.out.println("There are "+level[0].size()+" people with "+k+" connections away starting from "+source);//note: the list is filled in reverse order level[0] is the kth level
    }

    private void bfs(int source,int k)
    {
        Queue<pair>  q1  =new LinkedList<>();
        q1.add(new pair(source,k-1));
        vis[source]=true ;
        while(!q1.isEmpty())
        {
            int current= q1.peek().x,curLevel=q1.peek().level;q1.remove();
            Iterator<Integer> iter = list[current].iterator();
            while(iter.hasNext()) {
                int nxt=(int)iter.next();
                if(!vis[nxt]){
                    level[curLevel].add(nxt);
                    vis[nxt]=true;
                    if(curLevel-1>=0)
                        q1.add(new pair(nxt,curLevel-1));
                }
            }
        }
    }

}
// test ex   9 10   1 2   2 3   1 7   2 4   4 7   7 8   3 4   7 6   5 6   9 7     4   2
// test ex  10 10    1 2    2 3    2 4    4 7    7 5    5 6    4 8    8 9    9 10    3 8     3   3