/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionRHLocal;
import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import entities.Personne;
import fr.miage.toulouse.projetjee.projetjeeshared.CompetenceShared;
import fr.miage.toulouse.projetjee.projetjeeshared.Constants;
import fr.miage.toulouse.projetjee.projetjeeshared.EquipeShared;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import repositories.CompetenceFacadeLocal;
import repositories.EquipeFacadeLocal;
import repositories.PersonneFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
public class ServiceGestionRH implements ServiceGestionRHRemote {
    @EJB
    private GestionRHLocal gestionRH;
    @EJB
    private PersonneFacadeLocal personneFacade;
    @EJB
    private CompetenceFacadeLocal competenceFacade;
    @EJB
    private EquipeFacadeLocal equipeFacade;
    
    @Override
    public List<CompetenceShared> getListeCompetencesDemandees(){
        List<Competence> listCompetencesDemandees = gestionRH.getListeCompetencesDemandees();
        if(listCompetencesDemandees != null){
            List<CompetenceShared> listFinal = new ArrayList<>();
            for(Competence competence : listCompetencesDemandees){
                listFinal.add(new CompetenceShared(competence.getNom()));
            }
            return listFinal;
        }else{
            System.out.println("Aucune Competences Demandées");
            return null;
        } 
    }

    @Override
    public List<CompetenceShared> getListeCompetencesDeCollaborateur(PersonneShared collab) {
        Personne personne = personneFacade.findByNomAndPrenom(collab.getPrenom(), collab.getNom());
        if(personne != null){
            List<Competence> listCompetenceCollab = gestionRH.getListeCompetencesDeCollaborateur(personne);
            List<CompetenceShared> listFinal = new ArrayList<>();
            for(Competence competence : listCompetenceCollab){
                listFinal.add(new CompetenceShared(competence.getNom()));
            }
            return listFinal;
        }else{
            System.out.println("Personne inexistant");
            return null;
        }
    }

    @Override
    public void creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe) {
        try{
            gestionRH.creerFicheDePosteDeDemande(nomFicheDePoste, nomCompetences, nomEquipe);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }  

    @Override
    public List<FicheDePosteShared> getAllOpenedPoste() {
        List<FicheDePoste> allOpenPosts = gestionRH.getAllOpenedPoste();
        if(allOpenPosts != null){
            List<FicheDePosteShared> allOpendPostsShared = new ArrayList<>();
            for(FicheDePoste poste : allOpenPosts){
            List<CompetenceShared> lcs = new ArrayList<>();
            for(Competence competence : poste.getListeCompetenceRecherchees()){
                lcs.add(new CompetenceShared(competence.getNom()));
            }
            PersonneShared manager = new PersonneShared(poste.getEquipeDemandeuse().getManager().getId(), 
                    poste.getEquipeDemandeuse().getManager().getNom(), poste.getEquipeDemandeuse().getManager().getPrenom(), null);
            EquipeShared equipeDemandeuse = new EquipeShared(manager, poste.getEquipeDemandeuse().getNom());
            // ajouter poste dans la liste
            allOpendPostsShared.add(new FicheDePosteShared(poste.getNom(), Constants.PRESENTATION_ENTREPRISE, poste.getPresentationPoste(), 
                    lcs, equipeDemandeuse));
            }
            return allOpendPostsShared;
        }else{
            System.out.println("No opend post");
            return null;
        }
    }

    @Override
    public List<FicheDePosteShared> getAllWaitingPoste() {
        List<FicheDePoste> allWaitingPosts = gestionRH.getAllWaitingPoste();
        if(allWaitingPosts != null){
            List<FicheDePosteShared> allWaitingPostsShared = new ArrayList<>();
            for(FicheDePoste poste : allWaitingPosts){
            List<CompetenceShared> lcs = new ArrayList<>();
            for(Competence competence : poste.getListeCompetenceRecherchees()){
                lcs.add(new CompetenceShared(competence.getNom()));
            }
            PersonneShared manager = new PersonneShared(poste.getEquipeDemandeuse().getManager().getId(), 
                    poste.getEquipeDemandeuse().getManager().getNom(), poste.getEquipeDemandeuse().getManager().getPrenom(), null);
            EquipeShared equipeDemandeuse = new EquipeShared(manager, poste.getEquipeDemandeuse().getNom());
            // ajouter poste dans la liste
            allWaitingPostsShared.add(new FicheDePosteShared(poste.getNom(), Constants.PRESENTATION_ENTREPRISE, poste.getPresentationPoste(), 
                    lcs, equipeDemandeuse));
            }
            return allWaitingPostsShared;
        }else{
            System.out.println("No waiting posts");
            return null;
        }
    }

    @Override
    public FicheDePosteShared consulterUnPoste(Long idPoste) {
        FicheDePoste posteAConsulter = gestionRH.consulterUnPoste(idPoste);
        if(posteAConsulter != null){
            List<CompetenceShared> lcs = new ArrayList<>();
            for(Competence competence : posteAConsulter.getListeCompetenceRecherchees()){
                lcs.add(new CompetenceShared(competence.getNom()));
            }
            PersonneShared manager = new PersonneShared(posteAConsulter.getEquipeDemandeuse().getManager().getId(), 
                    posteAConsulter.getEquipeDemandeuse().getManager().getNom(), posteAConsulter.getEquipeDemandeuse().getManager().getPrenom(), null);
            EquipeShared equipeDemandeuse = new EquipeShared(manager, posteAConsulter.getEquipeDemandeuse().getNom());
            FicheDePosteShared posteShared = new FicheDePosteShared(posteAConsulter.getNom(), posteAConsulter.getPresentationEntreprise(), posteAConsulter.getPresentationPoste(), 
                    lcs, equipeDemandeuse);
            return posteShared;
        }else{
            System.out.println("Poste not found");
            return null;
        }
    }

    @Override
    public List<PersonneShared> getListCollaborateur() {
        List<Personne> allPersonne = gestionRH.getListCollaborateur();
        if(allPersonne != null){
            List<PersonneShared> allCollab = new ArrayList<>();
            for(Personne personne : allPersonne){
                List<CompetenceShared> lcs = new ArrayList<>();
                for(Competence competence : personne.getListeCompetences()){
                    lcs.add(new CompetenceShared(competence.getNom()));
                }
                allCollab.add(new PersonneShared(personne.getId(), personne.getNom(), personne.getPrenom(), lcs));
            }
            return allCollab;
        }else{
            System.out.println("No collaborateur found");
            return null;
        }
    }

    @Override
    public PersonneShared creerCandidatSiInexistant(String prenom, String nom) {
        Personne personne = gestionRH.creerCandidatSiInexistant(nom, prenom);
        return new PersonneShared(personne.getId(), personne.getNom(), personne.getPrenom(), null);
    }

    @Override
    public PersonneShared creerPersonneSiInexistant(String prenom, String nom, ArrayList<CompetenceShared> listeCompetences) {
        List<Competence> listC = new ArrayList<>();
        if(listeCompetences != null){
            System.out.println("liste compétence not null");
            for(CompetenceShared cmp : listeCompetences){
                Competence competence = competenceFacade.findByNomCompetence(cmp.getNom());
                if(competence != null){listC.add(competence);}
                else{listC.add(new Competence(cmp.getNom()));}
            }
        }      
        Personne personne = gestionRH.creerPersonneSiInexistant(prenom, nom, (ArrayList<Competence>) listC);
        return new PersonneShared(personne.getId(), nom, prenom, listeCompetences);
    }

    @Override
    public EquipeShared creerEquipe(String nomEquipe, String nomManager, String prenomManager) {
        Equipe equipe = gestionRH.creerEquipe(nomEquipe, nomManager, prenomManager);
        Personne manager = equipe.getManager();
        List<CompetenceShared> listCompetencesShared = new ArrayList<>();
        if(manager.getListeCompetences() != null){
            for( Competence competence : manager.getListeCompetences()){
                listCompetencesShared.add(new CompetenceShared(competence.getNom()));
            }
        }
        
        PersonneShared managerShared = new PersonneShared(manager.getId(), manager.getNom(), manager.getPrenom(), listCompetencesShared);
        EquipeShared equipeShared = new EquipeShared(managerShared, nomEquipe);
        managerShared.setEquipe(equipeShared);
        return new EquipeShared(managerShared, nomEquipe);
//        try{
//            Personne personne = personneFacade.findByNomAndPrenom(manager.getPrenom(), manager.getNom());
//            Equipe equipe = equipeFacade.creerEquipe(nomEquipe, personne);
//            return new EquipeShared(manager, nomEquipe);
//        }catch(NoResultException NoRes){
//            System.out.println("Manager not found");
//            return null;
//        }
    }

    @Override
    public CompetenceShared creerCompetence(String nom) {
        System.out.println("SERVICE GESTION RH : BEGIN CREATE COMPETENCE");
        Competence competence = gestionRH.creerCompetence(nom);
        System.out.println("SERVICE GESTION RH : COMPETENCE CREATE IN GESTION RH "+competence.getNom());
        return new CompetenceShared(nom);
    }
    
    
      
}
