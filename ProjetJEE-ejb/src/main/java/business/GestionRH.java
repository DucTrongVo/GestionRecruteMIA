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
import repositories.PersonneFacadeLocal;
import repositories.FicheDePosteFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
public class GestionRH implements GestionRHLocal {
    @EJB
    private FicheDePosteFacadeLocal posteFacadeLocal;
    @EJB
    private PersonneFacadeLocal candidatFacadeLocal;    
    
    /**
     * Enlever un poste de la liste des poste disponible
     * @param idPoste
     */
    public void supprimerPoste(Long idPoste){
        FicheDePoste poste = posteFacadeLocal.find(idPoste);
        if(poste == null){
            System.out.println("Le poste n'existe pas");
        }else{
            posteFacadeLocal.supprimerPoste(poste);
            System.out.println("Poste supprimé avec succès");
        }
    }
    /**
     * 
     * @param idCandidat id du candidat
     * @param idPoste id du poste
     */
    public void candidater(Long idCandidat, Long idPoste){
        Personne candidat = candidatFacadeLocal.find(idCandidat);
        if(candidat == null){
            System.out.println("Le candidat n'existe pas");
        }else{
            FicheDePoste poste = posteFacadeLocal.find(idPoste);
            if(poste == null){
                System.out.println("Le poste n'existe pas");
            }else{
                candidat.postuler(poste);
                poste.ajouterUnCandidature(candidat);
            }
        }
    }
    
    /**
     * 
     * @param idCandidat id du candidat
     * @param idPoste id du poste 
     */
    public void retirerUnCandidature(Long idCandidat, Long idPoste){
        Personne candidat = candidatFacadeLocal.find(idCandidat);
        if(candidat == null){
            System.out.println("Le candidat n'existe pas");
        }else{
            FicheDePoste poste = posteFacadeLocal.find(idPoste);
            if(poste == null){
                System.out.println("Le poste n'existe pas");
            }else{
                candidat.retirerLeCandidature(poste);
                poste.supprimerUnCandidature(candidat);
            }
        }
    }
    /**
     * 
     * @param idPoste du poste à consulter
     * @return 
     */
    public FicheDePoste consulterUnPoste(Long idPoste){
        FicheDePoste poste = posteFacadeLocal.find(idPoste);
        if(poste == null){
            System.out.println("Le poste n'existe pas");
            return null;
        }else{
            return poste;
        }
    }
    /**
     * Retourner la liste des postes disponibles
     * @return 
     */
    public List<FicheDePoste> getListPostes(){
        return posteFacadeLocal.findAll();
    }
    
    public void creerUnPoste(String nom, String statut, String presentationEntreprise, String presentationPoste, List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse){
        FicheDePoste poste = new FicheDePoste(nom, statut, presentationEntreprise, presentationPoste,
                                                listeCompetenceRecherchees,equipeDemandeuse );
        posteFacadeLocal.create(poste);
        System.out.println("Nouveau poste créé avec succès!");
    }
}
