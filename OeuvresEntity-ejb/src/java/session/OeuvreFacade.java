package session;

import dao.Oeuvre;
import dao.Proprietaire;
import dao.Reservation;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 * 
 */

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OeuvreFacade {
    @PersistenceContext(unitName = "OeuvresEntity-ejbPU")
    private EntityManager em;
    @EJB
    private ProprietaireFacade proprietaireF;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
    * Lecture d'une occurrence d'oeuvre sur la clé primaire
    * @param id_oeuvre
    * @return
    * @throws Exception
    */
    public Oeuvre Lire_Oeuvre_Id(int id_oeuvre) throws Exception {
        try {
            return em.find(Oeuvre.class, id_oeuvre);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
    * Retourne une liste d'objets Oeuvre
    * @return
    * @throws Exception
    */
    public List<Oeuvre> Liste_Oeuvres() throws Exception {
        try {
            return (em.createNamedQuery("Oeuvre.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public Reservation Lire_Reservation_Id(Date dateReservation, int id_oeuvre) throws Exception {
        try {
            Query query = em. createNamedQuery("Reservation.findByDateReservationIdOeuvre");
            query.setParameter("dateReservation", dateReservation, TemporalType.DATE);
            query.setParameter("idOeuvre", id_oeuvre);
            return (Reservation)query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Ajouter_Oeuvre(int id_oeuvre, String titre, double prix, int id_proprietaire)throws Exception {
        Oeuvre oeuvreE = null;
        Proprietaire proprietaireE = null;
        try {
            oeuvreE = new Oeuvre(id_oeuvre);
            proprietaireE = proprietaireF.Lire_Proprietaire_Id(id_proprietaire);
            oeuvreE.setPrix(BigDecimal.valueOf(prix));
            oeuvreE.setTitre(titre);
            oeuvreE.setProprietaire(proprietaireE);
            em.persist(oeuvreE);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Modifier_Oeuvre(int id_oeuvre, String titre, double prix, int id_proprietaire)throws Exception {
        Oeuvre oeuvreE = null;
        Proprietaire proprietaireE = null;
        try {
            oeuvreE = Lire_Oeuvre_Id(id_oeuvre);
            proprietaireE = proprietaireF.Lire_Proprietaire_Id(id_proprietaire);
            oeuvreE.setPrix(BigDecimal.valueOf(prix));
            oeuvreE.setTitre(titre);
            oeuvreE.setProprietaire(proprietaireE);
            em.merge(oeuvreE);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Supprimer_Oeuvre_Id(int id_oeuvre) throws Exception {
        Oeuvre oeuvreE;
        try {
            oeuvreE = Lire_Oeuvre_Id(id_oeuvre);
            em.remove(oeuvreE);
        } catch (Exception e) {
            throw e;
        }
    }
}
