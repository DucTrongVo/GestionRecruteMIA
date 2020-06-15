/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Competence;
import entities.FicheDePoste;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author zamourak
 */
@Stateless
public class GestionCEO implements GestionCEOLocal {

    private GestionRH gestionRH;
    
    @Override
    public ArrayList<Competence> AccederListeCompetence() {
        this.gestionRH = new GestionRH();
        return (ArrayList<Competence>) gestionRH.getListeCompetencesDemandees();
    }

    @Override
    public ArrayList<Integer> AccederListeComptesFichesDePoste() {
        this.gestionRH = new GestionRH();
        List<FicheDePoste> listePostesNonArchivees;
        List<FicheDePoste> listePostesTotale;
        listePostesNonArchivees = gestionRH.getAllOpenedPoste();
        listePostesNonArchivees.addAll(gestionRH.getAllWaitingPoste());
        listePostesTotale = listePostesNonArchivees;
        listePostesTotale.addAll(gestionRH.getAllClosedPoste());
        ArrayList<Integer> listeComptesPostes = new ArrayList<Integer>();
        listeComptesPostes.add(listePostesNonArchivees.size());
        listeComptesPostes.add(listePostesTotale.size());
        return listeComptesPostes;
    }

    @Override
    public ArrayList<Integer> AccederNombreCandidatures() {
        this.gestionRH = new GestionRH();
        int nombreCandidaturesRetenues = 0;
        int nombreCandidaturesTotales = 0;
        List<FicheDePoste> listePostesArchivee = gestionRH.getAllClosedPoste();
        for(FicheDePoste ficheDePoste : listePostesArchivee){
            nombreCandidaturesRetenues += 1;
            nombreCandidaturesTotales += ficheDePoste.getListeCandidats().size();
        }
        ArrayList<Integer> listeComptesCandidatures = new ArrayList<Integer>();
        listeComptesCandidatures.add(nombreCandidaturesRetenues);
        listeComptesCandidatures.add(nombreCandidaturesTotales);
        return listeComptesCandidatures;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
