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
import repositories.CompetenceFacadeLocal;
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
    private CompetenceFacadeLocal competenceFacade;
    
    @EJB
    private PersonneFacadeLocal personneFacade;

    public ArrayList<Competence> listerCompetencesDeEquipe(long idEquipe){
        Equipe equipe = equipeFacade.find(idEquipe);
        ArrayList<Competence> listCompetences = new ArrayList<>() ;
        List<Personne> listPersonne = personneFacade.findByEquipe(equipe);
        for(int i=0; i<listPersonne.size(); i++){
            Personne personne = listPersonne.get(i);
            List<Competence> listCompetencesProvisoire = competenceFacade.findByPersonne(personne);
            for(int j=0;j<listCompetencesProvisoire.size(); j++){
                listCompetences.add(listCompetencesProvisoire.get(j));
            }
        }
        return listCompetences;
    }
    
    public void creerDemandeDeCompetence(){
        
    }

}
