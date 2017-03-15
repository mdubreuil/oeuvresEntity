
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
