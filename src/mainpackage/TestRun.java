package mainpackage;

import java.util.Scanner;
import java.util.Map.Entry;

import mapping.MapConstants;
import database.FedxPackage;
import database.PackageConstants;



public class TestRun implements MapConstants, PackageConstants{

	private static Scanner	scan		= new Scanner(System.in);
	
	private static String	trackNo		= "";
	private static boolean	done		= false;
		
	public static void main(String ... arg) {		
		if(pkgDATABASE.size() < MAX_PKG){
			for(int i=0; i<(RAND.nextInt(BATCH_SIZE)+BATCH_SIZE); i++){//random batch of min 25000 to max 50000 pkgs arecreated
				trackNo = TRACK_NUMBER.randTrackNo();
				pkgDATABASE.put(trackNo, new FedxPackage(trackNo));
			}
		}
		
		UpdateThread thread1 = new UpdateThread();
		thread1.setName("thread1.UPDATE");
		thread1.start();
		
		while(true){	
			try{
				if(pkgDATABASE.size() < MAX_PKG){			
					for(int i=0; i<(RAND.nextInt(BATCH_SIZE)+BATCH_SIZE); i++){
						trackNo = TRACK_NUMBER.randTrackNo();
						pkgDATABASE.put(trackNo, new FedxPackage(trackNo));
					}
				}
				
				try{
					System.out.println("Enter track number: ");
					trackNo = scan.nextLine();
					
					synchronized (Thread.currentThread()) {
						for(String hist : pkgDATABASE.get(trackNo).getHistory())
							System.out.println(hist);
					}
					
					System.out.println("\n");
				}catch(Exception e){
					System.out.println("Error 404: Package not found." + "\n" + "Please check the tracking number.");
				}
				
				try {
					System.out.println("Wait...");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}catch(Exception e){}
		}		
	}
	
}







