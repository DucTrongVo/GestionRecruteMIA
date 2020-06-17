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
import repositories.CompetenceFacadeLocal;
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
        Personne personne = personneFacade.findByNomAndPrenom(collab.getNom(), collab.getPrenom());
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
    private PersonneShared getPersonneSharedFromPersonne(Personne personne){
        List<Competence> listeCompetenceManager = personne.getListeCompetences();
        List<CompetenceShared> listeCompetenceShared = new ArrayList<>();
        for(int i=0;i<listeCompetenceManager.size();i++){
            listeCompetenceShared.add(new CompetenceShared(listeCompetenceManager.get(i).getNom()));
        }
        PersonneShared personneShared = new PersonneShared(personne.getId(),personne.getNom(), personne.getPrenom(), listeCompetenceShared);
        //personneShared.setEquipe(equipe);
        personneShared.setIsCodir(personne.isIsCodir());
        personneShared.setIsManager(personne.isIsManager());
        return personneShared;
    }
    private EquipeShared getEquipeSharedFromEquipe(Equipe equipe, PersonneShared managerSh){
        Personne manager = equipe.getManager();
        EquipeShared equipeShared = new EquipeShared(managerSh, equipe.getNom());
        managerSh.setEquipe(equipeShared);
        List<PersonneShared> listCollaborateursShared = new ArrayList<>();
        List<Personne> listCollaborateurs = equipe.getCollaborateurs();
        for(int i=0;i<listCollaborateurs.size();i++){
            listCollaborateursShared.add(getPersonneSharedFromPersonne(listCollaborateurs.get(i)));
        }
        equipeShared.setCollaborateurs(listCollaborateursShared);
        return equipeShared;
    }

    @Override
    public FicheDePosteShared creerFicheDePosteDeDemande(String nomFicheDePoste, ArrayList<String> nomCompetences, String nomEquipe) {
        try{
            FicheDePoste poste = gestionRH.creerFicheDePosteDeDemande(nomFicheDePoste, nomCompetences, nomEquipe);
            PersonneShared managerShared = getPersonneSharedFromPersonne(poste.getEquipeDemandeuse().getManager());
            EquipeShared equipeS = getEquipeSharedFromEquipe(poste.getEquipeDemandeuse(), managerShared);
            List<Competence> listeCompetence = poste.getListeCompetenceRecherchees();
                List<CompetenceShared> listeCompetenceShared = new ArrayList<>();
                for(int i=0;i<listeCompetence.size();i++){
                    CompetenceShared competenceShared = new CompetenceShared(listeCompetence.get(i).getNom());
                    listeCompetenceShared.add(competenceShared);
                }
            return new FicheDePosteShared(poste.getId(), equipeS.getNom(), Constants.PRESENTATION_ENTREPRISE, nomFicheDePoste, listeCompetenceShared, equipeS);
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
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
            allOpendPostsShared.add(new FicheDePosteShared(poste.getId(), poste.getNom(), Constants.PRESENTATION_ENTREPRISE, poste.getPresentationPoste(), 
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
            allWaitingPostsShared.add(new FicheDePosteShared(poste.getId(), poste.getNom(), Constants.PRESENTATION_ENTREPRISE, poste.getPresentationPoste(), 
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
            FicheDePosteShared posteShared = new FicheDePosteShared(posteAConsulter.getId(), posteAConsulter.getNom(), posteAConsulter.getPresentationEntreprise(), posteAConsulter.getPresentationPoste(), 
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
                PersonneShared personneShared = new PersonneShared(personne.getId(), personne.getNom(), personne.getPrenom(), lcs);
                if(personne.isIsCodir()){
                    personneShared.setIsCodir(true);
                }
                if(personne.isIsManager()){
                    personneShared.setIsManager(true);
                }
                allCollab.add(personneShared);
            }
            return allCollab;
        }else{
            System.out.println("No collaborateur found");
            return null;
        }
    }
    
    @Override
    public List<PersonneShared> getListCandidat(){
        List<Personne> allCandidat = gestionRH.getListCandidat();
        if(allCandidat != null){
            List<PersonneShared> allCandidatShared = new ArrayList<>();
            for(Personne personne : allCandidat){
                allCandidatShared.add(this.getPersonneSharedFromPersonne(personne));
            }
            return allCandidatShared;
        }else{
            System.out.println("No Candidat found");
            return null;
        }
    }

    @Override
    public PersonneShared creerCandidatSiInexistant(String nom, String prenom) {
        Personne personne = gestionRH.creerCandidatSiInexistant(nom, prenom);
        return new PersonneShared(personne.getId(), personne.getNom(), personne.getPrenom(), null);
    }

    @Override
    public PersonneShared creerPersonneSiInexistant(String nom, String prenom, ArrayList<CompetenceShared> listeCompetences) {
        List<Competence> listC = new ArrayList<>();
        if(listeCompetences != null){
            for(CompetenceShared cmp : listeCompetences){
                Competence competence = competenceFacade.findByNomCompetence(cmp.getNom());
                if(competence != null){listC.add(competence);}
                else{listC.add(new Competence(cmp.getNom()));}
            }
        }      
        Personne personne = gestionRH.creerPersonneSiInexistant(nom, prenom, (ArrayList<Competence>) listC);
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
        managerShared.setIsManager(true);
        return new EquipeShared(managerShared, nomEquipe);
    }

    @Override
    public CompetenceShared creerCompetence(String nom) {
        gestionRH.creerCompetence(nom);
        return new CompetenceShared(nom);
    }

    @Override
    public List<EquipeShared> getAllEquipes() {
        List<Equipe> equipes = gestionRH.getAllEquipes();
        List<EquipeShared> equipesShared = new ArrayList<>();
        for(Equipe equipe : equipes){
            PersonneShared personneS = new PersonneShared(equipe.getManager().getId(), equipe.getManager().getNom(),
                                                        equipe.getManager().getPrenom(), null);
            equipesShared.add(new EquipeShared(personneS, equipe.getNom()));
        }
        return equipesShared;
    }

    @Override
    public List<CompetenceShared> getAllCompetences() {
        List<Competence> competences = gestionRH.getAllCompetences();
        List<CompetenceShared> competencesShared = new ArrayList<>();
        for(Competence competence : competences){
            competencesShared.add(new CompetenceShared(competence.getNom()));
        }
        return competencesShared;
    }

    @Override
    public String validerLaCreationUnPoste(Long idPersonne, Long idPoste, String presentationEntreprise, String presentationPoste) {
        gestionRH.validerLaCreationUnPoste(idPersonne, idPoste, presentationEntreprise, presentationPoste);
        return Constants.POST_VALIDATION_SUCCESS;
    }

    @Override
    public String setCodir(PersonneShared personne) {
        gestionRH.setCodir(personne.getId());
        personne.setIsCodir(true);
        return personne.getNom() + " "+personne.getPrenom()+" est désormais CODIR";
    }
    
    @Override
    public String setEquipe(Long idPersonne, String nomEquipe){
        gestionRH.setEquipe(idPersonne, nomEquipe);
        return "Ajoute succes dans l\'équipe "+nomEquipe;
    }
      
}
