<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Catalogue des oeuvres</h1>
    <br />
    <table class="table table-bordered table-striped">
        <thead>
            <tr align='center'>
                <td>Titre</td>
                <td>Nom propri�taire</td>
                <td>Pr�nom propri�taire</td>
                <td >D�tail</td>
                <td>R�server</td>
                <td>Modifier</td>
                <td>Supprimer</td>                
            </tr>  
        </thead>
        <tbody>
            <c:forEach var="oeuvre" items="${lstOeuvresR}">
                <tr align='center'>
                    <td>${oeuvre.titre}</td>
                    <td>${oeuvre.proprietaire.nomProprietaire}</td>
                    <td>${oeuvre.proprietaire.prenomProprietaire}</td>
                    <td><a class="btn btn-primary" href="detail.oe?id=${oeuvre.idOeuvre}">D�tail</a></td>
                    <td><a class="btn btn-primary" href="reserver.res?id=${oeuvre.idOeuvre}">R�server</a></td>
                    <td><a class="btn btn-primary" href="modifier.oe?id=${oeuvre.idOeuvre}">Modifier</a></td>
                    <td><a class="btn btn-primary" onclick="javascript:if (confirm('Suppression confirm�e ?')){ window.location='supprimer.oe?id=${oeuvre.idOeuvre}';}">Supprimer</a></td>                      
                </tr>
            </c:forEach>
        </tbody>
    </table>          
</div>
