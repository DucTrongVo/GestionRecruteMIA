/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionCEO;
import business.GestionCEOLocal;
import business.GestionRH;
import entities.Competence;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author zamourak
 */
@Stateless
public class ServiceCEO implements ServiceCEOLocal {
    @EJB
    private GestionCEOLocal gestionCEO;
    
    public ServiceCEO() {
    }
        
    @Override
    public ArrayList<Competence> AccederListeCompetence() {
        gestionCEO = new GestionCEO();    
        return gestionCEO.AccederListeCompetence();
        
    }

    @Override
    public ArrayList<Integer> AccederListeComptesFichesDePoste() {
        gestionCEO = new GestionCEO();
        return gestionCEO.AccederListeComptesFichesDePoste();  
    }

    @Override
    public ArrayList<Integer> AccederNombreCandidatures() {
        gestionCEO = new GestionCEO();
        return gestionCEO.AccederNombreCandidatures();
    }


}
