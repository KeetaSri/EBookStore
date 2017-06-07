package comkeetasri.github.ebookstore.model;

public class Book {
    private String title, year, imageSrc;
    private double price;
    private int id;

    public Book ( String title, String year, int id, double price, String imageSrc ) {
        this.title = title;
        this.year = year;
        this.id = id;
        this.price = price;
        this.imageSrc = imageSrc;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "Book : "+ title + "\n ID : " + id + "\n Price: "  + price + "\n year: " + year;
    }

}
