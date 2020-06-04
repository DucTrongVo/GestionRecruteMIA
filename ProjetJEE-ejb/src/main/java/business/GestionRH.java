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
import fr.miage.toulouse.projetjee.projetjeeshared.StatutDePoste;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.CompetenceFacade;
import repositories.FicheDePosteFacade;
import repositories.PersonneFacade;

/**
 *
 * @author trongvo
 */
@Stateless
public class GestionRH implements GestionRHLocal {
    @EJB
    private FicheDePosteFacade posteFacade;
    @EJB
    private CompetenceFacade competenceFacade;
    @EJB
    private PersonneFacade personneFacade;
    
    /**
     * Enlever un poste de la liste des poste disponible
     * @param idPoste l'id de la poste à supprimer
     */
    public void supprimerPoste(Long idPoste){
        FicheDePoste poste = posteFacade.find(idPoste);
        if(poste == null){
            System.out.println(Constants.POSTE_NOT_EXIST);
        }else{
            posteFacade.supprimerPoste(poste);
        }
    }
        
    /**
     * permet une personne à candidater dans une poste
     * @param idCandidat id du candidat
     * @param idPoste id du poste
     */
    public void creerUneCandidature(Long idCandidat, Long idPoste){
        Personne candidat = personneFacade.find(idCandidat);
        if(candidat == null){
            System.out.println(Constants.USER_NOT_EXIST);
        }else{
            FicheDePoste poste = posteFacade.find(idPoste);
            if(poste == null){
                System.out.println(Constants.POSTE_NOT_EXIST);
            }else{
                posteFacade.ajouterUneCandidatAuPoste(poste, candidat);
            }
        }
    }
    
    /**
     * Fonction qui permet un candidat de retirer son 
     * candidature d'un poste
     * @param idCandidat id du candidat
     * @param idPoste id du poste 
     */
    public void retirerUnCandidature(Long idCandidat, Long idPoste){
        Personne candidat = personneFacade.find(idCandidat);
        if(candidat == null){
            System.out.println(Constants.USER_NOT_EXIST);
        }else{
            FicheDePoste poste = posteFacade.find(idPoste);
            if(poste == null){
                System.out.println(Constants.POSTE_NOT_EXIST);
            }else{
                posteFacade.supprimerUnCandidatDuPoste(poste, candidat);
            }
        }
    }
    /**
     * permet de consulter les détails d'une poste
     * @param idPoste du poste à consulter
     * @return le poste avec l'id passé dans le paramètre
     */
    public FicheDePoste consulterUnPoste(Long idPoste){
        FicheDePoste poste = posteFacade.find(idPoste);
        if(poste == null){
            System.out.println(Constants.POSTE_NOT_EXIST);
            return null;
        }else{
            return poste;
        }
    }
    /**
     * Retourner la liste des postes disponibles
     * @return liste des postes ouverts
     */
    public List<FicheDePoste> getListPostes(){
        return posteFacade.findAll();
    }
    /**
     * Retourner une liste d'équipe qui a fait la demande de compétence
     * @param idCompetence l'id de la compétence démandé
     * @return la liste des équipes qui ont fait la demande
     */
    public List<Equipe> getEquipesDemandeurDeCompetence(Long idCompetence){
        Competence c = competenceFacade.find(idCompetence);
        if(c != null){
            return posteFacade.getEquipesDemandeursDeCompetence(c);
        }
        System.out.println(Constants.COMPETENCE_NOT_EXIST);
        return null;
    }
    
    public Equipe getEquipeDemandeurDuPoste(Long idPoste){
        FicheDePoste poste = posteFacade.find(idPoste);
        if(poste != null){
            return posteFacade.getEquipeDuDemandeurDePoste(poste);
        }
        System.out.println(Constants.POSTE_NOT_EXIST);
        return null;
    }
  
    /**
     * permet de retourner une liste de toutes les compétences
     * demandées
     * @return liste des compétences
     */
    public List<Competence> getListeCompetencesDemandees(){
        List<FicheDePoste> postes = posteFacade.findAll();
        List<Competence> competencesDemandees = new ArrayList<>();
        
        for(FicheDePoste poste : postes){
            List<Competence> lc = posteFacade.getListeCompetenceRecherchees(poste);
            for(Competence c : lc){
                if(!competencesDemandees.contains(c)){
                    competencesDemandees.add(c);
                }
            }
        }
        
        return competencesDemandees;
    }
    
    /**
     * fonction permettre à un codir de valider la création d'une poste
     * @param idPersonne l'id de la personne qui va valider (doit être un codir)
     * @param idPoste l'id de la poste à valider
     * @param presentationEntreprise la présentation de l'entreprise à ajouter lors de la validation du poste
     * @param presentationPoste la présentation du poste à ajouter lors de la validation du poste
     */
    public void validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste){
        Personne p = personneFacade.find(idPersonne);
        if(p != null){
            if(p.isIsCodir()){
                FicheDePoste poste = posteFacade.find(idPoste);
                if(poste == null){
                    System.out.println(Constants.POSTE_NOT_EXIST);
                    throw new IllegalArgumentException(Constants.POSTE_NOT_EXIST);
                }else{
                    if(posteFacade.getStatutDePoste(poste).equals(StatutDePoste.EN_ATTENTE)){
                        posteFacade.setStatut(poste, StatutDePoste.OUVERT);
                        posteFacade.ajouterDescriptionDePoste(poste, presentationEntreprise, presentationPoste);
                    }else{
                        String err = Constants.POSTE_STATUS_IS+" "+posteFacade.getStatutDePoste(poste).toString();
                        System.out.println(err);
                        throw new IllegalArgumentException(err);
                    }
                }
            }else{
                String err = Constants.ONLY_CODIR_CAN_VALIDATE;
                System.out.println(err);
                throw new IllegalArgumentException(err);
            }
        }else{
            System.out.println(Constants.USER_NOT_EXIST);
            throw new IllegalArgumentException(Constants.USER_NOT_EXIST);
        }
    }
    /**
     * Valider le recrutement d'un candidat
     * @param idPersonne le candidat à valider
     * @param idPoste le fiche de poste en question
     */
    public void feuVertCandidat(Long idPersonne, Long idPoste){
        Personne personne = personneFacade.find(idPersonne);
        if(personne != null){
            FicheDePoste poste = posteFacade.find(idPoste);
            if(poste != null){
                if(posteFacade.getStatutDePoste(poste).equals(StatutDePoste.OUVERT)){
                    personneFacade.isRecruited(personne);
                    posteFacade.setStatut(poste, StatutDePoste.ARCHIVEE);
                }else{
                    String err = Constants.POSTE_STATUS_IS+" "+posteFacade.getStatutDePoste(poste).toString();
                    System.out.println(err);
                    throw new IllegalArgumentException(err);
                }
                
            }else{
                System.out.println(Constants.POSTE_NOT_EXIST);
                throw new IllegalArgumentException(Constants.POSTE_NOT_EXIST);
            }
        }else{
            System.out.println(Constants.USER_NOT_EXIST);
            throw new IllegalArgumentException(Constants.USER_NOT_EXIST);
        }
    }
    /**
     * Refuser le candidature d'un candidat
     * @param idPersonne le candidat à refuser
     * @param idPoste le fiche de poste en question
     */
    public void feuRougeCandidat(Long idPersonne, Long idPoste){
        Personne personne = personneFacade.find(idPersonne);
        if(personne != null){
            FicheDePoste poste = posteFacade.find(idPoste);
            if(poste != null){
                if(posteFacade.getStatutDePoste(poste).equals(StatutDePoste.OUVERT)){
                    posteFacade.supprimerUnCandidatDuPoste(poste, personne);
                }else{
                    String err = Constants.POSTE_STATUS_IS+" "+posteFacade.getStatutDePoste(poste).toString();
                    System.out.println(err);
                    throw new IllegalArgumentException(err);
                }
            }else{
                System.out.println(Constants.POSTE_NOT_EXIST);
                throw new IllegalArgumentException(Constants.POSTE_NOT_EXIST);
            }
        }else{
            System.out.println(Constants.USER_NOT_EXIST);
            throw new IllegalArgumentException(Constants.USER_NOT_EXIST);
        }
    }
}
