/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.projetjee.projetjeeshared;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author trongvo
 */
public class PersonneShared implements Serializable{
    private Long id;
    private String nom;
    private String prenom;
    private boolean isCodir;
    private boolean isManager;
    private List<CompetenceShared> listeCompetences;
    private EquipeShared equipe;

    public PersonneShared(){};
    public PersonneShared(Long id, String nom, String prenom, List<CompetenceShared> listeCompetences) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.isCodir = false;
        this.isManager = false;
        this.equipe = null;
        this.listeCompetences = listeCompetences;
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

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public List<CompetenceShared> getListeCompetences() {
        return listeCompetences;
    }

    public void setListeCompetences(List<CompetenceShared> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public EquipeShared getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeShared equipe) {
        this.equipe = equipe;
    }
    
    
}
