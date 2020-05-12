/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.EquipeFacadeLocal;

/**
 *
 * @author igouane
 */
@Stateless
public class GestionManager implements GestionManagerLocal {
    
    @EJB
    private EquipeFacadeLocal equipeFacade;

    public void listerCompetencesDeEquipe(){
        //equipeFacade.listeCompetences();
    }
}
