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
import javax.persistence.Query;

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
    
    /**
    * Lecture d'une occurrence d'oeuvre sur la clé primaire
    * @param login
    * @return
    * @throws Exception
    */
    public Proprietaire Lire_Proprietaire_Login(String login) throws Exception {
        try {
            return em.find(Proprietaire.class, login);
            
//            Query query = em. createNamedQuery("Proprietaire.findByLogin");
//            query.setParameter("login", login);
//            return (Proprietaire)query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Proprietaire connecter(String login, String pwd) throws Exception {
        
        try {
//            Proprietaire proprietaire = Lire_Proprietaire_Login(login);
//            if (pwd.equals(proprietaire.getPwd())) {
//                return proprietaire;
//            }
            return Lire_Proprietaire_Login(login);
        } catch (Exception e) {
            return null;
            //throw e;
        }
    }
}
