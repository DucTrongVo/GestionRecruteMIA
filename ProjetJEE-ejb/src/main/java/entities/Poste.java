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
import javax.persistence.OneToMany;

/**
 *
 * @author trongvo
 * @param nomPoste : dénomination du poste
 * @param presEnt : présentation de l'entreprise
 * @param presPoste : présentation du poste
 * @param listeCompetences : la liste des compétences demandés dans le poste
 */
@Entity
public class Poste implements Serializable {

    private String nomPoste;
    private String presEnt;
    private String presPoste;
    @OneToMany
    private List<Competence> listeCompetences;
    @OneToMany
    private List<Candidat> listeCandidatPostule;
    
    public Poste(String nom, String presEnt, String presPoste, List<Competence> listeCompetences){
        this.nomPoste = nom;
        this.presEnt = presEnt;
        this.presPoste = presPoste;
        this.listeCompetences = listeCompetences;
        this.listeCandidatPostule = new ArrayList<Candidat>();
    }
    
    public Poste(){  
    }
    
    public String getNomPoste() {
        return nomPoste;
    }

    public String getPresEnt() {
        return presEnt;
    }

    public String getPresPoste() {
        return presPoste;
    }

    public List<Competence> getListeCompetences() {
        return listeCompetences;
    }
    
    public List<Candidat> getListCandidats(){
        return this.listeCandidatPostule;
    }
    
    public void supprimerCompetenceAuPoste(Competence c){
        this.listeCompetences.remove(c);
    }
    
    public void ajouterCompetenceAuPoste(Competence c){
        this.listeCompetences.add(c);
    }
    
    /**
     * Supprimer un candidat dans la liste des candidatures
     * @param c candidat qui doit être supprimer au poste
     */
    public void supprimerUnCandidature(Candidat c){
        this.listeCandidatPostule.remove(c);
    }
    
    /**
     * Ajouter un candidat dans la liste des candidatures
     * @param c candidat qui postuler au poste
     */
    public void ajouterUnCandidature(Candidat c){
        this.listeCandidatPostule.add(c);
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poste)) {
            return false;
        }
        Poste other = (Poste) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Poste[ id=" + id + " ]";
    }
    
}
