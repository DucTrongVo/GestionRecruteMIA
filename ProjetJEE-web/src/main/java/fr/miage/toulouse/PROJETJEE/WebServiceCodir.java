/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.PROJETJEE;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import services.ServiceCodirLocal;

/**
 *
 * @author trongvo
 */
@WebService(serviceName = "WebServiceCodir")
@Stateless()
public class WebServiceCodir {

    @EJB
    private ServiceCodirLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "validerLaCreationUnPoste")
    @Oneway
    public void validerLaCreationUnPoste(@WebParam(name = "idPersonne") Long idPersonne, @WebParam(name = "idPoste") Long idPoste, @WebParam(name = "presentationEntreprise") String presentationEntreprise, @WebParam(name = "presentationPoste") String presentationPoste) {
        ejbRef.validerLaCreationUnPoste(idPersonne, idPoste, presentationEntreprise, presentationPoste);
    }

    @WebMethod(operationName = "feuVertCandidat")
    @Oneway
    public void feuVertCandidat(@WebParam(name = "idPersonne") Long idPersonne, @WebParam(name = "idPoste") Long idPoste) {
        ejbRef.feuVertCandidat(idPersonne, idPoste);
    }

    @WebMethod(operationName = "feuRougeCandidat")
    @Oneway
    public void feuRougeCandidat(@WebParam(name = "idPersonne") Long idPersonne, @WebParam(name = "idPoste") Long idPoste) {
        ejbRef.feuRougeCandidat(idPersonne, idPoste);
    }
    
}
