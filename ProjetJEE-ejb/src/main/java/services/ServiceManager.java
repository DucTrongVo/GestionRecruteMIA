/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionManagerLocal;
import entities.Competence;
import entities.FicheDePoste;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author trongvo
 */
@Stateless
public class ServiceManager implements ServiceManagerLocal {

    @EJB
    private GestionManagerLocal gestionManager;
    
    @Override
    public String getAllCompetencesEquipe(Long idEquipe){
        String allNomsCompetence ="";
        ArrayList<Competence> listCompetences = gestionManager.listerCompetencesDeEquipe(idEquipe);
        for (int i=0;i<listCompetences.size();i++){
            allNomsCompetence = allNomsCompetence + listCompetences.get(i).getNom()+" / ";
        }
        return allNomsCompetence;
    }

 

    @Override
    public String creerFicheDePosteDeDemande(String nomFicheDePoste, String nomCompetences, String nomEquipe){
        ArrayList<String> comptences = new ArrayList<>();
        String[] lcs = nomCompetences.split(",");
        comptences.addAll(Arrays.asList(lcs));
        FicheDePoste ficheDePoste = gestionManager.creerFicheDePosteDeDemande(nomFicheDePoste, comptences, nomEquipe);
        if(ficheDePoste!=null){
            return ficheDePoste.getNom();
        }
        return "La fiche de poste n'a pas été crée.";
    }
}
