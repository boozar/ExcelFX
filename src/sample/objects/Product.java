package sample.objects;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty productType;
    private SimpleStringProperty manufacturer;
    private SimpleStringProperty specification;
    private SimpleDoubleProperty retailPrice;
    private SimpleDoubleProperty smallWholesale;
    private SimpleDoubleProperty largeWholesale;
//    private SimpleIntegerProperty quantity;

    public Product() {
        this.id = new SimpleIntegerProperty(-1);
        this.productType = new SimpleStringProperty("");
        this.manufacturer = new SimpleStringProperty("");
        this.specification = new SimpleStringProperty("");
        this.retailPrice = new SimpleDoubleProperty(-1);
        this.smallWholesale = new SimpleDoubleProperty(-1);
        this.largeWholesale = new SimpleDoubleProperty(-1);
        //       this.quantity = new SimpleIntegerProperty(-1);
    }

    public Product(int id, String productType, String manufacturer, String specification, double retailPrice, double smallWholesale, double largeWholesale) {
        this.id = new SimpleIntegerProperty(id);
        this.productType = new SimpleStringProperty(productType);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.specification = new SimpleStringProperty(specification);
        this.retailPrice = new SimpleDoubleProperty(retailPrice);
        this.smallWholesale = new SimpleDoubleProperty(smallWholesale);
        this.largeWholesale = new SimpleDoubleProperty(largeWholesale);
//        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProductType() {
        return productType.get();
    }

    public void setProductType(String productType) {
        this.productType.set(productType);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    public String getSpecification() {
        return specification.get();
    }

    public void setSpecification(String specification) {
        this.specification.set(specification);
    }

    public double getRetailPrice() {
        return retailPrice.get();
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice);
    }

    public double getSmallWholesale() {
        return smallWholesale.get();
    }

    public void setSmallWholesale(double smallWholesale) {
        this.smallWholesale.set(smallWholesale);
    }

    public double getLargeWholesale() {
        return largeWholesale.get();
    }

    public void setLargeWholesale(double largeWholesale) {
        this.largeWholesale.set(largeWholesale);
    }

    //   public double getQuantity() { return quantity.get(); }

    //   public void setQuantity( int quantity) { this.quantity.set(quantity); }
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    //    public SimpleIntegerProperty simpleQuantity(){ return quantity;}
    public SimpleDoubleProperty retailPriceProperty() {
        return retailPrice;
    }

    public SimpleDoubleProperty smallWholesaleProperty() {
        return smallWholesale;
    }

    public SimpleDoubleProperty largeWholesaleProperty() {
        return largeWholesale;
    }

    public SimpleStringProperty productTypeProperty() {
        return productType;
    }

    public SimpleStringProperty manufacturerProperty() {
        return manufacturer;
    }

    public SimpleStringProperty specificationProperty() {
        return specification;
    }

    @Override
    public String toString() {
        return "id - " + id.get() +
                ", productType - " + productType.get() +
                ", manufacturer - " + manufacturer.get() +
                ", specification - " + specification.get() +
                ", retailPrice - " + retailPrice.get() +
                ", smallWholesale - " + smallWholesale.get() +
                ", largeWholesale - " + largeWholesale.get()
                //               + ", quantity - " + quantity.get()
                ;
    }
}
