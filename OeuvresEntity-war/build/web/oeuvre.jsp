<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  class="col-md-8 col-md-offset-2">
    <h1 align='center'>${titre}</h1>
    <br />
    <form class="form-signin form-horizontal" role="form" action="enregistrer.oe?id=${oeuvreR.idOeuvre}" method="post">
        <div class="form-group" align="center">
            <label class="col-md-2 col-md-offset-2 control-label">Titre : </label>
            <div class="col-md-4">
               <input type="text" name="txtTitre" value="${oeuvreR.titre}" class="form-control" placeholder="Titre de l'oeuvre">
            </div>
        </div>
            <br />
        <div class="form-group" align="center">
            <label class="col-md-2 col-md-offset-2 control-label">Prix : </label>
            <div class="col-md-4">
                <input type="text" name="txtPrix" value="${oeuvreR.prix}"  class="form-control" placeholder="Prix de l'oeuvre" required>
            </div>
        </div>
            <br />
        <div class="form-group" align="center">
            <label class="col-md-2 col-md-offset-2 control-label">Proprietaire : </label>
            <div class="col-md-4">
                <select class='form-control' name='lProprietaires' required>
                    <c:forEach var="proprietaireE" items="${lstProprietairesR}">
                        <option value="${proprietaireE.idProprietaire}"<c:if test="${proprietaireE.idProprietaire == oeuvreR.proprietaire.idProprietaire}"> SELECTED</c:if> >${proprietaireE.prenomProprietaire} ${proprietaireE.nomProprietaire}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
            <br />
        <div class="form-group" align="center">
            <div class="col-md-4 col-md-offset-4">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider </button>
            </div>
        </div>
    </form>
</div>