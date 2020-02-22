package ebookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ebookstore.utils.LocalDateDeserializer;
import ebookstore.utils.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "requests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrequest")
    private int idRequest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client")
    private Client client;
    @Column(name = "daterequest")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateRequest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="requestbooks")
    private Book requestBooks;
    @Column(name = "reqstatus")
    private String reqStatus;

    public Request() {
    }

    public Request(int idRequest, Client client, Book requestBooks, LocalDate dateRequest, String reqStatus) {
        this.idRequest = idRequest;
        this.client = client;
        this.dateRequest = dateRequest;
        this.requestBooks = requestBooks;
        this.reqStatus = reqStatus;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public Book getRequestBooks() {
        return requestBooks;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setStatusReq(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Override
    public String toString() {
        return "Client [id=" + idRequest +
                ", client=" + client +
                ", dateRequest=" + dateRequest +
                ", requestBooks=" + requestBooks +
                ", reqStatus=" + reqStatus +
                "]";
    }
}
