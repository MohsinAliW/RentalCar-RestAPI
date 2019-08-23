package warraich.mohsin.technicaltest.models;

import java.util.ArrayList;

/**
 * Model class that contains the data types included in the API response.
 */
public class Response {
	
	private String supplier_id;
	
	private String pickup;
	
	private String dropoff;
	
	private ArrayList<Ride> options;
	
	public String getSupplierId() {
		return supplier_id;
	}
	
	public void setSupplierId(String supplierId) {
		supplierId = supplier_id;
	}
	
	public String getPickup() {
		return pickup;
	}
	
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	
	public String getDropoff() {
		return dropoff;
	}
	
	public void setDropoff(String dropoff) {
		this.dropoff = dropoff;
	}
	
	public ArrayList<Ride> getOptions() {
		return options;
	}
	
	public void setOptions(ArrayList<Ride> options) {
		this.options = options;
	}

}
