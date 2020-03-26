/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Candidat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author igouane
 */
@Stateless
public class CandidatFacade extends AbstractFacade<Candidat> implements CandidatFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatFacade() {
        super(Candidat.class);
    }
    
}
