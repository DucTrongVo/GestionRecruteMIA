/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.PROJETJEE;

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

    @WebMethod(operationName = "accederListeCompetence")
    public String accederListeCompetence() {
        return ejbRef.accederListeCompetence();
    }

    @WebMethod(operationName = "accederListeComptesFichesDePoste")
    public String accederListeComptesFichesDePoste() {
        return ejbRef.accederListeComptesFichesDePoste();
    }

    @WebMethod(operationName = "accederNombreCandidatures")
    public String accederNombreCandidatures() {
        return ejbRef.accederNombreCandidatures();
    }
    
}
