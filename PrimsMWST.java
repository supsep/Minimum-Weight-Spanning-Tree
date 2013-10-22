/* MWST.java
   Sepehr Taheri
   
   Prims Algorithm to find a minimum weight spanning tre
   
*/

import java.util.Scanner;
import java.io.File;


public class MWST{

	public static final int inf = 5555555;  // Initialize Infinity

	static int mwst(int[][] G){
		
		//Initialize Variables
		
		int numVerts = G.length;
    int numEdges;   // Number of Edges
    
    boolean[] visited = new boolean[numVerts]; // Array for Visited Vertices
    
    int i;		// Counter for for loop
    int j;		// Counter for foor loop 2
    int q =0; // Global counter for pos. of edges
    int z =0; // Global counter for pos. of edges   
    
    int min;  // Flag for Minimum Wegith Edge
    int totalWeight = 0; // MWST Total Weight
    
    //Begin Process
    
    
    for(i=0; i < numVerts ; i++) { //Traverse [][]
    	visited[i]=false;        // Initialize all vertices as not visted
    	for(j=0;j<numVerts;j++){   //Assigning Zeroes in G as Infinity (5555555) 
           if(G[i][j]==0)
           G[i][j]= inf ;
         }
        }
        
        

    visited[0]=true;  // Initialize first as visited
    numEdges=0;				// Initialize edges 
    
    

    while(numEdges < numVerts-1){ // While edges visited is < numVerts
    	
       min=inf;     // Initialize minimum as 0/Infinity 

       for(i=0;i<numVerts;i++)   // Loop through rows
       {
          if(visited[i]==true){ // If current row is being visited
          	
             for(j=0;j<numVerts;j++){ // Loop through columns
         	
                if(visited[j]==false){ // Loop through non visited columns
            	
                if(min >= G[i][j]) // If G[i][j] Doesnt equal infinity/previous min.
                
               {
               min=G[i][j]; // This is new minimum
               q = i;     // Set for scope of i for use in totalWeight
               z = j;			// Set for scope of j for use in totalWeight 

               }
            }
          }
        }
      }
      
      
    visited[z]=true;				// Update current as visted
    totalWeight = G[q][z]+ totalWeight;  // Sum up weights of MWST
    numEdges=numEdges+1; // Increment edge counter
    
    }
		
		return totalWeight;
	}


	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		int graphNum = 0;
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(!s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				G[i] = new int[n];
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			if (!isConnected(G)){
				System.out.printf("Graph %d is not connected (no spanning trees exist...)\n",graphNum);
				continue;
			}
			int totalWeight = mwst(G);
			System.out.printf("Graph %d: Total weight is %d\n",graphNum,totalWeight);
				
		}
	}

	/* isConnectedDFS(G, covered, v)
	   Used by the isConnected function below.
	   You may modify this, but nothing in this function will be marked.
	*/
	static void isConnectedDFS(int[][] G, boolean[] covered, int v){
		covered[v] = true;
		for (int i = 0; i < G.length; i++)
			if (G[v][i] > 0 && !covered[i])
				isConnectedDFS(G,covered,i);
	}
	   
	/* isConnected(G)
	   Test whether G is connected.
	   You may modify this, but nothing in this function will be marked.
	*/
	static boolean isConnected(int[][] G){
		boolean[] covered = new boolean[G.length];
		for (int i = 0; i < covered.length; i++)
			covered[i] = false;
		isConnectedDFS(G,covered,0);
		for (int i = 0; i < covered.length; i++)
			if (!covered[i])
				return false;
		return true;
	}

}