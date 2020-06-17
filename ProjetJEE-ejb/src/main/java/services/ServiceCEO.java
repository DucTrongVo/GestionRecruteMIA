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
    public String accederListeCompetence() {
        String competences = "";  
        ArrayList<Competence> lc = gestionCEO.accederListeCompetence();
        for(Competence competence : lc){
            competences += competences.length() > 0 ? "; "+competence.getNom() : competence.getNom();
        }
        return competences;
    }

    @Override
    public String accederListeComptesFichesDePoste() {
        String listComptesFDP = "";
        ArrayList<Integer> lcfdp = gestionCEO.accederListeComptesFichesDePoste();  
        for(int nb : lcfdp){
            listComptesFDP += listComptesFDP.length() > 0 ? "; "+Integer.toString(nb): Integer.toString(nb);
        }
        return listComptesFDP;
    }

    @Override
    public String accederNombreCandidatures() {
        String nbrCandidat = "";
        ArrayList<Integer> lnc = gestionCEO.accederNombreCandidatures();
        for(int nb : lnc){
            nbrCandidat += nbrCandidat.length() > 0 ? "; "+Integer.toString(nb): Integer.toString(nb);
        }
        return nbrCandidat;
    }


}
