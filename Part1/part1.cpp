#include<bits/stdc++.h>
using namespace  std;
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
int main()
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
     return 0;


}
