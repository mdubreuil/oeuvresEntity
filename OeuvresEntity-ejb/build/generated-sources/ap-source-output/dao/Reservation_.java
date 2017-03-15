package dao;

import dao.Adherent;
import dao.Oeuvre;
import dao.ReservationPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T14:31:08")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, ReservationPK> reservationPK;
    public static volatile SingularAttribute<Reservation, Adherent> adherent;
    public static volatile SingularAttribute<Reservation, Oeuvre> oeuvre;
    public static volatile SingularAttribute<Reservation, String> statut;

}