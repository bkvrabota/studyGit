package ebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ebookstore.utils.LocalDateDeserializer;
import ebookstore.utils.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbook")
    private int idBook;
    @Column(name = "available")
    private boolean available;
    @Column(name = "genre")
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "amount")
    private int amount;
    @Column(name = "datepublic")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datePublic;

    @ManyToMany(cascade=CascadeType.ALL, targetEntity=Order.class, fetch = FetchType.LAZY, mappedBy = "buyBooks")
    private Set<Order> orders = new HashSet<Order>();

    @OneToMany(cascade=CascadeType.ALL, targetEntity=Request.class, fetch = FetchType.LAZY, mappedBy = "requestBooks")
    private Set<Request> requests = new HashSet<Request>();

    public Book() {
    }

    public Book(int idBook, String genre, String publisher, String author, String title, String description,
                double price, LocalDate datePublic, int amount) {
        this.idBook = idBook;
        this.genre = genre;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.datePublic = datePublic;

        setAvailable(true);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDatePublic() {
        return datePublic;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book [id=" + idBook +
                ", genre=" + genre +
                ", publisher=" + publisher +
                ", author=" + author +
                ", title=" + title +
                ", description=" + description +
                ", price=" + price +
                ", amount=" + amount +
                ", datePublic=" + datePublic +
                "]";
    }
}
