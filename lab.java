import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
public class lift {
	static HashMap<Integer, Integer>[] hm;
	static int[] path;
	static int len =0;
	static boolean[] visited ;
	static int ans = 500000;
	static HashMap<Integer, Integer> waiting = new HashMap();
	static LinkedList<Integer> curr = new LinkedList();
	static LinkedList<Integer>[] ll;
	static int time=0;
	public static int w_bfs(int n){
		int current = 1;
		while(true){
			for(int i=0;i<ll[current].size();i++){
				if (waiting.containsKey(ll[current].get(i))){
					int ctime = waiting.get(ll[current].get(i));
					int dtime = time;
					int mini = hm[current].get(ll[current].get(i));
					if (mini<10000){
						if (dtime%(2*mini) ==0){
							dtime = dtime + mini;
						}
						else {
							dtime = dtime + mini + (2*mini) - (dtime%(2*mini)) ;}
						}
					else{
						mini = mini-10000;
						if (dtime-mini>=0){
							if ((dtime-mini)%(2*mini)==0){
								dtime = dtime + mini;
							}
							else{
								dtime = dtime + mini + (2*mini)- ((dtime-mini)%(2*mini)); 
							}
						}
						else{
							dtime = dtime + mini + (mini - dtime);
						}
					}
					if (dtime < ctime){
						waiting.put(ll[current].get(i), dtime);
					}
				}
				else{
					int dtime = time;
					int mini = hm[current].get(ll[current].get(i));
					if (mini<10000){
						if (dtime%(2*mini) ==0){
							dtime = dtime + mini;
						}
						else {
							dtime = dtime + mini + (2*mini) - (dtime%(2*mini)) ;}
						}
					else{
						mini = mini-10000;
						if (dtime-mini>=0){
							if ((dtime-mini)%(2*mini)==0){
								dtime = dtime + mini;
							}
							else{
								dtime = dtime + mini + (2*mini)- ((dtime-mini)%(2*mini)); 
							}
						}
						else{
							dtime = dtime + mini + (mini - dtime);
						}
					}
					waiting.put(ll[current].get(i), dtime);	
				}
			}	
			Iterator it = waiting.entrySet().iterator();
			int min = 100000;
			int curn=0;
			while(it.hasNext()){
				Map.Entry pair = (Map.Entry)it.next();
		        int point =(int) pair.getValue();	
		        if (point < min){
		        	min = point;
		        	curn = (int) pair.getKey();
		        } 
			}
			current = curn;
			time = waiting.get(curn);
			waiting.remove(curn);
			if (curn == n){
				return time;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		String[] cases = rd.readLine().split(" ");
		int n = Integer.parseInt(cases[0]);
		int e = Integer.parseInt(cases[1]);
		hm = new HashMap[n+1];
		ll = new LinkedList[n+1];
		path = new int[n+1];
		visited = new boolean[n+1];
		for(int i=0;i<=n;i++){
			hm[i]= new HashMap<Integer, Integer>();
			ll[i]= new LinkedList<Integer>();
		}
		for(int i=0;i<e;i++){
			cases = rd.readLine().split(" ");
			int v1 = Integer.parseInt(cases[0]);
			int v2 = Integer.parseInt(cases[1]);
			ll[v1].add(v2);
			ll[v2].add(v1);
			hm[v1].put(v2,(int )Math.abs(v2-v1));
			hm[v2].put(v1,(int )Math.abs(v2-v1)+10000);
		}	
		int ans = w_bfs(n );
		System.out.println(ans*5);
	}
}
