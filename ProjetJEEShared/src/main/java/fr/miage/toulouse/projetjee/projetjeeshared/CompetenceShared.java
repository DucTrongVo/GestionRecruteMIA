/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.projetjee.projetjeeshared;

import java.io.Serializable;

/**
 *
 * @author trongvo
 */
public class CompetenceShared implements Serializable{
    private String nom;
    
    public CompetenceShared(){};
    public CompetenceShared(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
