/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fr.miage.toulouse.projetjee.projetjeeshared.StatutDePoste;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author zamourak
 */
@Entity
public class FicheDePoste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private StatutDePoste statut;
    private String presentationEntreprise;
    private String presentationPoste;
    @OneToMany
    private List<Competence> listeCompetenceRecherchees;
    @OneToMany
    private List<Personne> listeCandidats;
    @OneToOne
    private Equipe equipeDemandeuse;
    
    public FicheDePoste() {
    }

    
    
    public FicheDePoste(String nom, String presentationEntreprise, String presentationPoste, 
                        List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse) {
        this.nom = nom;
        this.statut = StatutDePoste.EN_ATTEND;
        this.presentationEntreprise = presentationEntreprise;
        this.presentationPoste = presentationPoste;
        this.listeCompetenceRecherchees = listeCompetenceRecherchees;
        this.listeCandidats = new ArrayList<Personne>();
        this.equipeDemandeuse = equipeDemandeuse;
    }

    
    
    public Long getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public StatutDePoste getStatut() {
        return statut;
    }

    public void setStatut(StatutDePoste statut) {
        this.statut = statut;
    }

    public String getPresentationEntreprise() {
        return presentationEntreprise;
    }

    public void setPresentationEntreprise(String presentationEntreprise) {
        this.presentationEntreprise = presentationEntreprise;
    }

    public String getPresentationPoste() {
        return presentationPoste;
    }

    public void setPresentationPoste(String presentationPoste) {
        this.presentationPoste = presentationPoste;
    }

    public List<Competence> getListeCompetenceRecherchees() {
        return listeCompetenceRecherchees;
    }

    public void setListeCompetenceRecherchees(List<Competence> listeCompetenceRecherchees) {
        this.listeCompetenceRecherchees = listeCompetenceRecherchees;
    }

    public List<Personne> getListeCandidats() {
        return listeCandidats;
    }

    public void setListeCandidats(List<Personne> listeCandidats) {
        this.listeCandidats = listeCandidats;
    }

    public Equipe getEquipeDemandeuse() {
        return equipeDemandeuse;
    }

    public void setEquipeDemandeuse(Equipe equipeDemandeuse) {
        this.equipeDemandeuse = equipeDemandeuse;
    }

    
    public void supprimerCompetenceAuPoste(Competence c){
        this.listeCompetenceRecherchees.remove(c);
    }
    
    public void ajouterCompetenceAuPoste(Competence c){
        this.listeCompetenceRecherchees.add(c);
    }
    
    /**
     * Supprimer un candidat dans la liste des candidatures
     * @param c candidat qui doit être supprimer au poste
     */
    public void supprimerUnCandidature(Personne c){
        this.listeCandidats.remove(c);
    }
    
    /**
     * Ajouter un candidat dans la liste des candidatures
     * @param c candidat qui postuler au poste
     */
    public void ajouterUnCandidature(Personne c){
        this.listeCandidats.add(c);
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
        if (!(object instanceof FicheDePoste)) {
            return false;
        }
        FicheDePoste other = (FicheDePoste) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FicheDePoste[ id=" + id + " ]";
    }
    
}
