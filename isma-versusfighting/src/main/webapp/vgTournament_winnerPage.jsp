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
    <title>V-ISMA - VG Tournament - Tournament Results</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body>
<p align="right">
    <a href="<s:url action='resetGameSession'/>">reinitialisation</a>
    &nbsp;
    <a href="<s:url action='todo'/>">statistiques</a>
</p>
<br/>
<br/>

<h1>Résultats</h1>

<div id="superglobal">
    <div id="global">
        <div style="text-align: center">
            <p class="videogamefont">Vainqueur : <s:property value="winner.label"/></p>
            <img style="margin-top: 10px;margin-bottom: 10px;width: 30%;height: 30%;"
                 src="images/Street_Fighter_Kids_by_imaginism.jpg" alt="">

            <p class="videogamefont">Thank you for playing !</p>
        </div>
    </div>
    <div id="footer"></div>
</div>
<br/>

</body>
</html>