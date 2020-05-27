/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.Equipe;
import entities.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.CompetenceFacade;
import repositories.EquipeFacade;
import repositories.FicheDePosteFacade;
import repositories.PersonneFacade;

/**
 *
 * @author igouane
 */
@Stateless
public class GestionManager implements GestionManagerLocal {
    
    @EJB
    private EquipeFacade equipeFacade;
    
    @EJB
    private CompetenceFacade competenceFacade;
    
    @EJB
    private PersonneFacade personneFacade;
    
    @EJB
    private FicheDePosteFacade ficheDePosteFacade;

    /**
     * retourner la liste de toutes les compétences
     * des membre d'une équipe
     * @param idEquipe l'identificatino de l'équipe
     * @return le liste des compétences
     */
    @Override
    public ArrayList<Competence> listerCompetencesDeEquipe(long idEquipe){
        Equipe equipe = equipeFacade.find(idEquipe);
        ArrayList<Competence> listCompetences = new ArrayList<>() ;
        List<Personne> listPersonne = personneFacade.findByEquipe(equipe);
        for(Personne personne : listPersonne){
            List<Competence> listCompetencesProvisoire = personneFacade.getListCompetences(personne);
            for(Competence competence : listCompetencesProvisoire){
                if(!listCompetences.contains(competence)){
                    listCompetences.add(competence);
                }
            }
        }
        return listCompetences;
    }
    
    @Override
    public void creerDemandeDeCompetence(String nom, String presentationEntreprise, String presentationPoste, List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse){
        ficheDePosteFacade.creerUneDemandeDePoste(nom, presentationEntreprise, presentationPoste, listeCompetenceRecherchees, equipeDemandeuse);
    }

}
