/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author zamourak
 */
@Local
public interface GestionCEOLocal {
    public ArrayList<Competence> accederListeCompetence();
    public ArrayList<Integer> accederListeComptesFichesDePoste();
    public ArrayList<Integer> accederNombreCandidatures();    
}
