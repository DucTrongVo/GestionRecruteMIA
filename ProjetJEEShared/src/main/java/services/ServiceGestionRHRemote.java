/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import fr.miage.toulouse.projetjee.projetjeeshared.CompetenceShared;
import fr.miage.toulouse.projetjee.projetjeeshared.EquipeShared;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author trongvo
 */
@Remote
public interface ServiceGestionRHRemote {
    public List<CompetenceShared> getListeCompetencesDemandees();
    public List<CompetenceShared> getListeCompetencesDeCollaborateur(PersonneShared collab);
    public FicheDePosteShared creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe);
    public List<FicheDePosteShared> getAllOpenedPoste();
    public List<FicheDePosteShared> getAllWaitingPoste();
    public FicheDePosteShared consulterUnPoste(Long idPoste);
    public List<PersonneShared> getListCollaborateur();
    public void setCodir(PersonneShared personne);
    public PersonneShared creerCandidatSiInexistant(String prenom, String nom);
    public PersonneShared creerPersonneSiInexistant(String prenom, String nom, ArrayList<CompetenceShared> listeCompetences);
    public EquipeShared creerEquipe(String nomEquipe,String nomManager, String prenomManager);
    public CompetenceShared creerCompetence(String nom);
    public List<EquipeShared> getAllEquipes();
    public List<CompetenceShared> getAllCompetences();
    public void validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste);
}
