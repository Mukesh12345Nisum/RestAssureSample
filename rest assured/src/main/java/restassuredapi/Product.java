package restassuredapi;

public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private double discount;
    private double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Product(int id, String title, String description, double price, double discount, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
    }




}
