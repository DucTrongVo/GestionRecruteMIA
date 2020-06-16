/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.PROJETJEE;

import fr.miage.toulouse.projetjee.projetjeeshared.CompetenceShared;
import fr.miage.toulouse.projetjee.projetjeeshared.EquipeShared;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import services.ServiceGestionRHRemote;

/**
 *
 * @author trongvo
 */
@WebService(serviceName = "WebServiceGestionRH")
@Stateless()
public class WebServiceGestionRH {

    @EJB
    private ServiceGestionRHRemote ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getListeCompetencesDemandees")
    public List<CompetenceShared> getListeCompetencesDemandees() {
        return ejbRef.getListeCompetencesDemandees();
    }

    @WebMethod(operationName = "getListeCompetencesDeCollaborateur")
    public List<CompetenceShared> getListeCompetencesDeCollaborateur(@WebParam(name = "collab") PersonneShared collab) {
        return ejbRef.getListeCompetencesDeCollaborateur(collab);
    }

    @WebMethod(operationName = "creerFicheDePosteDeDemande")
    public FicheDePosteShared creerFicheDePosteDeDemande(@WebParam(name = "nomFicheDePoste") String nomFicheDePoste, @WebParam(name = "nomCompetences") ArrayList<String> nomCompetences, @WebParam(name = "nomEquipe") String nomEquipe) {
        return ejbRef.creerFicheDePosteDeDemande(nomFicheDePoste, nomCompetences, nomEquipe);
    }

    @WebMethod(operationName = "getAllOpenedPoste")
    public List<FicheDePosteShared> getAllOpenedPoste() {
        return ejbRef.getAllOpenedPoste();
    }

    @WebMethod(operationName = "getAllWaitingPoste")
    public List<FicheDePosteShared> getAllWaitingPoste() {
        return ejbRef.getAllWaitingPoste();
    }

    @WebMethod(operationName = "consulterUnPoste")
    public FicheDePosteShared consulterUnPoste(@WebParam(name = "idPoste") Long idPoste) {
        return ejbRef.consulterUnPoste(idPoste);
    }

    @WebMethod(operationName = "getListCollaborateur")
    public List<PersonneShared> getListCollaborateur() {
        return ejbRef.getListCollaborateur();
    }

    @WebMethod(operationName = "setCodir")
    @Oneway
    public void setCodir(@WebParam(name = "personne") PersonneShared personne) {
        ejbRef.setCodir(personne);
    }

    @WebMethod(operationName = "creerCandidatSiInexistant")
    public PersonneShared creerCandidatSiInexistant(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return ejbRef.creerCandidatSiInexistant(nom, prenom);
    }

    @WebMethod(operationName = "creerPersonneSiInexistant")
    public PersonneShared creerPersonneSiInexistant(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "listeCompetences") ArrayList<CompetenceShared> listeCompetences) {
        return ejbRef.creerPersonneSiInexistant(nom, prenom, listeCompetences);
    }

    @WebMethod(operationName = "creerEquipe")
    public EquipeShared creerEquipe(@WebParam(name = "nomEquipe") String nomEquipe, @WebParam(name = "nomManager") String nomManager, @WebParam(name = "prenomManager") String prenomManager) {
        return ejbRef.creerEquipe(nomEquipe, nomManager, prenomManager);
    }

    @WebMethod(operationName = "creerCompetence")
    public CompetenceShared creerCompetence(@WebParam(name = "nom") String nom) {
        return ejbRef.creerCompetence(nom);
    }

    @WebMethod(operationName = "getAllEquipes")
    public List<EquipeShared> getAllEquipes() {
        return ejbRef.getAllEquipes();
    }

    @WebMethod(operationName = "getAllCompetences")
    public List<CompetenceShared> getAllCompetences() {
        return ejbRef.getAllCompetences();
    }
    
    @WebMethod(operationName = "setEquipe")
    @Oneway
    public void setEquipe(@WebParam(name = "idPersonne") Long idPersonne, @WebParam(name = "nomEquipe") String nomEquipe) {
        ejbRef.setEquipe(idPersonne, nomEquipe);
    }
    
    @WebMethod(operationName = "getListCandidat")
    @Oneway
    public void getListCandidat() {
        ejbRef.getListCandidat();
    }

    @WebMethod(operationName = "validerLaCreationUnPoste")
    @Oneway
    public void validerLaCreationUnPoste(@WebParam(name = "idPersonne") Long idPersonne, @WebParam(name = "idPoste") Long idPoste, @WebParam(name = "presentationEntreprise") String presentationEntreprise, @WebParam(name = "presentationPoste") String presentationPoste, @WebParam(name = "posteShared") FicheDePosteShared posteShared) {
        ejbRef.validerLaCreationUnPoste(idPersonne, idPoste, presentationEntreprise, presentationPoste, posteShared);
    }
    
}
