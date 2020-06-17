/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionRHLocal;
import fr.miage.toulouse.projetjee.projetjeeshared.Constants;
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
    public String validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste) {
        gestionRH.validerLaCreationUnPoste(idPersonne, idPoste, presentationEntreprise, presentationPoste);
        return Constants.POST_VALIDATION_SUCCESS;
    }

    @Override
    public String feuVertCandidat(Long idPersonne, Long idPoste) {
        gestionRH.feuVertCandidat(idPersonne, idPoste);
        return Constants.GREEN_LIGHT_SUCCESS;
    }

    @Override
    public String feuRougeCandidat(Long idPersonne, Long idPoste) {
        gestionRH.feuRougeCandidat(idPersonne, idPoste);
        return Constants.RED_LIGHT_SUCCESS;
    }
}
