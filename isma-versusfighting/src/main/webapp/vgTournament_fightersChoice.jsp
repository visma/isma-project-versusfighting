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

    <link rel="stylesheet" media="screen" href="css/vgTournament.css" type="text/css"/>
    <title>V-ISMA - VG Tournament - Fighters choice</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function checkNextPageSubmitEnable() {
            var selectionOver = $("#selectionOverForPlayers").val() == "true";
            $("#nextPageSubmit").attr("disabled", !selectionOver);
        }
        function initializeImagesEffects() {
            $('.fighterThumb').hover(
                    function () {
                        $(this).animate({opacity:"1"}, 300);
                        $(this).css('cursor', 'pointer');

                    },
                    function () {
                        $(this).css('cursor', 'default');
                        if (!isImageSelected($(this))) {
                            $(this).animate({opacity:"0.5"}, 300);
                        }
                    }
            );
            $('.fighterThumb').click(function () {
                onImageClick($(this));
                checkNextPageSubmitEnable();
            });
        }

        function isImageSelected(image) {
            var hiddenId = image.attr("hiddenid");
            //Conversion en boolean
            var selected = $('#' + hiddenId).val() == "true";
            return selected;
        }
        function onImageClick(image) {
            var hiddenId = image.attr("hiddenid");
            var selected = isImageSelected(image);
            var fighterRemainingField = $("#fightersRemaining");
            var fightersRemainingInt = parseInt(fighterRemainingField.val());
            if (!selected && fightersRemainingInt == 0) {
                return;
            }
            selected = !selected;
            $("#fightersRemaining").val(selected ? fightersRemainingInt - 1 : fightersRemainingInt + 1);
            $('#' + hiddenId).val(selected);
            applyImageSelectionStyle(image, selected);
        }
        function applyImageSelectionStyle(image, selected) {
            if (selected) {
                image.css({backgroundColor:"yellow", opacity:"1"});
            } else {
                image.css({backgroundColor:"transparent", opacity:"0.5"});
            }
        }


        $(document).ready(function () {
            initializeImagesEffects();
            checkNextPageSubmitEnable();
            $("#choosedPlayer").change(function () {
                $('#changePlayerForFightersChoiceForm').submit();
            });
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

        <h1>Choix des combattants</h1>
        <s:form id="changePlayerForFightersChoiceForm" action="changePlayerForFightersChoice">
            <s:hidden name="selectionOverForPlayers" id="selectionOverForPlayers" disabled="true" theme="simple"/>
            <table border="1">
                <s:select
                        id="choosedPlayer"
                        name="choosedPlayer"
                        label="Joueur "
                        headerValue="--- Choose player for fighters selection ---"
                        headerKey="-1"
                        list="playerList"
                        listKey="id"
                        listLabel="label"
                        value="defaultChoosedPlayerId"
                        />
                <tr>
                    <td>Fighters remaining</td>
                    <td>
                        <s:textfield name="fightersRemaining" id="fightersRemaining" disabled="true" theme="simple"/>
                    </td>
                </tr>
            </table>
            <br/>
        </s:form>
        <s:form id="validateFightersChoiceForm" action="validateFightersChoice">
        <table border="1">
            <s:iterator var="line" value="data" status="stat">
                <tr>
                    <s:iterator var="cell" value="line" status="stat2">
                        <td>
                            <input type="hidden"
                                   name="data[<s:property value="%{#stat.index}"/>][<s:property value="%{#stat2.index}"/>].selected"
                                   id="datax<s:property value="%{#stat.index}"/>y<s:property value="%{#stat2.index}"/>"
                                   value="<s:property value="#cell.selected"/>"
                                    />
                            <!-- TODO essayer de passer par le getGame() de l'action plutot que cette bidouille sur le FighterInfo... -->
                            <!-- TODO essayer d'utiliser une variable pour le style de l'image plutot qu'un gros copié collé -->
                            <s:if test="#cell.selected">
                                <%--<s:set var="imageStyle" value="background-color:transparent; opacity:0.5"/>--%>
                                <img
                                        id="<s:property value="'fighter_' + #cell.fighter.label + '_selected'"/>"
                                        hiddenid="datax<s:property value="%{#stat.index}"/>y<s:property value="%{#stat2.index}"/>"
                                        class="fighterThumb"
                                        style="background-color:yellow; opacity:1"
                                        src="<s:property value="'images/' + #cell.game.id.toLowerCase() + '/' + #cell.fighter.imageFileName"/>"/>
                            </s:if>
                            <s:else>
                                <%--<s:set var="imageStyle" value="background-color:yellow; opacity:1"/>--%>
                                <img
                                        id="<s:property value="'fighter_' + #cell.fighter.label + '_selected'"/>"
                                        hiddenid="datax<s:property value="%{#stat.index}"/>y<s:property value="%{#stat2.index}"/>"
                                        class="fighterThumb"
                                        style="background-color:transparent; opacity:0.5"
                                        src="<s:property value="'images/' + #cell.game.id.toLowerCase() + '/' + #cell.fighter.imageFileName"/>"/>
                            </s:else>
                        </td>

                    </s:iterator>
                </tr>
            </s:iterator>
        </table>
        <p style="padding-top: 10px;">
                <s:submit id="validateFightersChoiceSubmit" action="validateFightersChoice" value="Sauvegarder"
                          theme="simple"
                          disabled="false"/>&nbsp;
                <s:submit id="nextPageSubmit" action="validateFighters" value="Suivant" theme="simple" disabled="true"/>

        <p>
            </s:form>
    </div>
    <div id="footer">
    </div>
</div>
</body>
</html>