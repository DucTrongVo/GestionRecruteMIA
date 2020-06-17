/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Competence;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author zamourak
 */
@Local
public interface ServiceCEOLocal {
    public String accederListeCompetence();
    public String accederListeComptesFichesDePoste();
    public String accederNombreCandidatures();
}
