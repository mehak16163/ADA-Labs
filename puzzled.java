import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class puzzled {
	static int currx=0;
	static int curry=0;
	static int min=0 ;
	static int sum=0;
	static LinkedList<Node> graph ;
	 static void find(Node[][] c , int row ,int col){
		 c[0][0].rweight = c[0][0].weight;
		 sum = sum + c[0][0].weight;
		 if (c[0][0].down!=null){
			 c[0][0].down.rweight = c[0][0].weight+ c[0][0].down.weight;
			 graph.add(c[0][0].down);}
		 if(c[0][0].right!=null){
			 c[0][0].right.rweight = c[0][0].weight +c[0][0].right.weight;
		 graph.add(c[0][0].right);}
		 c[0][0].visited=true;
		while(!(currx==row-1 && curry==col-1)){
			int index = 0;
			Node mini = graph.get(0);
			int minw = graph.get(0).rweight;
			for(int i=1;i<graph.size();i++){
				Node n = graph.get(i);
				if (n.rweight < minw){
					minw = n.rweight;
					mini = n;
					index = i;
				}
			}
			
			graph.remove(index);
			sum = sum+minw;
			currx = mini.x;
			curry = mini.y;
			mini.visited=true;
			if(mini.up!=null && mini.up.visited==false ){
				if (mini.up.rweight==-1){
					mini.up.rweight=mini.rweight + mini.up.weight; 
					graph.add(mini.up);}
				else if (mini.up.rweight > mini.rweight+ mini.up.weight){
					graph.remove(mini.up);
					mini.up.rweight = mini.up.weight + mini.rweight;
					graph.add(mini.up);
					
				}
			}
			if(mini.down!=null && mini.down.visited==false){
				if (mini.down.rweight==-1){
					mini.down.rweight=mini.rweight + mini.down.weight; 
					graph.add(mini.down);}
				else if (mini.down.rweight > mini.rweight + mini.down.weight){
					graph.remove(mini.down);
					mini.down.rweight = mini.down.weight + mini.rweight;
					graph.add(mini.down);
					
				}
			}
			if(mini.left!=null && mini.left.visited==false){
				if (mini.left.rweight==-1){
					mini.left.rweight=mini.rweight + mini.left.weight; 
					graph.add(mini.left);}
				else if (mini.left.rweight > mini.rweight + mini.left.weight){
					graph.remove(mini.left);
					mini.left.rweight = mini.left.weight + mini.rweight;
					graph.add(mini.left);
					
				}
			}
			if(mini.right!=null && mini.right.visited==false){
				if (mini.right.rweight==-1){
					mini.right.rweight=mini.rweight + mini.right.weight; 
					graph.add(mini.right);}
				else if (mini.right.rweight > mini.rweight + mini.right.weight){
					graph.remove(mini.right);
					mini.right.rweight = mini.right.weight + mini.rweight;
					graph.add(mini.right);
					
				}
			}
			
			
		}
	}
	 
	 
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(rd.readLine());
		for(int i=0;i<test;i++){
			int row = Integer.parseInt(rd.readLine());
			int col = Integer.parseInt(rd.readLine());
			currx=0;curry=0;
			min =0;
			sum =0;
			graph = new LinkedList();
			int[][] mat = new int[row][col]; 
			Node[][] nodes = new Node[row][col];
			for (int j=0;j<row;j++){
				String[] line = rd.readLine().split(" ");
				for (int k=0;k<col;k++){
					mat[j][k] = Integer.parseInt(line[k]);
					nodes[j][k] = new Node(null, null , null, null , mat[j][k], j,k);
				}
			}
			for(int j=0;j<row;j++){
				for (int k=0;k<col;k++){
					if (k!=0){
						nodes[j][k].left = nodes[j][k-1];
					}
					if(j!=0){
						nodes[j][k].up = nodes[j-1][k];
					}
					if(j!=row-1){
						
						nodes[j][k].down = nodes[j+1][k];
					}
					if(k!=col-1){
						nodes[j][k].right = nodes[j][k+1];
					}
					
			}
			}
			find(nodes , row,col);
			System.out.println(nodes[row-1][col-1].rweight);
		}
	}

}

class Node{
	int rweight =-1;
	int x = 0; int y =0;
	Node up = null;
	Node down = null;
	Node left = null;
	Node right = null;
	int weight = 0;
	boolean visited = false;
	Node(Node a , Node b , Node c, Node d, int w , int _x , int _y){
		up = a; down =b; left = c; right = d; weight = w ;x = _x ; y = _y;
	}
	
	
	
}
