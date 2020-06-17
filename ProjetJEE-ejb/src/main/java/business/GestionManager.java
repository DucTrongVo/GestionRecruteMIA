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
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.CompetenceFacadeLocal;
import repositories.EquipeFacadeLocal;
import repositories.FicheDePosteFacadeLocal;
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
    
    @EJB
    private CompetenceFacadeLocal competenceFacade;
    
    @EJB
    private FicheDePosteFacadeLocal posteFacade;

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
    
    /**
     * creer un fiche de poste de status EN ATTENTE pour répondre à un demande de compétence d'un manager
     * @param nomFicheDePoste le nom du poste
     * @param nomCompetences la liste des noms des compétences cherchées
     * @param nomEquipe le nom d'équipe qui faire la demande
     * @return un fiche de poste de status EN ATTENTE
     */
    @Override
    public FicheDePoste creerFicheDePosteDeDemande(String nomFicheDePoste, String nomCompetences, String nomEquipe){
        ArrayList<String> comptences = new ArrayList<>();
        String[] lcs = nomCompetences.split(",");
        comptences.addAll(Arrays.asList(lcs));
        Equipe equipe = equipeFacade.findByNom(nomEquipe);
        ArrayList<Competence> listCompetences = new ArrayList<>();
        for (String nomC : comptences) {
            listCompetences.add(competenceFacade.findByNomCompetence(nomC));
        }
        return(posteFacade.creerUneFicheDePoste(nomFicheDePoste, listCompetences, equipe));
        //return gestionRH.creerFicheDePosteDeDemande(nomFicheDePoste, comptences, nomEquipe);
    }

}
