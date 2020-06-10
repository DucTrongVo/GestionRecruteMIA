/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionRH;
import entities.FicheDePoste;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author trongvo
 */
@Stateless
public class ServiceCandidat implements ServiceCandidatRemote {

    private GestionRH gestionRH;
    private List<FicheDePoste> listeDesPostes;
    private FicheDePoste posteAConsulter;
    
    @Override
    public void updateListPostes() {
        listeDesPostes = gestionRH.getPostesDispo();
    }

    @Override
    public void creerUneCandidature(Long idCandidat, Long idPoste) {
        gestionRH.creerUneCandidature(idCandidat, idPoste);
    }

    @Override
    public void retirerUneCandidature(Long idCandidat, Long idPoste) {
        gestionRH.retirerUnCandidature(idCandidat, idPoste);
    }

    @Override
    public void updatePosteAConsulter(Long idPoste) {
        posteAConsulter = gestionRH.consulterUnPoste(idPoste);
    }
    
    public FicheDePoste getPosteAConsulter(Long idPoste){
        this.updatePosteAConsulter(idPoste);
        return this.posteAConsulter;
    }
    
    public List<FicheDePoste> getPostesDispo(){
        this.updateListPostes();
        return this.listeDesPostes;
    }
    
    public void creerCandidat(String nom, String prenom){
        gestionRH.creerCandidat(nom, prenom);
    }

    
}
