/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.Personne;
import java.util.ArrayList;
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
public class EquipeFacade extends AbstractFacade<Equipe> implements EquipeFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipeFacade() {
        super(Equipe.class);
    }
    
    public ArrayList<Competence> findAllCompetences(){
        ArrayList<Competence> listeCompetences = new ArrayList<>();
        
        return listeCompetences;
    }    
    @Override
    public Equipe creerEquipe(String nomEquipe,Personne manager) {
        Equipe equipe = new Equipe(nomEquipe,manager);
        this.create(equipe);
        return equipe;
    }
    
    @Override
    public Equipe findByManager(Personne manager) {
     CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
     CriteriaQuery<Equipe> cq = cb.createQuery(Equipe.class);
     Root<Equipe> root = cq.from(Equipe.class);
     cq.where(cb.equal(root.get("manager"), manager));
     return getEntityManager().createQuery(cq).getSingleResult();
    }
    
    @Override
    public Equipe findByNom(String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Equipe> cq = cb.createQuery(Equipe.class);
        Root<Equipe> root = cq.from(Equipe.class);
        cq.where(cb.equal(root.get("nom"), nom));
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
