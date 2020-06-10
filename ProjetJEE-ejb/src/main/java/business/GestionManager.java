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
import fr.miage.toulouse.projetjee.projetjeeshared.Constants;
import static fr.miage.toulouse.projetjee.projetjeeshared.Constants.EQUIPE_NOT_EXIST;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
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
    
    private GestionRH gestionRH;

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
