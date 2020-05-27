/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import entities.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zamourak
 */
@Stateless
public class FicheDePosteFacade extends AbstractFacade<FicheDePoste> implements FicheDePosteFacadeLocal {

    @EJB
    FicheDePosteFacadeLocal posteFacadeLocal;
    
    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FicheDePosteFacade() {
        super(FicheDePoste.class);
    }

    /**
     * Ajouter un candidature au liste des candidatures du poste
     * @param poste le poste en question
     * @param candidat le candidat qui postule
     */
    public void ajouterUnCandidatAuPoste(FicheDePoste poste, Personne candidat){
        if(poste.getListeCandidats().contains(candidat)){
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" a déja postulé au poste "+poste.getNom());
        }else{
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" ajouté avec succès au poste "+poste.getNom());
        }
    }
    
    /**
     * Supprimer un candidature au liste des candidatures du poste
     * @param poste le poste en question
     * @param personne le candidat qui doit être supprimer
     */
    public void supprimerUnCandidatDuPoste(FicheDePoste poste, Personne personne){
        if(!poste.getListeCandidats().contains(personne)){
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" n'existe pas dans le poste "+poste.getNom());
        }else{
            poste.supprimerUnCandidature(personne);
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" supprimé avec succès du poste "+poste.getNom());
        }
    }
    /**
     * Ajouter une compétence dans le liste des compétences demandés
     * @param poste le poste dans lequel il faut ajouter la compétence
     * @param competence la compétence à ajouter
     */
    public void ajouterCompetenceAuPoste(FicheDePoste poste, Competence competence){
        if(poste.getListeCompetenceRecherchees().contains(poste)){
            System.out.println("La compétence "+competence.getNom()+" existe déja dans la demande du poste "+poste.getNom());
        }else{
            poste.ajouterCompetenceAuPoste(competence);
            System.out.println("La compétence "+competence.getNom()+" ajouté avec succès dans la demande du poste "+poste.getNom());
        }
    }
    
    /**
     * Supprimer une compétence dans le liste des compétences demandés
     * @param poste le poste dans lequel il faut suprrimer la compétence
     * @param candidat la compétence à supprimer
     */
    public void supprimerCompetenceAuPoste(FicheDePoste poste, Competence candidat){
        if(!poste.getListeCompetenceRecherchees().contains(poste)){
            System.out.println("La compétence "+candidat.getNom()+" n'existe pas dans la demande du poste "+poste.getNom());
        }else{
            poste.supprimerCompetenceAuPoste(candidat);
            System.out.println("La compétence "+candidat.getNom()+" supprimé avec succès de la demande du poste "+poste.getNom());
        }
    }
    
    
    public void creerDemandeDeCompetence(Equipe equipe, ArrayList listeCompetences, String nom){
        FicheDePoste ficheDePoste = new FicheDePoste(nom, String statut, String presentationEntreprise, String presentationPoste, 
                        List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse));
        
    }
}

