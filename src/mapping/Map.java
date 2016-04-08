package mapping;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Map implements MapConstants{
	
	public Integer[] search(int source, int destination){
		String path 	= source + " to " + destination;
		
		if(!(mapDATABASE.containsKey(path))){
			addPath(source, destination);
		}
		
		return mapDATABASE.get(path);
	}
	
	public void addPath(int source, int destination){
		String path					= source + " to " + destination;
		Integer[] newPath			= djALGO.returnPath(destination, source);		//returns a path from source towards destination
		mapDATABASE.put(path, newPath);
		
		String reversePath			= destination + " to " + source;
		Integer[] newReversePath	= new Integer[newPath.length];
		if(!(mapDATABASE.containsKey(reversePath))){
			for(int i=newPath.length-1; i>=0; i--)
				newReversePath[(newPath.length-1)-i] = newPath[i];
			
			mapDATABASE.put(reversePath, newReversePath);
		}
	}
	
}