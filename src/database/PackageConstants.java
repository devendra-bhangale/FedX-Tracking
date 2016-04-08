package database;

import java.util.HashMap;
import java.util.Random;



public interface PackageConstants {

	final static int CLOCK_SCALE				= 1000;
	
	final static int MAX_PKG					= 100000;		//100000;
	final static int TRACK_NUMBER_BASE			= 100000;		//more than 1,00,000 including 1,00,000
	final static int BATCH_SIZE					= 25000;		//25000;
	final static int TRAVELLED					= 50;
	
	final static String TAB						= "\t\t\t";
	final static String LONGTAB					= TAB+TAB;
	final static String[] ACTIVITY				= {"Shipped", "In transit", "Arrived", "Departed", "In transit", "Delivered"};
	
	TrackingNumber TRACK_NUMBER					= new TrackingNumber();
	FastClock FAST_CLOCK						= new FastClock();
	Random RAND									= new Random();
	
	HashMap<String, FedxPackage> pkgDATABASE	= new HashMap<String, FedxPackage>();
}


