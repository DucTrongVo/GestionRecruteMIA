/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionRH;
import business.GestionRHLocal;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author trongvo
 */
@Stateless
public class ServiceCodir implements ServiceCodirLocal {
    
    @EJB
    private GestionRHLocal gestionRH;
    
    @Override
    public void validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste, FicheDePosteShared posteShared) {
        gestionRH.validerLaCreationUnPoste(idPersonne, idPoste, presentationEntreprise, presentationPoste, posteShared);
    }

    @Override
    public void feuVertCandidat(Long idPersonne, Long idPoste) {
        gestionRH.feuVertCandidat(idPersonne, idPoste);
    }

    @Override
    public void feuRougeCandidat(Long idPersonne, Long idPoste) {
        gestionRH.feuRougeCandidat(idPersonne, idPoste);
    }
}
