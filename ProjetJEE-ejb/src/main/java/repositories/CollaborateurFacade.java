/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Collaborateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author igouane
 */
@Stateless
public class CollaborateurFacade extends AbstractFacade<Collaborateur> implements CollaborateurFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CollaborateurFacade() {
        super(Collaborateur.class);
    }
    
    public Collaborateur findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Collaborateur> cq = cb.createQuery(Collaborateur.class);
        Root<Collaborateur> root = cq.from(Collaborateur.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), prenom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public Collaborateur creerCollaborateurSiInexistant(String prenom, String nom) {
        try{
            return this.findByPrenomAndNom(prenom, nom);
        }catch(NoResultException noRes){
            Collaborateur nouveauCollaborateur = new Collaborateur(prenom, nom);
            this.create(nouveauCollaborateur);
            return nouveauCollaborateur;
        }
    }
    
}
