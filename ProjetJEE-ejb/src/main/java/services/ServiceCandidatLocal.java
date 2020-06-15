/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.FicheDePoste;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trongvo
 */
@Local
public interface ServiceCandidatLocal {
    public void creerCandidatSiInexistant(String nom, String prenom);
    public List<FicheDePoste> getPostesDispo();
    public FicheDePoste getPosteAConsulter(Long idPoste);
    public void creerUneCandidature(Long idCandidat, Long idPoste);
    public void retirerUneCandidature(Long idCandidat, Long idPoste);
}
