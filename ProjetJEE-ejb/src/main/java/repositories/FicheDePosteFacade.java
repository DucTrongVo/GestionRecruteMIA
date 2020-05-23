/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.FicheDePoste;
import entities.Personne;
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
     * @param p le poste en question
     * @param c le candidat qui postule
     */
    public void ajouterUnCandidatAuPoste(FicheDePoste p, Personne candidat){
        if(p.getListeCandidats().contains(candidat)){
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" a déja postulé au poste "+p.getNom());
        }else{
            p.ajouterUnCandidature(candidat);
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" ajouté avec succès au poste "+p.getNom());
        }
    }
    
    /**
     * Supprimer un candidature au liste des candidatures du poste
     * @param p le poste en question
     * @param c le candidat qui doit être supprimer
     */
    public void supprimerUnCandidatDuPoste(FicheDePoste p, Candidat c){
        if(p.getListCandidats().contains(c)){
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" n'existe pas dans le poste "+p.getNomPoste());
        }else{
            p.supprimerUnCandidature(c);
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" supprimé avec succès du poste "+p.getNomPoste());
        }
    }
    /**
     * Ajouter une compétence dans le liste des compétences demandés
     * @param p le poste dans lequel il faut ajouter la compétence
     * @param c la compétence à ajouter
     */
    public void ajouterCompetenceAuPoste(FicheDePoste p, Competence c){
        if(p.getListeCompetences().contains(p)){
            System.out.println("Le compétence "+c.getNom()+" existe déja dans la demande du poste "+p.getNomPoste());
        }else{
            p.ajouterCompetenceAuPoste(c);
            System.out.println("Le compétence "+c.getNom()+" ajouté avec succès dans la demande du poste "+p.getNomPoste());
        }
    }
    
    /**
     * Supprimer une compétence dans le liste des compétences demandés
     * @param p le poste dans lequel il faut suprrimer la compétence
     * @param c la compétence à supprimer
     */
    public void supprimerCompetenceAuPoste(FicheDePoste p, Competence c){
        if(p.getListeCompetences().contains(p)){
            System.out.println("Le compétence "+c.getNom()+" n'existe pas dans la demande du poste "+p.getNomPoste());
        }else{
            p.supprimerCompetenceAuPoste(c);
            System.out.println("Le compétence "+c.getNom()+" supprimé avec succès de la demande du poste "+p.getNomPoste());
        }
    }

    /**
     * Supprimer le poste de la BD
     * @param poste 
     */
    @Override
    public void supprimerPoste(FicheDePoste poste) {
        posteFacadeLocal.remove(poste);
    }
    
}

