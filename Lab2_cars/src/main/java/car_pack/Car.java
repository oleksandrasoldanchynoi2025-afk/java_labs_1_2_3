package car_pack;

public class Car {

    private int id;
    private String model;
    private int year;
    private int price;
    private String idnum;

    //Конструктор
    public Car(int id, int year, String model, int price, String idnum) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
        this.idnum = idnum;
    }



    public String getModel() {
        return model;
    }

    public int getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    public String getIdNum() {
        return idnum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String toString() {
        //рядок з описом об'єкта
        return id + ") " + model + " | " + year + "р. | " + price + "грн | " + idnum;
    }
}