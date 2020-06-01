#include <bits/stdc++.h>
using namespace std;
class solution1
{
private:
	vector< vector<int> > v,level;
vector<bool> vis;
void bfs(int source,int k)
 { 
   queue< pair< int,int > > q;
   q.push({ source,k });
   vis[source]=true ;
   while(!q.empty())
   {  pair< int,int > curr = q.front();
   	q.pop();
   	vector<int>::iterator ptr;
   	int node = curr.first; 
   	for(ptr=v[node].begin();ptr!=v[node].end();++ptr)
   	{  
   		if(!vis[*ptr]){
   	 	level[curr.second].push_back(*ptr);
   			vis[*ptr]=true;
   		if(curr.second-1)
   			q.push({*ptr, curr.second-1 });
   		}
   	}
   }


}
public :
    solution1()
    {
    	int u ,to ;
   	 int n,e,k,source;
   	 cout<<"please enter number of vertices:"<<endl;
   	 cin>>n;
   	 cout<<"please enter number of edges:"<<endl;
   	 cin>>e;
     cout<<"please enter edges in the form (u  v):"<<endl;
   	 v.reserve(n+1);
   	 vis.reserve(n+1);
   	 level.reserve(e+1);
   	 for(int i =0;i<e;i++)
   	 {
   	 	cin>>u>>to;
	    v[u].push_back(to);
		v[to].push_back(u);
   	 }
   	 cout<<"please enter starting vertex:"<<endl;
   	 cin>>source>>k;	
   	 bfs(source,k);
   	 for(int i =k;i>0;i--)
   	 {
   	 	cout<<"level "<<k-i+1<<":\t";
   	 	for(int j=0;j<level[i].size();j++)
   	 	{
   	 		cout<<" "<<level[i][j];
   	 	}
   	 	cout<<endl;
   	 }
   	 cout<<"size = "<<level[1].size()<<endl;
    }
};
class solution2
{
  private : 
  vector< vector<int> > v;
 int n ;
int dx[4]={0,-1,0,1};
int dy[4]={1,0,-1,0};
int isaval(int x){return x==0;}
void printroute(int x,int y)
{
       if(x==1&&y==1)
       {
       	cout<<"(0,0)";
       	return ;
       }
       int i = -1*(v[x][y]+1);
       printroute(x-dx[i],y-dy[i]);
       cout<<"("<<x-1<<","<<y-1<<")";
}

void bfs()
{ 
  if(v[1][1]==1)
	return ;
   queue <pair<int,int> >q;
	q.push({1,1});
   while(!q.empty()&&v[n][n]==0)
   {
   	pair<int,int> curr =q.front() ;
   	q.pop();
   	for(int i =0;i<4;i++)
   	{
   		if(isaval(v[ curr.first +dx[i] ][ curr.second+ dy[i] ]))
   		{ 
   		 v[ curr.first + dx[i] ][curr.second + dy[i] ]=-1*(i+1);
           q.push({curr.first+dx[i],curr.second+dy[i]});
   		}
   	}

   }
}
public  :
    solution2()
    {
      cin>>n;
 v.resize(n+2,vector<int>(n+2,0));
  for(int i=0;i<=n+1;i++)
  {
  	v[i][0]=1;
  	v[i][n+1]=1;
  	v[0][i]=1;
  	v[n+1][i]=1;
  }
  for(int i =1;i<=n;i++)
  	for(int j=1;j<=n;j++)
  	   cin>>v[i][j];
   bfs();
 	cout<<endl;
 	if(v[n][n]==0)
 		cout<<"No path found ";
else printroute(n,n);
    }

};
int main()
{
  solution1 s1;
  solution2 s2;
  return 0 ;
}
// s1 9 10 1 2 2 3 1 7 2 4 4 7 7 8 3 4 7 6 5 6 9 7 4 2
// s2 4 0 1 1 0 0 0 1 0 0 0 0 0 0 1 1 0 