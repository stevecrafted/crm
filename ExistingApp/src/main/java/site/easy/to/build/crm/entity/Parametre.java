package site.easy.to.build.crm.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Parametre")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "valeur", precision = 10, scale = 0)
    private BigDecimal valeur;

    public Parametre() {
    }

    public Parametre(String nom, BigDecimal valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }
}