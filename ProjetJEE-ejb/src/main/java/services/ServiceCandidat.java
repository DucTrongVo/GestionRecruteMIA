/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionRHLocal;
import entities.FicheDePoste;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author trongvo
 */
@Stateless
public class ServiceCandidat implements ServiceCandidatLocal {
    @EJB
    private GestionRHLocal gestionRH;
    
    @Override
    public void creerUneCandidature(Long idCandidat, Long idPoste) {
        gestionRH.creerUneCandidature(idCandidat, idPoste);
    }

    @Override
    public void retirerUneCandidature(Long idCandidat, Long idPoste) {
        gestionRH.retirerUnCandidature(idCandidat, idPoste);
    }

    
    @Override
    public FicheDePoste getPosteAConsulter(Long idPoste){
        return gestionRH.consulterUnPoste(idPoste);
    }
    
    @Override
    public List<FicheDePoste> getAllOpenedPoste(){
        return gestionRH.getAllOpenedPoste();
    }
    
    @Override
    public void creerCandidatSiInexistant(String nom, String prenom){
        gestionRH.creerCandidatSiInexistant(nom, prenom);
    }
}
