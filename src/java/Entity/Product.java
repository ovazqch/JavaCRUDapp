package Entity;

public class Product {

    private int productID;
    private String productName;
    private String productDescription;
    private int availableQty;
    private int price;
    private boolean internationalShipment;
    private String photo;

    public Product(int productID, String productName, String productDescription, int availableQty, int price, boolean internationalShipment, String photo) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.availableQty = availableQty;
        this.price = price;
        this.internationalShipment = internationalShipment;
        this.photo = photo;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public int getPrice() {
        return price;
    }

    public boolean isInternationalShipment() {
        return internationalShipment;
    }

    public String getPhoto() {
        return photo;
    }

}
