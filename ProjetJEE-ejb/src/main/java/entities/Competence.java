/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author igouane
 */
@Entity
@XmlRootElement

public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;    
    @ManyToMany(mappedBy = "listeCompetences")
    @XmlTransient
    private List<Personne> listePersonnes;    

    public Competence() {
    }

    public Competence(String nom) {
        this.nom = nom;
    }

    public Competence(String nom, List<Personne> listePersonnes) {
        this.nom = nom;
        this.listePersonnes = listePersonnes;
    }


    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlTransient
    public List<Personne> getListePersonnes() {
        return listePersonnes;
    }

    public void setListePersonnes(List<Personne> listePersonnes) {
        this.listePersonnes = listePersonnes;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Competence[ id=" + id + " ]";
    }
    
}