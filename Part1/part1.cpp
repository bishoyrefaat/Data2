#include<bits/stdc++.h>
using namespace  std;
vector< vector<int> > v(1000),level(1000);
bool vis[1000];
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
   	 cin>>n>>e;
   	// v.resize(n,vector<int>(0));
 	 //level.resize(e,vector<int>(0));
 	 memset(vis,false,sizeof(vis));
   	 for(int i =0;i<e;i++)
   	 {
   	 	cin>>u>>to;
	    v[u].push_back(to);
		v[to].push_back(u);
   	 }
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
   	 cout<<"size = "<<level[k].size()<<endl;
     return 0;


}
