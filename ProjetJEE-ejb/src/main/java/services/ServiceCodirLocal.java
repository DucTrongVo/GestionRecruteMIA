/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Local;

/**
 *
 * @author trongvo
 */
@Local
public interface ServiceCodirLocal {
    public void validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste);
    public void feuVertCandidat(Long idPersonne, Long idPoste);
    public void feuRougeCandidat(Long idPersonne, Long idPoste);
}