/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Competence;
import entities.Equipe;
import entities.FicheDePoste;
import entities.Personne;
import fr.miage.toulouse.projetjee.projetjeeshared.Constants;
import fr.miage.toulouse.projetjee.projetjeeshared.StatutDePoste;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zamourak
 */
@Stateless
public class FicheDePosteFacade extends AbstractFacade<FicheDePoste> implements FicheDePosteFacadeLocal {
    
    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FicheDePosteFacade() {
        super(FicheDePoste.class);
    }

    public StatutDePoste getStatutDePoste(FicheDePoste poste){
        return poste.getStatut();
    }
    /**
     * Ajouter un candidature au liste des candidatures du poste
     * @param poste le poste en question
     * @param candidat le candidat qui postule
     */
    public void ajouterUnCandidatAuPoste(FicheDePoste poste, Personne candidat){
        if(poste.getListeCandidats().contains(candidat)){
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" a déja postulé au poste "+poste.getNom());
        }else{
            poste.ajouterUnCandidature(candidat);
            System.out.println("Candidat "+candidat.getNom()+" "+candidat.getPrenom()+" ajouté avec succès au poste "+poste.getNom());
        }
    }
    
    /**
     * Supprimer un candidature au liste des candidatures du poste
     * @param poste le poste en question
     * @param personne le candidat qui doit être supprimer
     */
    public void supprimerUnCandidatDuPoste(FicheDePoste poste, Personne personne){
        if(!poste.getListeCandidats().contains(personne)){
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" n'existe pas dans le poste "+poste.getNom());
        }else{
            poste.supprimerUnCandidature(personne);
            System.out.println("Candidat "+personne.getNom()+" "+personne.getPrenom()+" supprimé avec succès du poste "+poste.getNom());
        }
    }
    /**
     * Ajouter une compétence dans le liste des compétences demandés
     * @param poste le poste dans lequel il faut ajouter la compétence
     * @param competence la compétence à ajouter
     */
    public void ajouterCompetenceAuPoste(FicheDePoste poste, Competence competence){
        if(poste.getListeCompetenceRecherchees().contains(competence)){
            System.out.println("Le compétence "+competence.getNom()+" existe déja dans la demande du poste "+poste.getNom());
        }else{
            poste.ajouterCompetenceAuPoste(competence);
            System.out.println("Le compétence "+competence.getNom()+" ajouté avec succès dans la demande du poste "+poste.getNom());
        }
    }
    
    /**
     * Supprimer une compétence dans le liste des compétences demandés
     * @param poste le poste dans lequel il faut suprrimer la compétence
     * @param competence la compétence à supprimer
     */
    public void supprimerCompetenceAuPoste(FicheDePoste poste, Competence competence){
        if(!poste.getListeCompetenceRecherchees().contains(competence)){
            System.out.println("Le compétence "+competence.getNom()+" n'existe pas dans la demande du poste "+poste.getNom());
        }else{
            poste.supprimerCompetenceAuPoste(competence);
            System.out.println("Le compétence "+competence.getNom()+" supprimé avec succès de la demande du poste "+poste.getNom());
        }
    }

    /**
     * Supprimer le poste de la BD
     * @param poste la poste à supprimer
     */
    @Override
    public void supprimerPoste(FicheDePoste poste) {
        this.remove(poste);
        System.out.println(Constants.DELETE_SUCCES);
    }
    
    public void creerUnPoste(String nom, String presentationEntreprise, String presentationPoste, 
                                List<Competence> listeCompetenceRecherchees, Equipe equipeDemandeuse){
        FicheDePoste poste = new FicheDePoste(nom, presentationEntreprise, presentationPoste,
                                                listeCompetenceRecherchees,equipeDemandeuse );
        this.create(poste);
        System.out.println(Constants.CREATE_SUCCES);
    }
    public Equipe getEquipeDuDemandeurDePoste(FicheDePoste poste){
        return poste.getEquipeDemandeuse();
    }
    public List<Equipe> getEquipesDemandeursDeCompetence(Competence c){
        List<Equipe> equipes = new ArrayList<>();
        
        List<FicheDePoste> postes = this.findAll();
        
        for(FicheDePoste p : postes){
            Equipe e = p.getEquipeDemandeuse();
            if(!equipes.contains(e)){
                equipes.add(e);
            }
        }
        
        return equipes;
    }
    
    public void setStatut(FicheDePoste p, StatutDePoste statut){
        p.setStatut(statut);
        System.out.println("Statut du poste "+p.getNom()+" remet à "+statut.toString()+" avec succès!");
    }
}

