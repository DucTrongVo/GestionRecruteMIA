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
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author zamourak
 */
@Stateless
public class GestionCEO implements GestionCEOLocal {
    @EJB
    private GestionRHLocal gestionRH;
    
    @Override
    public ArrayList<Competence> accederListeCompetence() {
        return (ArrayList<Competence>) gestionRH.getListeCompetencesDemandees();
    }

    @Override
    public ArrayList<Integer> accederListeComptesFichesDePoste() {
        List<FicheDePoste> listePostesNonArchivees;
        List<FicheDePoste> listePostesTotale;
        listePostesNonArchivees = gestionRH.getAllOpenedPoste();
        listePostesNonArchivees.addAll(gestionRH.getAllWaitingPoste());
        listePostesTotale = listePostesNonArchivees;
        listePostesTotale.addAll(gestionRH.getAllClosedPoste());
        ArrayList<Integer> listeComptesPostes = new ArrayList<>();
        listeComptesPostes.add(listePostesNonArchivees.size());
        listeComptesPostes.add(listePostesTotale.size());
        return listeComptesPostes;
    }

    @Override
    public ArrayList<Integer> accederNombreCandidatures() {
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
