package mainpackage;

import java.util.Map.Entry;

import mapping.MapConstants;
import database.FedxPackage;
import database.PackageConstants;



public class UpdateThread extends Thread implements PackageConstants, MapConstants{

	public UpdateThread() {
	}

	@Override
	public void run() {
		while(true){	
			try{
				synchronized(this){
					for(Entry<String, FedxPackage> entry : pkgDATABASE.entrySet()){
						if(!entry.getValue().lastUpdate)
							entry.getValue().update();
					}
				}
				
				try{
					Thread.sleep(1000);						//1000 msec = 1 sec => 10min in fast clock
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}catch(Exception e){}
		}
	}
}
