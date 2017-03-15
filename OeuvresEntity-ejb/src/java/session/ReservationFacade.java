package session;

import dao.Adherent;
import dao.Oeuvre;
import dao.Reservation;
import dao.ReservationPK;
import java.util.Date;
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
public class ReservationFacade {
    
    @PersistenceContext(unitName = "OeuvresEntity-ejbPU")
    private EntityManager em;
    
    @EJB
    private AdherentFacade adherentF;

    @EJB
    private OeuvreFacade oeuvreF;

    /**
    * Retourne une liste d'objets Reservation
    * @return
    * @throws Exception
    */
    public List<Reservation> getReservations() throws Exception {
        try {
            return (em.createNamedQuery("Reservation.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Reservation getReservationByDateAndOeuvre(Date dateReservation,int id_oeuvre) throws Exception {
        try {
            Query query = em. createNamedQuery("Reservation.findByCouple");
            query.setParameter("dateReservation", dateReservation, TemporalType.DATE);
            query.setParameter("idOeuvre", id_oeuvre);
            return (Reservation)query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Ajouter_Reservation(int id_oeuvre, int id_adherent, Date dateReservation) throws Exception {
        Reservation reservationE = new Reservation();
        Oeuvre oeuvreE = null;
        Adherent adherentE = null;
        try {           
            oeuvreE = oeuvreF.Lire_Oeuvre_Id(id_oeuvre);
            adherentE = adherentF.Lire_Adherent_Id(id_adherent);
            ReservationPK reservationPk = new ReservationPK(dateReservation, oeuvreE.getIdOeuvre());
            reservationE.setReservationPK(reservationPk);
            reservationE.setOeuvre(oeuvreE);
            reservationE.setAdherent(adherentE);

            em.persist(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Modifier_Reservation(int id_oeuvre, Date dateReservation) throws Exception {
        Reservation reservationE = null;
        try {
            reservationE = getReservationByDateAndOeuvre(dateReservation,id_oeuvre);
            reservationE.setStatut("Confirmée");
            em.merge(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Supprimer_Reservation_Id(int id_oeuvre, Date dateReservation) throws Exception {
        Reservation reservationE = null;
        try {
            reservationE = getReservationByDateAndOeuvre(dateReservation,id_oeuvre);
            em.remove(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }
}
