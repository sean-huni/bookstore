package xyz.lib.bookstore.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.domain
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 14:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Document(collection = "book")
public class Book extends AbstractDO {

    @Transient
    public static final String SEQUENCE_NAME = "book_sequence";

    private String title;
    private String author;
    private String publisher;
    private Date datePublished;
    private String language;
    private String category;
    private Integer pages;
    private String format;
    private String isbn;
    private Double weight;
    private BigDecimal listPrice;
    private BigDecimal ourPrice;
    private Boolean active;
    private String description;
    private Integer quantity;
    private String imgPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(BigDecimal ourPrice) {
        this.ourPrice = ourPrice;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean isActive) {
        this.active = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getDatePublished(), book.getDatePublished()) &&
                Objects.equals(getLanguage(), book.getLanguage()) &&
                Objects.equals(getCategory(), book.getCategory()) &&
                Objects.equals(getPages(), book.getPages()) &&
                Objects.equals(getFormat(), book.getFormat()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getWeight(), book.getWeight()) &&
                Objects.equals(getListPrice(), book.getListPrice()) &&
                Objects.equals(getOurPrice(), book.getOurPrice()) &&
                Objects.equals(active, book.active) &&
                Objects.equals(getDescription(), book.getDescription()) &&
                Objects.equals(getQuantity(), book.getQuantity()) &&
                Objects.equals(getImgPath(), book.getImgPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPublisher(), getDatePublished(), getLanguage(), getCategory(), getPages(), getFormat(), getIsbn(), getWeight(), getListPrice(), getOurPrice(), active, getDescription(), getQuantity(), getImgPath());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", datePublished=" + datePublished +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                ", pages=" + pages +
                ", format='" + format + '\'' +
                ", isbn='" + isbn + '\'' +
                ", weight=" + weight +
                ", listPrice=" + listPrice +
                ", ourPrice=" + ourPrice +
                ", active=" + active +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
