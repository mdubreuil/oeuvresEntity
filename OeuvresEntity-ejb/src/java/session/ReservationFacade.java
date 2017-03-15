package session;

import dao.Reservation;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mélanie DUBREUIL et Ophélie EOUZAN
 * 
 */
@Stateless
@LocalBean
public class ReservationFacade {
    @PersistenceContext(unitName = "OeuvresEntity-ejbPU")
    private EntityManager em;
    
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
}
