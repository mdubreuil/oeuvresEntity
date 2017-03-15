<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  class="col-md-8 col-md-offset-1">
    <h1 align='center'>${titre}</h1>
    <form class="form-signin form-horizontal" role="form" action="enregistrer.oe?id=${oeuvreR.idOeuvre}" method="post">
        <div class="form-group">
            <label class="col-md-3 control-label">Titre : </label>
            <div class="col-md-6">
               <input type="text" name="txtTitre" value="${oeuvreR.titre}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prix : </label>
            <div class="col-md-3">
                <input type="text" name="txtPrix" value="${oeuvreR.prix}"  class="form-control" placeholder="Prix de l'oeuvre" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Proprietaire : </label>
            <div class="col-sm-6 col-md-3">
                <select class='form-control' name='lstProprietaires' required>
                    <c:forEach var="proprietaireE" items="${lProprietairesR}">
                        <option value="${proprietaireE.idProprietaire}"<c:if test="${proprietaireE.idProprietaire == oeuvreR.proprietaire.idProprietaire}"> SELECTED</c:if> >${proprietaireE.prenomProprietaire} ${proprietaireE.nomProprietaire}</option>
                    </c:forEach>
                </select>
            </div>
        </div>             
        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider </button>
            </div>
        </div>
    </form>
</div>