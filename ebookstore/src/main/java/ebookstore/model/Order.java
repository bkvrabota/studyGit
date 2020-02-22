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
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private int idOrder;
    @Column(name = "statusorder")
    private String statusOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client")
    private Client client;
    @Column(name = "dateexecution")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateExecution;
    @Column(name = "orderprice")
    private double orderPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="book_orders",
            joinColumns = { @JoinColumn(name = "idorder") },
            inverseJoinColumns = { @JoinColumn(name = "idbook") })
    private Set<Book> buyBooks = new HashSet<>();

    public Order() {
    }

    public Order(int idOrder, Client client, String statusOrder, Set<Book> buyBooks, LocalDate dateExecution) {
        this.idOrder = idOrder;
        this.client = client;
        this.statusOrder = statusOrder;
        this.buyBooks = buyBooks;
        this.dateExecution = dateExecution;

        setOrderPrice(buyBooks, orderPrice);
    }

    public int getIdOrder() {
        return idOrder;
    }

    public Client getClient() {
        return client;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public Set<Book> getBuyBooks() {
        return buyBooks;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public LocalDate getDateExecution() {
        return dateExecution;
    }

    private void setOrderPrice(Set<Book> buyBooks, double orderPrice) {

        for (Book book : buyBooks) {
            orderPrice += book.getPrice();
        }
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order [id=" + idOrder +
                ", statusOrder=" + statusOrder +
                ", client=" + client +
                ", dateExecution=" + dateExecution +
                ", buyBooks=" + buyBooks +
                ", orderPrice=" + orderPrice +
                "]";
    }
}
