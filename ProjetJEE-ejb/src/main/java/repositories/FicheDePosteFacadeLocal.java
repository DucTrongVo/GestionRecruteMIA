/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zamourak
 */
@Local
public interface FicheDePosteFacadeLocal {

    void create(FicheDePoste ficheDePoste);

    void edit(FicheDePoste ficheDePoste);

    void remove(FicheDePoste ficheDePoste);

    FicheDePoste find(Object id);

    List<FicheDePoste> findAll();

    List<FicheDePoste> findRange(int[] range);

    int count();
    
    public void supprimerPoste(FicheDePoste poste);
    
    FicheDePoste creerUneFicheDePoste(String nom, List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse);  

    void ajouterDescriptionDePoste(FicheDePoste poste, String presentationEntreprise, String presentationPoste);
    
    List<FicheDePoste> findPostesDisponibles();
}
