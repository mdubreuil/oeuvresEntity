<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-target">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.oe">Accueil</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-target">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Oeuvres<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="catalogue.oe">Lister</a></li>
                            <li><a href="ajouter.oe">Ajouter</a></li>
                        </ul>
                    </li>                           
                </ul> 
                <ul class="nav navbar-nav">
                    <li><a href="listeReservations.res">Réservations</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

