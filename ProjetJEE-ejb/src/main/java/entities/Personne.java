/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zamourak
 */
@Entity
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    private boolean isCodir;
    
    @ManyToOne
    private Equipe equipe;
    
    
    @ManyToMany
    private ArrayList<Competence> listeCompetences;    

    @OneToMany
    private List<FicheDePoste> listePostulation;

    public Personne() {
    }

    public Personne(String nom, String prenom, boolean isCodir, Equipe equipe, ArrayList<Competence> listeCompetences, List<FicheDePoste> listePostulation) {
        this.nom = nom;
        this.prenom = prenom;
        this.isCodir = isCodir;
        this.equipe = equipe;
        this.listeCompetences = listeCompetences;
        this.listePostulation = listePostulation;
    }
    

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

    public boolean isIsCodir() {
        return isCodir;
    }

    public void setIsCodir(boolean isCodir) {
        this.isCodir = isCodir;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public ArrayList<Competence> getListeCompetences() {
        return listeCompetences;
    }

    public void setListeCompetences(ArrayList<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public List<FicheDePoste> getListePostulation() {
        return listePostulation;
    }

    public void setListePostulation(List<FicheDePoste> listePostulation) {
        this.listePostulation = listePostulation;
    }

    /**
     * Permettre au collaborateur de postuler à une poste
     * @param poste le poste à postuler
     */
    public void postuler(FicheDePoste poste){
        this.listePostulation.add(poste);
    }
    
    /**
     * permettre au collaborateur à retirer sa candidature d'une poste
     * @param poste le poste à retirer le candidature
     */
    public void retirerLeCandidature(FicheDePoste poste){
        this.listePostulation.remove(poste);
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
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personne[ id=" + id + " ]";
    }
    
}