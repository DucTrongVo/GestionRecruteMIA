/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.PROJETJEE;

import entities.FicheDePoste;
import fr.miage.toulouse.projetjee.projetjeeshared.FicheDePosteShared;
import fr.miage.toulouse.projetjee.projetjeeshared.PersonneShared;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import services.ServiceCandidatLocal;

/**
 *
 * @author trongvo
 */
@WebService(serviceName = "WebServiceCandidat")
@Stateless()
public class WebServiceCandidat {

    @EJB
    private ServiceCandidatLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerCandidatSiInexistant")
    public String creerCandidatSiInexistant(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return ejbRef.creerCandidatSiInexistant(nom, prenom);
    }

    @WebMethod(operationName = "getAllOpenedPoste")
    public List<FicheDePoste> getAllOpenedPoste() {
        return ejbRef.getAllOpenedPoste();
    }

    @WebMethod(operationName = "getPosteAConsulter")
    public FicheDePoste getPosteAConsulter(@WebParam(name = "idPoste") Long idPoste) {
        return ejbRef.getPosteAConsulter(idPoste);
    }

    @WebMethod(operationName = "creerUneCandidature")
    public String creerUneCandidature(@WebParam(name = "idCandidat") Long idCandidat, @WebParam(name = "idPoste") Long idPoste) {
        return ejbRef.creerUneCandidature(idCandidat, idPoste);
    }

    @WebMethod(operationName = "retirerUneCandidature")
    public String retirerUneCandidature(@WebParam(name = "idCandidat") Long idCandidat, @WebParam(name = "idPoste") Long idPoste) {
        return ejbRef.retirerUneCandidature(idCandidat, idPoste);
    }
    
}
