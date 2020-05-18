import java.util.*;

public class part1 extends AbstractPart{

    private LinkedList<Integer> list[];
    private LinkedList<Integer> level[];
    private boolean[] vis;

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
        for (int i = 0; i < n+1; ++i){ list[i] = new LinkedList();level[i] = new LinkedList();}

        System.out.println("please enter edges in the form (u  v):");
        for(int i =0;i<e;i++)
        {
            u=s.nextInt();
            v=s.nextInt();
            list[v].add(u);
            list[u].add(v);
        }
        System.out.println("please enter starting vertex:");
        source=s.nextInt(); //source is starting vertex
        System.out.println("please enter value k :");
        k=s.nextInt();
        bfs(source,k);
        for(int i =k;i>0;i--) {
            System.out.println("level "+(k-i+1)+":\t");
            for(int j=0;j<level[i].size();j++)
            {
                System.out.print(" "+level[i].get(j));
            }
            System.out.println();
        }
        System.out.println("size = "+level[1].size());

    }

    void bfs(int source,int k)
    {
        Queue<Integer>  q1  =new LinkedList<>();
        Queue<Integer>  q2  =new LinkedList<>();
        q1.add(source);
        q2.add(k);
        vis[source]=true ;
        while(!q1.isEmpty())
        {
            int curfir= q1.poll(),cursec=q2.poll();
            Iterator<Integer> iter = list[curfir].iterator();
            while(iter.hasNext()) {
                int nxt=(int)iter.next();
                if(!vis[nxt]){
                level[cursec].add(nxt);
                vis[nxt]=true;
                if(cursec-1>0)
                    q1.add(nxt);
                    q2.add(cursec-1);
            }
            }
        }
    }

}
// test ex   9 10 1 2 2 3 1 7 2 4 4 7 7 8 3 4 7 6 5 6 9 7  4   2