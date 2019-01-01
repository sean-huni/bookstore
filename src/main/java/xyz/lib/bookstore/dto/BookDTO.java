package xyz.lib.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;
import xyz.lib.bookstore.validation.CategoryConstraint;
import xyz.lib.bookstore.validation.FormatConstraint;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.domain
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 14:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public class BookDTO extends AbstractDTO implements Serializable {
    private static final Long serialVersionUID = 3478349384L;

    @NotEmpty(message = "{error.book.title}")
    private String title;
    private String author;
    private String publisher;
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss")
    private Date datePublished;
    private String language;
    @CategoryConstraint(message = "{error.book.category}")
    private String category;
    private Integer pages;
    @FormatConstraint(message = "{error.book.format}")
    private String format;
    private String isbn;
    private Double weight;
    private BigDecimal listPrice;
    private BigDecimal ourPrice;
    private Boolean active;
    private String description;
    private Integer quantity;
    private MultipartFile bookImage;

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

    public MultipartFile getBookImage() {
        return bookImage;
    }

    public void setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
    }
}
