<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ taglib prefix="html" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>V-ISMA - VG Tournament - Players choice</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href='http://fonts.googleapis.com/css?family=Holtwood+One+SC' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Monofett' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Bangers' rel='stylesheet' type='text/css'>

    <script src="js/jquery-1.7.1.js" type="text/javascript"></script>

    <link rel="stylesheet" media="screen" href="css/versusFighting.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">

        function enableAddPlayerSubmit() {
            var addPlayerSubmit = $("#addPlayerSubmit");
            var choosePlayerComboBox = $("#choosePlayerComboBox");
            choosePlayerComboBox.change(function () {
                addPlayerSubmit.attr("disabled", $(this).attr('value') == -1);
            });
        }
        function enableNextPageSubmit() {
            var nextPageSubmit = $("#nextPageSubmit");
            var playerTable = $("#playersTable tr");
            var registeredPlayerCount = playerTable.length - 1;
            nextPageSubmit.attr("disabled", registeredPlayerCount < 2);
        }
        function enableSavePlayerSubmit() {
            var newPlayerTextField = $("#newPlayerTextField");
            var savePlayerSubmit = $("#savePlayerSubmit");
            newPlayerTextField.keyup(function () {
                var input = $(this).val();
                savePlayerSubmit.attr('disabled', input.trim().length == 0);

            });
        }

        $(document).ready(function () {
            enableNextPageSubmit();
            enableSavePlayerSubmit();
            enableAddPlayerSubmit();
        });
    </script>
</head>
<body>
<div id="superglobal">
    <div id="global">
        <p align="right">
            <a href="<s:url action='resetGameSession'/>">reinitialisation</a>
            &nbsp;
            <a href="<s:url action='todo'/>">statistiques</a>
        </p>

        <h1>Choix des joueurs</h1>
        <s:form action="savePlayer" theme="simple">
            <div class="videogame">
                <p>Enregistrer joueur</p>

                <p><s:textfield id="newPlayerTextField" name="newPlayer" value="" theme="simple"/></p>

                <p><s:submit id="savePlayerSubmit" value="Enregistrer" theme="simple" disabled="true"/></p>
            </div>
        </s:form>
        <s:form action="registerPlayer" theme="simple">
            <%--<h2>Ajout d'un joueur</h2>--%>
            <br/>

            <div class="videogame">
                <p>Ajouter joueur</p>

                <p><s:select name="choosedPlayer"
                             label="Player choice"
                             headerValue="--- Select a new challenger ---"
                             headerKey="-1"
                             list="availablePlayerList"
                             listKey="id"
                             listLabel="label"
                             id="choosePlayerComboBox"
                        />
                </p>

                <p>
                    <s:submit id="addPlayerSubmit" value="Ajouter" theme="simple" disabled="true"/>
                </p>
            </div>
        </s:form>
        <h1>Liste des joueurs</h1>

        <s:if test="%{registeredPlayerList.isEmpty()}">
            <div>
                <p class="videogamefont">Veuillez selectionner des joueurs</p>
            </div>
        </s:if>
        <s:else>
            <div>
                <table id="playersTable" border="1" class="tableau">
                    <tr>
                        <th>Joueur</th>
                    </tr>
                    <s:iterator var="player" value="registeredPlayerList" status="stat">
                        <tr>
                            <td><s:property value="name"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </s:else>
        <s:form action="validatePlayers" theme="simple">
            <p style="padding-top: 10px;">
                <s:submit id="nextPageSubmit" value="Etape Suivante" theme="simple"/>
            </p>
        </s:form>
    </div>
    <div id="footer">

    </div>
</div>
</body>
</html>