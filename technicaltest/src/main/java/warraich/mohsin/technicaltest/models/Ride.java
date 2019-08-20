package warraich.mohsin.technicaltest.models;

public class Ride {
	
	private String car_type;

    private long price;

    private String supplier;


    public Ride(String car_type, long price, String supplier) {
        this.car_type = car_type;
        this.price = price;
        this.supplier = supplier;

    }
    
    public Ride(String car_type, long price) {
        this.car_type = car_type;
        this.price = price;

    }

    public long getPrice() {
        return price;
    }


    public String getcar_type() {
        return car_type;
    }

    public String getSupplier() {
        return supplier;
    }

}
