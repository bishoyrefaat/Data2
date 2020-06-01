#include <bits/stdc++.h>
using namespace std;
vector< vector<int> > v;
int n ;
int dx[]={0,-1,0,1};
int dy[]={1,0,-1,0};
int isaval(int x){return x==0;}
void printroute(int x=n,int y=n)
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

int main()
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
else printroute();	
}
