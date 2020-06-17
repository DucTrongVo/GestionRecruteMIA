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
import javax.jws.WebParam;
import services.ServiceManagerLocal;

/**
 *
 * @author trongvo
 */
@WebService(serviceName = "WebServiceManager")
@Stateless()
public class WebServiceManager {

    @EJB
    private ServiceManagerLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getAllCompetencesEquipe")
    public String getAllCompetencesEquipe(@WebParam(name = "idEquipe") Long idEquipe) {
        return ejbRef.getAllCompetencesEquipe(idEquipe);
    }

    @WebMethod(operationName = "creerFicheDePosteDeDemande")
    public String creerFicheDePosteDeDemande(@WebParam(name = "nomFicheDePoste") String nomFicheDePoste, @WebParam(name = "nomCompetences") String nomCompetences, @WebParam(name = "nomEquipe") String nomEquipe) {
        return ejbRef.creerFicheDePosteDeDemande(nomFicheDePoste, nomCompetences, nomEquipe);
    }
    
}
