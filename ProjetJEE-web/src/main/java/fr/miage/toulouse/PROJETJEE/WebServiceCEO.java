/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.PROJETJEE;

import entities.Competence;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import services.ServiceCEOLocal;

/**
 *
 * @author trongvo
 */
@WebService(serviceName = "WebServiceCEO")
@Stateless()
public class WebServiceCEO {

    @EJB
    private ServiceCEOLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "AccederListeCompetence")
    public ArrayList<Competence> AccederListeCompetence() {
        return ejbRef.AccederListeCompetence();
    }

    @WebMethod(operationName = "AccederListeComptesFichesDePoste")
    public ArrayList<Integer> AccederListeComptesFichesDePoste() {
        return ejbRef.AccederListeComptesFichesDePoste();
    }

    @WebMethod(operationName = "AccederNombreCandidatures")
    public ArrayList<Integer> AccederNombreCandidatures() {
        return ejbRef.AccederNombreCandidatures();
    }
    
}
