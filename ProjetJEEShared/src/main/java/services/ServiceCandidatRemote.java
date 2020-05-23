/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author trongvo
 */
@Remote
public interface ServiceCandidatRemote {
    public void updateListPostes();
    public void updatePosteAConsulter(Long idPoste);
    public void candidater(Long idCandidat, Long idPoste);
    public void retirerUnCandidature(Long idCandidat, Long idPoste);
}
