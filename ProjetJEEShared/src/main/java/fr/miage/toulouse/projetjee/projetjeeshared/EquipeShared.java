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
public class EquipeShared implements Serializable{
    private PersonneShared manager;
    private String nom;
    private List<PersonneShared> collaborateurs;

    public EquipeShared(){};
    public EquipeShared(PersonneShared manager, String nom) {
        this.manager = manager;
        this.nom = nom;
    }

    public PersonneShared getManager() {
        return manager;
    }

    public void setManager(PersonneShared manager) {
        this.manager = manager;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<PersonneShared> getCollaborateurs() {
        return collaborateurs;
    }

    public void setCollaborateurs(List<PersonneShared> collaborateurs) {
        this.collaborateurs = collaborateurs;
    }
    
}
