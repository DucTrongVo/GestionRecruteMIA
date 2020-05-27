/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zamourak
 */
@Local
public interface PersonneFacadeLocal {

    void create(Personne personne);

    void edit(Personne personne);

    void remove(Personne personne);

    Personne find(Object id);

    List<Personne> findAll();

    List<Personne> findRange(int[] range);

    int count();
    
    List<Personne> findByEquipe(Equipe equipe);

    public List<Competence> getListCompetences(Personne personne);
    
}
