/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import javax.ejb.Local;

/**
 *
 * @author trongvo
 */
@Local
public interface ServiceCodirLocal {
    public String validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste);
    public String feuVertCandidat(Long idPersonne, Long idPoste);
    public String feuRougeCandidat(Long idPersonne, Long idPoste);
}
