package database;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mapping.MapConstants;



public class FedxPackage implements MapConstants, PackageConstants{

	private int 				source			= 0;
	private int 				destination		= 0;
	
	private Integer[] 			path			= new Integer[NUMBER_OF_CITIES];
	
	private String 				trackNo			= "";
	private String				date			= "";			//needs updates
	private String				time			= "";			//needs updates
	private String				location		= "";			//needs updates
	private String				status			= "";			//needs updates
	
	private String				weight			= "";
	private String				dimension		= "";
	
	private ArrayList<String>	history			= new ArrayList<String>();			//needs updates
	
	private int					activity		= 0;
	private int 				currentCity		= 0;
	private int 				nextCity		= 0;
	private int 				distance		= 0;
	
	private boolean				newUpdate		= false;
	public boolean				lastUpdate		= false;
	
	DecimalFormat 				two				= new DecimalFormat();
	
	public FedxPackage(String trackNo) {						
		source			= randSrc();
		destination		= randDest();
		path			= MAP.search(source, destination);
		this.trackNo	= trackNo;
		
		date			= currentDate();
		time			= currentTime();		
		location		= CITY[source];							
		status			= ACTIVITY[0];
		
		weight			= randWeight();
		dimension		= RAND.nextInt(20) + "x" + RAND.nextInt(20) + "x" + RAND.nextInt(20) + " inch";
		
		activity		= 0;
		currentCity		= 0;		
		nextCity		= 0;											
		distance		= 0;									
		
		newUpdate		= true;
		lastUpdate		= false;
		
		/********history********/
		// Header
		String hist		= String.format("TRACKING No.: %-24s %-20s", this.trackNo, "SERVICE: FedEx" + "\n");
		history.add(hist);
		
		// Weight - Dimension
		hist		= String.format("WEIGHT: %-30s DIMENSION: %-20s", weight, dimension + "\n");
		history.add(hist);
		
		// Source - Destination
		hist		= String.format("SOURCE: %-30s DESTINATION: %-20s", CITY[source], CITY[destination] + "\n\n" + "TRAVEL HISTORY: " + "\n");
		history.add(hist);
		
		// Column Names
		hist		= String.format("%-2s %-20s %-20s %-20s %-20s", " ", "DATE", "TIME", "ACTIVITY", "LOCATION");
		history.add(hist);
	}							

	public synchronized void update(){
		if(newUpdate == true){
			newUpdate = false;
			addHistory();
			
			lastUpdate	= (status == "Delivered") ? true : false;
			return;
		}
				
		if(distance <= 0){
			newUpdate		= true;
			currentCity		= nextCity;
			if(path[nextCity] != destination)
				nextCity	+= 1;
			activity		+= 1;
			distance		= CITYMATRIX[path[currentCity]][path[nextCity]];
		}
		
		else{
			switch(status){
			
			case "In transit":
				distance		-= TRAVELLED;
				break;
				
			case "Arrived":
				newUpdate		= true;
				activity		+= 1;
				break;
				
			case "Departed":
				newUpdate		= true;
				activity = (path[nextCity] == destination) ? 4 : 1;
				break;
			}
		}
		
		if(newUpdate == true){
			date			= currentDate();
			time			= currentTime();		
			location		= CITY[path[currentCity]];
			status			= (path[currentCity] == destination) ? "Delivered" : ACTIVITY[activity];
		}
	}
	
	public void addHistory(){
		String hist	= "";
		hist = String.format("%-2s %-20s \n %-22s %-20s %-20s %-20s", "*", date, " ", time, status, location);
		history.add(hist);
	}
	
	public int randSrc(){
		int src = RAND.nextInt(NUMBER_OF_CITIES) + 1;
		return src;
	}
	
	public int randDest(){
		int dest;
		do{
			dest = RAND.nextInt(NUMBER_OF_CITIES) + 1;
		}while(dest == source);
		return dest;
	}
	
	public String currentDate(){
		int[] dateInt	= FAST_CLOCK.getDate();
		
		String dateStr	= dateInt[0] + "/" + dateInt[1] + "/" + dateInt[2];
		return dateStr;
	}
	
	public String currentTime(){
		int[] timeInt	= FAST_CLOCK.getTime();
				
		String dateStr	= timeInt[0] + ":" + timeInt[1] + ":" + timeInt[2];
		return dateStr;
	}
	
	public String randWeight(){
		two.setMaximumFractionDigits(2);
		two.setMinimumFractionDigits(2);
		
		String wt	= two.format(RAND.nextDouble() + (double)(RAND.nextInt(100)+1));
		
		return (wt + " lbs");
	}
	
	/************************GETTERS************************/

	public Integer[] getPath() {
		return path;
	}

	public synchronized ArrayList<String> getHistory() {
		return history;
	}

		
}
