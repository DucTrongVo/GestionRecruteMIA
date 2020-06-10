/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.Personne;
import fr.miage.toulouse.projetjee.projetjeeshared.Constants;
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
    
    @Override
    public List<Competence> getListCompetences(Personne p){
        return p.getListeCompetences();
    }

    public Personne findByNomAndPrenom(String prenom, String nom) {
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

    public Personne creerPersonneSiInexistant(String prenom, String nom, ArrayList<Competence> listeCompetences) {
        try{
            return this.findByNomAndPrenom(prenom, nom);
        }catch(NoResultException noRes){
            Personne nouveauCollaborateur = new Personne(prenom, nom, listeCompetences);
            this.create(nouveauCollaborateur);
            System.out.println(Constants.CREATE_SUCCES);
            return nouveauCollaborateur;
        }
    }    
    
    public Personne creerCandidatSiInexistant(String prenom, String nom) {
        try{
            return this.findByNomAndPrenom(prenom, nom);
        }catch(NoResultException noRes){
            Personne newCandidat = new Personne(prenom, nom, null);
            this.create(newCandidat);
            System.out.println(Constants.CREATE_SUCCES);
            return newCandidat;
        }
    }    
    
    @Override
    public List<Personne> findByEquipe(Equipe equipe) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
        Root<Personne> root = cq.from(Personne.class);
        cq.where(cb.equal(root.get("equipe"), equipe));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public void ajouterUneCompetence(Personne personne, Competence competence){
        personne.getListeCompetences().add(competence);
    }
    
}
