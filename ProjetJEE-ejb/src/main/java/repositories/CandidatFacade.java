/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Candidat;
import entities.Poste;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.ServiceCandidat;

/**
 *
 * @author igouane
 */
@Stateless
public class CandidatFacade extends AbstractFacade<Candidat> implements CandidatFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.toulouse.PROJETJEE_ProjetJEE-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    ServiceCandidat serviceCandidat;
    
    public CandidatFacade() {
        super(Candidat.class);
    }
    
    public void postuler(Candidat c, Poste p){
        if(c.getListePostulation().contains(p)){
            System.out.println("Candidat "+c.getNom()+" "+c.getPrenom()+" a déja postuler au poste "+p.getNomPoste());
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
}
