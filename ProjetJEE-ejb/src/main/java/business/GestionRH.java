/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Candidat;
import entities.Competence;
import entities.Poste;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.CandidatFacadeLocal;
import repositories.PosteFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
public class GestionRH implements GestionRHLocal {
    @EJB
    private PosteFacadeLocal posteFacadeLocal;
    @EJB
    private CandidatFacadeLocal candidatFacadeLocal;    
    
    /**
     * Enlever un poste de la liste des poste disponible
     * @param idPoste
     */
    public void supprimerPoste(Long idPoste){
        Poste poste = posteFacadeLocal.find(idPoste);
        if(poste == null){
            System.out.println("Le poste n'existe pas");
        }else{
            posteFacadeLocal.supprimerPoste(poste);
            System.out.println("Poste supprimé avec succès");
        }
    }
    /**
     * 
     * @param idCandidat
     * @param idPoste 
     */
    public void candidater(Long idCandidat, Long idPoste){
        Candidat candidat = candidatFacadeLocal.find(idCandidat);
        if(candidat == null){
            System.out.println("Le candidat n'existe pas");
        }else{
            Poste poste = posteFacadeLocal.find(idPoste);
            if(poste == null){
                System.out.println("Le poste n'existe pas");
            }else{
                candidat.postuler(poste);
                poste.ajouterUnCandidature(candidat);
            }
        }
    }
    
    public void retirerUnCandidature(Long idCandidat, Long idPoste){
        Candidat candidat = candidatFacadeLocal.find(idCandidat);
        if(candidat == null){
            System.out.println("Le candidat n'existe pas");
        }else{
            Poste poste = posteFacadeLocal.find(idPoste);
            if(poste == null){
                System.out.println("Le poste n'existe pas");
            }else{
                candidat.retirerLeCandidature(poste);
                poste.supprimerUnCandidature(candidat);
            }
        }
    }
    
    public Poste consulterUnPoste(Long idPoste){
        Poste poste = posteFacadeLocal.find(idPoste);
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
    public List<Poste> getListPostes(){
        return posteFacadeLocal.findAll();
    }
    
    public void creerUnPoste(String nom, String presEnt, String presPoste){
        Poste poste = new Poste(nom,presEnt,presPoste,new ArrayList<Competence>());
        posteFacadeLocal.create(poste);
        System.out.println("Nouveau poste créé avec succès!");
    }
}
