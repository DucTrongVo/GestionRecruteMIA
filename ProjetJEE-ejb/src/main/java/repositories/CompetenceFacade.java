/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Personne;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author zamourak
 */
@Stateless
public class CompetenceFacade extends AbstractFacade<Competence> implements CompetenceFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetenceFacade() {
        super(Competence.class);
    }
    
    public Competence findByCompetence(String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Competence> cq = cb.createQuery(Competence.class);
        Root<Competence> root = cq.from(Competence.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }    

    public void CreerCompetence(String nom){
        Competence competence = new Competence(nom);
        this.create(competence);
    }
    
    @Override
    public List<Competence> findByPersonne(Personne collaborateur) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Competence> cq = cb.createQuery(Competence.class);
        Root<Competence> root = cq.from(Competence.class);
        cq.where(cb.equal(root.get("collaborateur"), collaborateur));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
