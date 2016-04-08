package mapping;

//This is a java program to find the shortest path between source vertex and destination vertex
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class DijkstrasAlgo implements MapConstants{
	
	public Set<Integer> settled;
	public Set<Integer> unsettled;
	
	public int number_of_nodes;
	
	public int[] distances;
	public int[] lastHopVect;
	
	public DijkstrasAlgo(){
		settled				= new HashSet<Integer>();
		unsettled			= new HashSet<Integer>();
		number_of_nodes		= NUMBER_OF_CITIES;
		distances			= new int[number_of_nodes + 1];
		lastHopVect			= new int[number_of_nodes + 1];
	}

	private void dijkstra_algorithm(int source){
		int evaluationNode;

		settled.clear();
		unsettled.clear();
		
		for (int i = 1; i <= number_of_nodes; i++){
			distances[i]	= Integer.MAX_VALUE;
			lastHopVect[i]	= Integer.MAX_VALUE;
		}

		unsettled.add(source);
		distances[source] = 0;
		while (!unsettled.isEmpty()){
			evaluationNode = getNodeWithMinimumDistanceFromUnsettled();
			unsettled.remove(evaluationNode);
			settled.add(evaluationNode);
			evaluateNeighbours(evaluationNode);
		}
	}

	private int getNodeWithMinimumDistanceFromUnsettled(){
		int min;
		int node = 0;

		Iterator<Integer> iterator = unsettled.iterator();
		node	= iterator.next();
		min		= distances[node];
		for (int i = 1; i <= distances.length; i++){
			if (unsettled.contains(i)){
				if (distances[i] <= min){
					min		= distances[i];
					node	= i;
				}
			}
		}
		return node;
	}

	private void evaluateNeighbours(int evaluationNode){
		int edgeDistance = -1;
		int newDistance = -1;

		for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++){
			if (!settled.contains(destinationNode)){
				if (CITYMATRIX[evaluationNode][destinationNode] != Integer.MAX_VALUE){
					edgeDistance	= CITYMATRIX[evaluationNode][destinationNode];
					newDistance		= distances[evaluationNode] + edgeDistance;
					
					if (newDistance < distances[destinationNode]){
						distances[destinationNode]		= newDistance;
						lastHopVect[destinationNode]	= evaluationNode;
					}
					unsettled.add(destinationNode);
				}
			}
		}
	}

	public Integer[] returnPath(int source, int destination){
		dijkstra_algorithm(source);		//main algorithm runs
		
		ArrayList<Integer> shortestPath	= new ArrayList<Integer>();
		shortestPath.clear();
		
		shortestPath.add(destination);
		shortestPath.add(lastHopVect[destination]);
		if(shortestPath.get(shortestPath.size()-1) != source){
			do {
				shortestPath.add(lastHopVect[shortestPath.get(shortestPath.size()-1)]);
			} while (shortestPath.get(shortestPath.size()-1) != source);
		}
				
		return shortestPath.toArray(new Integer[]{});
	}
	
}





/*
	public int[] returnPath(int source, int destination){
		dijkstra_algorithm(source);		//main algorithm runs
		
		int[] shortestPath	= new int[NUMBER_OF_CITIES + 1];
		int i = 1;
		
		shortestPath[i-1]	= destination;
		shortestPath[i]		= lastHopVect[destination];
		do {
			i++;
			shortestPath[i]	= lastHopVect[shortestPath[i - 1]];
		} while (shortestPath[i] != source);
		
		return shortestPath;
	}
*/


/*
	public Integer[] returnPath(int source, int destination){
		dijkstra_algorithm(source);		//main algorithm runs
		
		ArrayList<Integer> shortestPathList		= new ArrayList<Integer>();
		Integer[] shortestPathArray				= new Integer[shortestPathList.size()];
		
		shortestPathArray = shortestPathList.toArray(shortestPathArray);
		
		int i = 1;
		
		shortestPathArray[i-1]		= destination;
		shortestPathArray[i]		= lastHopVect[destination];
		do {
			i++;
			shortestPathArray[i]	= lastHopVect[shortestPathArray[i - 1]];
		} while (shortestPathArray[i] != source);
		
		return shortestPathArray;
	}
*/



