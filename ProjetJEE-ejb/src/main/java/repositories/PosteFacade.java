/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Candidat;
import entities.Competence;
import entities.Poste;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author trongvo
 */
@Stateless
public class PosteFacade extends AbstractFacade<Poste> implements PosteFacadeLocal {

    @EJB
    PosteFacadeLocal posteFacadeLocal;
    
    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PosteFacade() {
        super(Poste.class);
    }
    /**
     * Ajouter un candidature au liste des candidatures du poste
     * @param p le poste en question
     * @param c le candidat qui postule
     */
    public void ajouterUnCandidatAuPoste(Poste p, Candidat c){
        if(p.getListCandidats().contains(c)){
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" a déja postulé au poste "+p.getNomPoste());
        }else{
            p.ajouterUnCandidature(c);
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" ajouté avec succès au poste "+p.getNomPoste());
        }
    }
    
    /**
     * Supprimer un candidature au liste des candidatures du poste
     * @param p le poste en question
     * @param c le candidat qui doit être supprimer
     */
    public void supprimerUnCandidatDuPoste(Poste p, Candidat c){
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
    public void ajouterCompetenceAuPoste(Poste p, Competence c){
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
    public void supprimerCompetenceAuPoste(Poste p, Competence c){
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
    public void supprimerPoste(Poste poste) {
        posteFacadeLocal.remove(poste);
    }
}
