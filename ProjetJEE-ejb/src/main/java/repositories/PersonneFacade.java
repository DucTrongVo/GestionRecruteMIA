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

    @Override
    public Personne findByNomAndPrenom(String nom, String prenom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
        Root<Personne> root = cq.from(Personne.class);
        cq.where(
            cb.and(
                cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
            )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Personne creerPersonneSiInexistant(String nom, String prenom, ArrayList<Competence> listeCompetences) {
        try{
            return this.findByNomAndPrenom(nom, prenom);
        }catch(NoResultException noRes){
            Personne nouveauCollaborateur = new Personne(nom, prenom, listeCompetences);
            this.create(nouveauCollaborateur);
            System.out.println(noRes.toString());
            System.out.println(Constants.CREATE_SUCCES);
            return nouveauCollaborateur;
        }
    }    
    
    @Override
    public Personne creerCandidatSiInexistant(String nom, String prenom) {
        try{
            return this.findByNomAndPrenom(nom, prenom);
        }catch(NoResultException noRes){
            Personne newCandidat = new Personne(nom, prenom, null);
            this.create(newCandidat);
            System.out.println(noRes.toString());
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
    
    @Override
    public void setEquipe(Personne personne, Equipe equipe){
        personne.setEquipe(equipe);
    }
}
