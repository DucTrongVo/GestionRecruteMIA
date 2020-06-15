/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import entities.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trongvo
 */
@Local
public interface GestionRHLocal {
    
    public FicheDePoste creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe);
    public List<Competence> getListeCompetencesDemandees();
    public FicheDePoste consulterUnPoste(Long idPoste);
    public List<Competence> getListeCompetencesDeCollaborateur(Personne collab);
    public Personne creerCandidatSiInexistant(String nom, String prenom);
    public Personne creerPersonneSiInexistant(String prenom, String nom, ArrayList<Competence> listeCompetences);
    public List<FicheDePoste> getAllOpenedPoste();
    public List<FicheDePoste> getAllWaitingPoste();
    public List<FicheDePoste> getAllClosedPoste();
    public List<Personne> getListCollaborateur();
    public Equipe creerEquipe(String nomEquipe,String nomManager, String prenomManager);
    public void setEquipe(Long idPersonne, String nomEquipe);
    public Competence creerCompetence(String nom);
    public List<Equipe> getAllEquipes();
    public List<Competence> getAllCompetences();
    public void validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste);
    public void setCodir(Long idPersonne);
}
