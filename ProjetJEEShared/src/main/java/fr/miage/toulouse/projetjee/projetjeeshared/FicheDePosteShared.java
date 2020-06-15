/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.projetjee.projetjeeshared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trongvo
 */
public class FicheDePosteShared implements Serializable{
    private Long id;
    private String nom;
    private StatutDePoste statut;
    private String presentationEntreprise;
    private String presentationPoste;
    private List<CompetenceShared> listeCompetenceRecherchees;
    private List<PersonneShared> listeCandidats;
    private EquipeShared equipeDemandeuse;
    
    public FicheDePosteShared(){};
    public FicheDePosteShared(Long id, String nom, String presentationEntreprise, String presentationPoste, 
                        List<CompetenceShared> listeCompetenceRecherchees, EquipeShared equipeDemandeuse) {
        this.id = id;
        this.nom = nom;
        this.statut = StatutDePoste.EN_ATTENTE;
        this.presentationEntreprise = presentationEntreprise;
        this.presentationPoste = presentationPoste;
        this.listeCompetenceRecherchees = listeCompetenceRecherchees;
        this.listeCandidats = new ArrayList<>();
        this.equipeDemandeuse = equipeDemandeuse;
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

    public List<CompetenceShared> getListeCompetenceRecherchees() {
        return listeCompetenceRecherchees;
    }

    public void setListeCompetenceRecherchees(List<CompetenceShared> listeCompetenceRecherchees) {
        this.listeCompetenceRecherchees = listeCompetenceRecherchees;
    }

    public List<PersonneShared> getListeCandidats() {
        return listeCandidats;
    }

    public void setListeCandidats(List<PersonneShared> listeCandidats) {
        this.listeCandidats = listeCandidats;
    }

    public EquipeShared getEquipeDemandeuse() {
        return equipeDemandeuse;
    }

    public void setEquipeDemandeuse(EquipeShared equipeDemandeuse) {
        this.equipeDemandeuse = equipeDemandeuse;
    }
    
}
