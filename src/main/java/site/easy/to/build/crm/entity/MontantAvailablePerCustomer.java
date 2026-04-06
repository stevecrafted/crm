package site.easy.to.build.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "MontantAvailablePerCustomer")
public class MontantAvailablePerCustomer {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "total_budget")
    private BigDecimal totalBudget;

    @Column(name = "total_depense_lead")
    private BigDecimal totalDepenseLead;

    @Column(name = "total_depense_ticket")
    private BigDecimal totalDepenseTicket;

    @Column(name = "reste")
    private BigDecimal reste;

    public MontantAvailablePerCustomer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getTotalDepenseLead() {
        return totalDepenseLead;
    }

    public void setTotalDepenseLead(BigDecimal totalDepenseLead) {
        this.totalDepenseLead = totalDepenseLead;
    }

    public BigDecimal getTotalDepenseTicket() {
        return totalDepenseTicket;
    }

    public void setTotalDepenseTicket(BigDecimal totalDepenseTicket) {
        this.totalDepenseTicket = totalDepenseTicket;
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }
}