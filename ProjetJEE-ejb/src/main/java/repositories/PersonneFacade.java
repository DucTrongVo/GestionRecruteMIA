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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import services.ServiceCandidat;

/**
 *
 * @author zamourak
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> implements PersonneFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }

    ServiceCandidat serviceCandidat;
    

    public void postuler(Personne personne, FicheDePoste poste){
        if(personne.getListePostulation().contains(poste)){
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" a déja postuler au poste "+poste.getNom());
        }else{
            personne.postuler(poste);
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" postulé avec succès au poste "+poste.getNom());
        }
    }
    
    public void retirerLeCandidature(Personne personne, FicheDePoste poste){
        if(!personne.getListePostulation().contains(poste)){
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" n'a pas encore postulé au poste "+poste.getNom());
        }else{
            personne.retirerLeCandidature(poste);
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" a retiré avec succès son candidature au poste "+poste.getNom());
        }
    }


    public Personne findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
        Root<Personne> root = cq.from(Personne.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), prenom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public Personne creerPersonneSiInexistant(String prenom, String nom, boolean isCodir, Equipe equipe, ArrayList<Competence> listeCompetences, List<FicheDePoste> listePostulation) {
        try{
            return this.findByPrenomAndNom(prenom, nom);
        }catch(NoResultException noRes){
            Personne nouveauCollaborateur = new Personne(prenom, nom, isCodir, equipe, listeCompetences, listePostulation);
            this.create(nouveauCollaborateur);
            return nouveauCollaborateur;
        }
    }    
    
}
