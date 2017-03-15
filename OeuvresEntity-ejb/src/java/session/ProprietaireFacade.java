package session;

import dao.Oeuvre;
import dao.Proprietaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProprietaireFacade {
    @PersistenceContext(unitName = "OeuvresEntity-ejbPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
    * Retourne une liste d'objets Proprietaire
    * @return
    * @throws Exception
    */
    public List<Proprietaire> Liste_Proprietaires() throws Exception {
        try {
            return (em.createNamedQuery("Proprietaire.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
    * Lecture d'une occurrence d'oeuvre sur la clé primaire
    * @param id_proprio
    * @return
    * @throws Exception
    */
    public Proprietaire Lire_Proprietaire_Id(int id_proprio) throws Exception {
        try {
            return em.find(Proprietaire.class, id_proprio);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
