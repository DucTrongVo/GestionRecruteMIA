/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.FicheDePoste;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author igouane
 */
@Local
public interface GestionManagerLocal {
    
    ArrayList<Competence> listerCompetencesDeEquipe(long idEquipe);
    
    public FicheDePoste creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe);
 
}
