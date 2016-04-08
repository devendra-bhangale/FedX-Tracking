package database;



public class TrackingNumber implements PackageConstants{

	public synchronized String randTrackNo(){
		int trackNo;
		do{
			trackNo = RAND.nextInt(2*MAX_PKG) + TRACK_NUMBER_BASE;
		}while(pkgDATABASE.containsKey(Integer.toString(trackNo)));
		
		return Integer.toString(trackNo);
	}
	
}

