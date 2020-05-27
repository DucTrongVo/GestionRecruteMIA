/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author igouane
 */
@Entity
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Personne manager;
    private String nom;

    @OneToMany(mappedBy = "equipe")
    private List<Personne> collaborateurs;

    public Equipe() {
    }

    public Equipe(String nomEquipe,Personne manager) {
        this.nom=nomEquipe;
        this.manager=manager;
    }

    public Personne getManager() {
        return manager;
    }

    public void setManager(Personne manager) {
        this.manager = manager;
    }

    public List<Personne> getCollaborateurs() {
        return collaborateurs;
    }

    public void setCollaborateurs(List<Personne> collaborateurs) {
        this.collaborateurs = collaborateurs;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipe[ id=" + id + " ]";
    }
    
}
/*
    public ArrayList<Competence> listerLesCompetences(){
        ArrayList<Competence> listeCompetences = new ArrayList<Competence>();
        
        return listeCompetences;
    }
*/