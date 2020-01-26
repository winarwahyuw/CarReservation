package winarwahyuw.winw.daftarbelanja.model;

public class Data {
//    private int amount;
    private String revCode;
    private String name;
    private String address;
    private String rent_start;
    private String rent_end;
    private String car;
    private String cost;
    private String id;

    public Data(){

    }

    public Data(String mRevCode, String mName, String mAddress, String mRentStart, String mRentEnd, String mCar, String mCost, String id) {
        this.revCode=mRevCode;
        this.name=mName;
        this.address=mAddress;
        this.rent_start=mRentStart;
        this.rent_end=mRentEnd;
        this.car=mCar;
        this.cost=mCost;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public int getAmount() {
//        return amount;
//    }

//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

    public String getRevCode() {
        return revCode;
    }

    public void setRevCode(String revCode) {
        this.revCode = revCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRent_start() {
        return rent_start;
    }

    public void setRent_start(String rent_start) {
        this.rent_start = rent_start;
    }

    public String getRent_end() {
        return rent_end;
    }

    public void setRent_end(String rent_end) {
        this.rent_end = rent_end;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}