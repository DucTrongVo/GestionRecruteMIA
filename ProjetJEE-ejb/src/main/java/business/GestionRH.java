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
import javax.persistence.NoResultException;
import repositories.CompetenceFacadeLocal;
import repositories.EquipeFacadeLocal;
import repositories.FicheDePosteFacadeLocal;
import repositories.PersonneFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
public class GestionRH implements GestionRHLocal {
    
    @EJB
    private FicheDePosteFacadeLocal posteFacade;
    
    @EJB
    private CompetenceFacadeLocal competenceFacade;
    
    @EJB
    private PersonneFacadeLocal personneFacade;
    
    @EJB
    private EquipeFacadeLocal equipeFacade;
    
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
                posteFacade.ajouterUneCandidatureAuPoste(poste, candidat);
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
    @Override
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
    public List<FicheDePoste> getPostesDispo(){
        return posteFacade.findAll();
    }
    /**
     * Retourner une liste des équipes qui ont fait la demande de compétence
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
    @Override
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
     * retourner la liste des compétences d'un collaborateur
     * @param collab le collaborateur
     * @return liste des compétences du collaborateur en paramètre
     */
    @Override
    public List<Competence> getListeCompetencesDeCollaborateur(Personne collab){
        try{
            Personne personne = personneFacade.findByNomAndPrenom(collab.getNom(), collab.getPrenom());
            return personne.getListeCompetences();
        }catch(Exception e) {
            System.out.println(e.toString());
            return null;
        }
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
                    this.recruterCollaborateurDansEquipe(personne, poste);
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
    
    @Override
    public Personne creerCandidatSiInexistant(String nom, String prenom){
        return personneFacade.creerCandidatSiInexistant(prenom, nom);
    }
    
    public void recruterCollaborateurDansEquipe(Personne personne, FicheDePoste poste){
//        Personne personne = personneFacade.find(idPersonne);
//        FicheDePoste poste = posteFacade.find(idPoste);
        Equipe equipeDemandeuse = poste.getEquipeDemandeuse();
        personne.setEquipe(equipeDemandeuse);
    }
    
    @Override
    public FicheDePoste creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe){
        Equipe equipe = equipeFacade.findByNom(nomEquipe);
        ArrayList<Competence> listCompetences = new ArrayList<>();
        for (String nomC : nomCompetences) {
            listCompetences.add(competenceFacade.findByNomCompetence(nomC));
        }
        return(posteFacade.creerUneFicheDePoste(nomFicheDePoste, listCompetences, equipe));
    }
    /**
     * Retourner toutes les fiches de poste 
     * @return liste de poste valité (ouvert)
     */
    @Override
    public List<FicheDePoste> getAllOpenedPoste() {
        List<FicheDePoste> allPostes = posteFacade.findAll();
        List<FicheDePoste> allOpenedPosts = new ArrayList<>();
        for(FicheDePoste poste : allPostes){
            if(poste.getStatut() == StatutDePoste.OUVERT){
                allOpenedPosts.add(poste);
            }
         }
        return allOpenedPosts;
    }

    @Override
    public List<FicheDePoste> getAllWaitingPoste() {
        List<FicheDePoste> allPostes = posteFacade.findAll();
        List<FicheDePoste> allWaitingPosts = new ArrayList<>();
        for(FicheDePoste poste : allPostes){
            if(poste.getStatut() == StatutDePoste.EN_ATTENTE){
                allWaitingPosts.add(poste);
            }
         }
        return allWaitingPosts;
    }

    @Override
    public List<Personne> getListCollaborateur() {
        List<Personne> allPersonne = personneFacade.findAll();
        List<Personne> allCollab = new ArrayList<>();
        for(Personne personne : allPersonne){
            if(personne.getEquipe() != null){
                allCollab.add(personne);
            }
        }
        return allCollab;
    }

    @Override
    public Personne creerPersonneSiInexistant(String prenom, String nom, ArrayList<Competence> listeCompetences) {
        return personneFacade.creerPersonneSiInexistant(prenom, nom, listeCompetences);
    }
    
    @Override
    public Equipe creerEquipe(String nomEquipe, String nomManager, String prenomManager){
        Personne manager = personneFacade.creerPersonneSiInexistant(prenomManager, nomManager, new ArrayList<Competence>());
        System.out.println("GESTION RH : MANAGER IS "+manager.getNom());
        Equipe equipe = equipeFacade.creerEquipe(nomEquipe, manager);
        this.setEquipe(manager.getId(), equipe.getNom());
        return equipe;
    }
    
    @Override
    public void setEquipe(Long idPersonne, String nomEquipe){
        Personne personne = personneFacade.find(idPersonne);
            if(personne != null){
                Equipe equipe = equipeFacade.findByNom(nomEquipe);
                if(equipe != null){
                    personneFacade.setEquipe(personne, equipe);
                }
            }else{
                System.out.println("personne does not existed!");
            }
    }
    @Override
    public Competence creerCompetence(String nom){
        competenceFacade.creerCompetence(nom);
        return competenceFacade.findByNomCompetence(nom);
    }
}
