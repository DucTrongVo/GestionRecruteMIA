/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.Equipe;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author igouane
 */
@Local
public interface GestionManagerLocal {
    
    ArrayList<Competence> listerCompetencesDeEquipe(long idEquipe);
    
    void creerDemandeDeCompetence(String nom, List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse);

    
}
