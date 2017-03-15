
package session;

import dao.Adherent;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 */
@Stateless
@LocalBean
public class AdherentFacade
{
    @PersistenceContext(unitName = "OeuvresEntity-ejbPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
    * Lecture d'une occurrence d'adherent sur la clé primaire
    * @param id_adherent
    * @return
    * @throws Exception
    */
    public Adherent Lire_Adherent_Id(int id_adherent) throws Exception {
        try {
            return em.find(Adherent.class, id_adherent);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
    * Retourne une liste d'objets Oeuvre
    * @return
    * @throws Exception
    */
    public List<Adherent> Liste_Adherents() throws Exception {
        try {
            return (em.createNamedQuery("Adherent.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
}
