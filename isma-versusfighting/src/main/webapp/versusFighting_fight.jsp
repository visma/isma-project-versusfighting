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
    <title>V-ISMA - VG Tournament - Fight !</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="js/grayscale.js"></script>
    <script type="text/javascript" src="js/jquery.gradienttext-0.1.js"></script>
    <script type="text/javascript">
        function enableNextRoundSubmitButton() {
            var disabled = $("#winnerCombo").attr('value') == -1;
            $("#nextRoundSubmit").attr("disabled", disabled)
        }
        function enableGradientVersusTextEffect() {
            $("#versus").gradienttext({
//                colors:['#F06030', '#FFFFE0'],
                colors:['red', 'yellow'],
                style:'vertical',
                shadow:true,
                shadow_color:'#000000',
                shadow_offset_x:1,
                shadow_offset_x:1,
                shadow_blur:1
            });
        }
        $(document).ready(function () {
            enableGradientVersusTextEffect();
            enableNextRoundSubmitButton();
            $('.fighterEliminatedThumb').grayscale();
            $('#winnerCombo').change(function () {
                enableNextRoundSubmitButton();
            })
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


        <h1>Tableau de bord du tournoi</h1>
        <table>
            <s:iterator var="player" value="playerList" status="stat">
                <tr>
                    <td><s:property value="#player.label"/></td>
                    <s:iterator var="fighter" value="fighterInstanceList" status="stat2">
                        <td>
                            <!-- TODO essayer d'utiliser une variable plutot qu'un gros copié collé -->
                            <s:if test="#fighter.hidden">
                                <img
                                        class="fighterThumb"
                                        style="background-color:transparent; opacity:1"
                                        src="images/eb_questionMark.gif"/>
                            </s:if>
                            <s:elseif test="#fighter.active">
                                <img
                                        class="fighterThumb"
                                        style="background-color:transparent; opacity:1"
                                        src="<s:property value="'images/' + #fighter.game.id.toLowerCase() + '/' + #fighter.object.imageFileName"/>"/>
                            </s:elseif>
                            <s:else>
                                <img
                                        class="fighterEliminatedThumb"
                                        style="opacity:1"
                                        src="<s:property value="'images/' + #fighter.game.id.toLowerCase() + '/' + #fighter.object.imageFileName"/>"/>
                            </s:else>
                        </td>
                    </s:iterator>
                </tr>
            </s:iterator>
        </table>
        <h1>Match en cours</h1>

        <div>
            <table>
                <tr>
                    <td>
                        <img
                                class="fighterThumb"
                                style="background-color:transparent; opacity:1"
                                src="<s:property value="'images/' + fighterP1.game.id.toLowerCase() + '/' + fighterP1.object.imageFileName"/>"/>
                    </td>
                    <td><b id="versus" style="font-size: 20px;">VERSUS</b></td>
                    <td>
                        <img
                                class="fighterThumb"
                                style="background-color:transparent; opacity:1"
                                src="<s:property value="'images/' + fighterP2.game.id.toLowerCase() + '/' + fighterP2.object.imageFileName"/>"/>
                    </td>
                </tr>
                <tr>
                    <td><b class="videogamefont"><s:property value="player1.label"/> </b></td>
                    <td>&nbsp;</td>
                    <td><b class="videogamefont"><s:property value="player2.label"/> </b></td>
                </tr>
            </table>
        </div>
        <div>
            <h1>Résolution</h1>
            <s:form action="nextRound">
                <div class="videogame">
                    <p>Vainqueur :
                        <s:select
                                id="winnerCombo"
                                name="winner"
                                label="Choix du vainqueur"
                                headerValue="--- Declare Winner ---"
                                headerKey="-1"
                                list="fightingPlayerList"
                                listKey="object.id"
                                listLabel="label"
                                theme="simple"
                                />
                        <s:submit id="nextRoundSubmit" value="Next Round" theme="simple"/>
                    </p>
                </div>
            </s:form>
        </div>
        <br/>
    </div>
    <div id="footer">

    </div>
</div>
</body>
</html>