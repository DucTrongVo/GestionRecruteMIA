/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import entities.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.EquipeFacadeLocal;
import repositories.PersonneFacadeLocal;

/**
 *
 * @author igouane
 */
@Stateless
public class GestionManager implements GestionManagerLocal {
    
    @EJB
    private EquipeFacadeLocal equipeFacade;
    
    @EJB
    private PersonneFacadeLocal personneFacade;

    private GestionRHLocal gestionRH;

    /**
     * retourner la liste de toutes les compétences
     * des membre d'une équipe
     * @param idEquipe l'identificatino de l'équipe
     * @return le liste des compétences
     */
    @Override
    public ArrayList<Competence> listerCompetencesDeEquipe(long idEquipe){
        
        Equipe equipe = equipeFacade.find(idEquipe);
        ArrayList<Competence> listTotalCompetences = new ArrayList<>() ;
        List<Personne> listPersonne = personneFacade.findByEquipe(equipe);
        for(Personne personne : listPersonne){
            List<Competence> listCompetencesProvisoire = personneFacade.getListCompetences(personne);
            for(Competence competence : listCompetencesProvisoire){
                if(!listTotalCompetences.contains(competence)){
                    listTotalCompetences.add(competence);
                }
            }
        }
        return listTotalCompetences;
    }
    
    @Override
    public FicheDePoste creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<Long> listIdCompetences, String nomEquipe){
        return gestionRH.creerFicheDePosteDeDemande(nomFicheDePoste, listIdCompetences, nomEquipe);
    }

}
