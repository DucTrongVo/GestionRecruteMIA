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
import javax.persistence.OneToMany;

/**
 *
 * @author igouane
 */
@Entity
public class Candidat implements Serializable {
    private String nom;
    private String prenom;
    
    @OneToMany
    private List<Poste> listePostulation;
    
    public Candidat(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    protected Candidat() {
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public List<Poste> getListePostulation(){
        return this.listePostulation;
    }
    /**
     * Permet le candidature à postuler 
     * @param poste le poste à postuler
     */
    public void postuler(Poste poste){
        listePostulation.add(poste);
        //poste.ajouterUnCandidature(this);
    }

    /**
     * Permet un candidat à retire son candidature d'un poste
     * @param poste le poste à retirer le candidature
     */
    public void retirerLeCandidature(Poste poste){
        listePostulation.remove(poste);
        //poste.supprimerUnCandidature(this);
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
        if (!(object instanceof Candidat)) {
            return false;
        }
        Candidat other = (Candidat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Candidat[ id=" + id + " ]";
    }
    
}
