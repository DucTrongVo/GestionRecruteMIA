/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.FicheDePoste;
import entities.Personne;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.ServiceCandidat;

/**
 *
 * @author zamourak
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> implements PersonneFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }

    ServiceCandidat serviceCandidat;
    

    public void postuler(Personne c, FicheDePoste p){
        if(c.getListePostulation().contains(p)){
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" a déja postuler au poste "+p.getNomFicheDePoste());
        }else{
            c.postuler(p);
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" postulé avec succès au poste "+p.getNomPoste());
            p.ajouterUnCandidature(c);
        }
    }
    
    public void retirerLeCandidature(Candidat c, Poste p){
        if(!c.getListePostulation().contains(p)){
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" n'a pas encore postulé au poste "+p.getNomPoste());
        }else{
            c.retirerLeCandidature(p);
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" a retiré avec succès son candidature au poste "+p.getNomPoste());
            p.ajouterUnCandidature(c);
        }
    }


    public Collaborateur findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Collaborateur> cq = cb.createQuery(Collaborateur.class);
        Root<Collaborateur> root = cq.from(Collaborateur.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), prenom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public Collaborateur creerCollaborateurSiInexistant(String prenom, String nom) {
        try{
            return this.findByPrenomAndNom(prenom, nom);
        }catch(NoResultException noRes){
            Collaborateur nouveauCollaborateur = new Collaborateur(prenom, nom);
            this.create(nouveauCollaborateur);
            return nouveauCollaborateur;
        }
    }    
    
}
