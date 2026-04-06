package site.easy.to.build.crm.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount", precision = 10, scale = 0)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerLoginInfo customerLoginInfo;

    public Budget() {
    }

    public Budget(BigDecimal amount, CustomerLoginInfo customerLoginInfo) {
        this.amount = amount;
        this.customerLoginInfo = customerLoginInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CustomerLoginInfo getCustomerLoginInfo() {
        return customerLoginInfo;
    }

    public void setCustomerLoginInfo(CustomerLoginInfo customerLoginInfo) {
        this.customerLoginInfo = customerLoginInfo;
    }
}