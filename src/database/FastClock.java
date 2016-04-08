package database;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;



public class FastClock extends Clock implements PackageConstants{

	//Starting point of the fast clock
    private ZonedDateTime begin;

    //Creates a new fast clock. It will use 'now' as the starting point.
    public FastClock() {
        begin = ZonedDateTime.now();
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        //Gets 'now'.
        ZonedDateTime now	= ZonedDateTime.now();
        long nowSeconds		= now.toInstant().getEpochSecond();
        long beginSeconds	= begin.toInstant().getEpochSecond();

        //Difference between the starting point of this clock and 'now'.
        long delta			= nowSeconds - beginSeconds;
        
        //Creates a datetime that is in the future.
        return begin.plusSeconds(delta * CLOCK_SCALE).toInstant();
    }

    public int[] getTime(){
    	String arr		= instant().toString();
    	int[] myTime 	= new int[3];
    	
    	myTime[0] = Integer.parseInt("" + arr.charAt(11) + "") * 10 + Integer.parseInt("" + arr.charAt(12) + "");
    	myTime[1] = Integer.parseInt("" + arr.charAt(14) + "") * 10 + Integer.parseInt("" + arr.charAt(15) + "");
    	myTime[2] = Integer.parseInt("" + arr.charAt(17) + "") * 10 + Integer.parseInt("" + arr.charAt(18) + "");
    	
    	return myTime;
    }
    
    public int[] getDate(){
    	String arr		= instant().toString();
    	int[] myDate	= new int[3];
    	
    	myDate[0]		= Integer.parseInt("" + arr.charAt(0) + "") * 1000 + Integer.parseInt("" + arr.charAt(1) + "") * 100 + Integer.parseInt("" + arr.charAt(2) + "") * 10 + Integer.parseInt("" + arr.charAt(3) + "");
    	myDate[1]		= Integer.parseInt("" + arr.charAt(5) + "") * 10 + Integer.parseInt("" + arr.charAt(6) + "");
    	myDate[2]		= Integer.parseInt("" + arr.charAt(8) + "") * 10 + Integer.parseInt("" + arr.charAt(9) + "");
    	
    	return myDate;
    }
    
}
