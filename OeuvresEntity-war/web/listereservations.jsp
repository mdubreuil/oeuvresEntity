<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Liste des r�servations</h1>
    <br />
    <table class="table table-bordered table-striped">
        <thead>
            <tr align='center'>
                <td>Titre</td>
                <td>Date</td>
                <td>Statut</td>
                <td>Pr�nom adh�rent</td>
                <td>Nom adh�rent</td>
                <td>Confirmer</td>
                <td>Supprimer</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="reservation" items="${lstReservationsR}">
                <tr align='center'>
                    <td>${reservation.oeuvre.titre}</td>
                    <td><fmt:formatDate value="${reservation.reservationPK.dateReservation}" type="date" pattern="yyyy-MM-dd"/></td>
                    <td>${reservation.statut}</td>
                    <td>${reservation.adherent.prenomAdherent}</td>
                    <td>${reservation.adherent.nomAdherent}</td>
                    <td><a class="btn btn-primary" href="confirmerReservation.res?id=${reservation.reservationPK.idOeuvre}&dateres='<fmt:formatDate value="${reservation.reservationPK.dateReservation}" type="date" pattern="yyyy-MM-dd"/>'">Confirmer</a></td>
                    <td><a class="btn btn-primary" href="supprimerReservation.res?id=${reservation.reservationPK.idOeuvre}&dateres='<fmt:formatDate value="${reservation.reservationPK.dateReservation}" type="date" pattern="yyyy-MM-dd"/>'">Supprimer</a></td>                    
                </tr>
            </c:forEach>                    
        </tbody>
    </table>              
</div>