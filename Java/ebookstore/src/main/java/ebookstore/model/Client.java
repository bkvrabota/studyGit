package ebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclient")
    private int idClient;
    @Column(name = "cardnumber")
    private int cardNumber;
    @Column(name = "phone")
    private String phone;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @OneToMany(cascade=CascadeType.ALL, targetEntity=Order.class, fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Order> orders = new HashSet<Order>();

    @OneToMany(cascade=CascadeType.ALL, targetEntity=Request.class, fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Request> requests = new HashSet<Request>();

    public Client() {
    }

    public Client(int idClient, int cardNumber, String phone, String firstName, String lastName) {
        this.idClient = idClient;
        this.cardNumber = cardNumber;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Client [id=" + idClient +
                ", cardNumber=" + cardNumber +
                ", phone=" + phone +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                "]";
    }
}
