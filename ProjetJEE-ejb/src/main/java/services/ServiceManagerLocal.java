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
public interface ServiceManagerLocal {
    public String getAllCompetencesEquipe(Long idEquipe);
    public String creerFicheDePosteDeDemande(String nomFicheDePoste, String nomCompetences, String nomEquipe);
}
