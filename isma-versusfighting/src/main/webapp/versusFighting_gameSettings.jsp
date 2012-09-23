<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ taglib prefix="html" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href='http://fonts.googleapis.com/css?family=Holtwood+One+SC' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Monofett' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Bangers' rel='stylesheet' type='text/css'>


    <link rel="stylesheet" media="screen" href="css/versusFighting.css" type="text/css"/>
    <title>V-ISMA - VG Tournament - Game settings</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="js/jquery.numeric.js"></script>
    <script type="text/javascript">
        function configureFighterAmountTextField() {
            $("#fightersAmountTextField").numeric();

        }
        function checkEnableNextPageSubmit() {
            var gameComboBox = $("#gameComboBox");
            var matchesAmountComboBox = $("#matchesAmountComboBox");
            var fightersAmountTextField = $("#fightersAmountTextField");

            var enabled = gameComboBox.attr('value') != -1
                    && matchesAmountComboBox.attr('value') != -1
                    && fightersAmountTextField.val().length > 0
                    && checkFightersAmountValidity();
            $("#nextPageSubmit").attr("disabled", !enabled);
        }
        function checkFightersAmountValidity() {
            var gameId = $("#gameComboBox").find('option:selected').val();
            var castingCount = $("#game_" + gameId + "_castingCount");
            var fightersAmountTextField = $("#fightersAmountTextField");
            var fighterAmountInt = parseInt(fightersAmountTextField.val());
            var castingCountInt = parseInt(castingCount.val());
            if ((fighterAmountInt > castingCountInt) || (fighterAmountInt < 1)) {
                fightersAmountTextField.css({color:"red"});
                return false;
            } else {
                //TODO il faudrait mieux récupérer la vraie valeur du foreground du textfield plutot que de mettre black
                fightersAmountTextField.css({color:"black"});
                return true;
            }
        }
        function enableNextPageSubmit() {
            $("#gameComboBox").change(function () {
                checkEnableNextPageSubmit();
                checkFightersAmountValidity();
            });
            $("#matchesAmountComboBox").change(function () {
                checkEnableNextPageSubmit();
            });
            $("#fightersAmountTextField").keyup(function () {
                checkEnableNextPageSubmit();
                checkFightersAmountValidity();
            });
        }
        $(document).ready(function () {
            configureFighterAmountTextField();
            enableNextPageSubmit();
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
        <s:iterator var="game" value="games">
            <s:hidden name="game_%{#game.id}_castingCount" id="game_%{#game.id}_castingCount"
                      value="%{#game.casting.fighterCount}"/>
        </s:iterator>
        <h1>Paramètrage du tournoi</h1>
        <s:form action="validateGameSettings">
            <table border="1">
                <s:select
                        id="gameComboBox"
                        name="choosedGame"
                        label="Choix du jeu"
                        headerValue="--- Select a versus fighting game ---"
                        headerKey="-1"
                        list="games"
                        listKey="id"
                        listLabel="label"/>
                <s:textfield id="fightersAmountTextField" name="fightersAmount" value="" label="Nombre de personnages"
                             size="2"
                             maxlength="2"/>
                <s:select
                        id="matchesAmountComboBox"
                        name="matchesAmount"
                        label="Nombre de matches"
                        headerValue="--- Select matches amount ---"
                        headerKey="-1"
                        list="matchesAmounts"
                        listKey="amount"
                        listLabel="label"/>
            </table>
            <p style="padding-top: 10px;">
                <s:submit id="nextPageSubmit" value="Etape Suivante" theme="simple" disabled="true"/>
            </p>
        </s:form>
    </div>
    <div id="footer">
    </div>
</div>
</body>
</html>