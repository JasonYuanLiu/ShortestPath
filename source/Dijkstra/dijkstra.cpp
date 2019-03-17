#include <iostream>
#include <fstream>
#include <cstdio>
#include <queue>
#include <cstring>
using std::cout;
using std::ifstream;
using std::priority_queue;
using std::max;
struct QNode{
    int u;
    double dist;
    void init(int a,double b){
        u=a;dist=b;
    }
    bool operator < (const QNode& q)const{
        return dist>q.dist;
    }
};
struct Edge{
    int from,to,next;
    double len;
    void init(int a,int b,double c,int pos[],int cnt){
        from=a;to=b;
        len=c;
        next=pos[from];
        pos[from]=cnt;
    }
};
QNode make_QNode(int u,double dist){
    QNode a;
    a.init(u,dist);
    return a;
}
priority_queue<QNode> q;
int start_,end_;
int cnt,max_index;
int pos[1000000];
int pre[1000000];
double dist[1000000];
Edge edges[10000000];
void dij(int s,int t){
    while(!q.empty()) q.pop();
    for(int i=0;i<=max_index;i++)
        dist[i]=1e15;
    dist[s]=0;
    q.push(make_QNode(s,0));
    while(!q.empty()){
        QNode cur=q.top();
        q.pop();
        int u=cur.u;
        for(int i=pos[u];i!=0;i=edges[i].next)
            if(dist[edges[i].to]>dist[u]+edges[i].len){
                dist[edges[i].to]=dist[u]+edges[i].len;
                pre[edges[i].to]=u;
                q.push(make_QNode(edges[i].to,dist[edges[i].to]));
            }
    }
}
double vertices[1000000][4];
void init(){
    cnt=0;
    max_index=0;
    memset(pos,0,sizeof(pos));
    memset(edges,0,sizeof(edges));
    memset(pre,0,sizeof(pre));
    ifstream fin;
    fin.open("edges.csv");
    freopen("path.csv","w",stdout);
    int cnt=0;
    int x,y;
    double len;
    char c;
    fin>>start_>>c>>end_;
    start_++;
    end_++;
    while(!fin.eof()){
        fin>>x>>c>>y>>c>>len;
        x++;y++;
        max_index=max(max_index,x);
        max_index=max(max_index,y);
        cnt++;
        edges[cnt].init(x,y,len,pos,cnt);
        cnt++;
        edges[cnt].init(y,x,len,pos,cnt);
    }
    fin.close();
    ifstream fin_;
    fin_.open("vertices.csv");
    double x1,y1,z1;
    memset(vertices,0,sizeof(vertices));
    for(int i=1;i<=max_index;i++){
        fin_>>x1>>c>>y1>>c>>z1;
        vertices[i][0]=x1;
        vertices[i][1]=y1;
        vertices[i][2]=z1;
    }
    fin_.close();
}
int main(){
    init();
    dij(start_,end_);
    //printf("total entries is %d\n",cnt);
    int cur=end_;
    printf("X,Y,Z\n");
    while(cur!=0){
        cout<<vertices[cur][0]<<','<<vertices[cur][1]<<','<<vertices[cur][2]<<'\n';
        cur=pre[cur];
    }
    //printf("minimum distance to vertex 5050 is %lf",dist[5050]);
    return 0;
}
