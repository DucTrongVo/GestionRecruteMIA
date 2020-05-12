/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author igouane
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
    
    public ArrayList<Competence> listerLesCompetences(){
        ArrayList<Competence> listeCompetences = new ArrayList<Competence>();
        
        return listeCompetences;
    }
    
}
