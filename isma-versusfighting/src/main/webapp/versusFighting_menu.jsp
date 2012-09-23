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
    <title>V-ISMA - Menu</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).keyup(function (event) {
                if (event.keyCode == 13) {
                    $("#form").submit();
                }
            });
        });
    </script>
</head>
<body>
<div id="superglobal">
    <div id="global">
        <h1 style="padding-top: 40px;text-align:center;">A VersusFighting Tournament Application</h1>

        <div style="padding-top: 100px;text-align:center;">
            <s:form id="form" action="next" theme="simple">
                <p class="videogamefont" style="font-size:36px;text-decoration: blink;">
                    PRESS ENTER TO CONTINUE
                </p>
            </s:form>
        </div>
        <br/>
    </div>
    <div id="footer">
        <p align="center" class="videogamefont" style="text-decoration: blink">
            insert coins<br/>
            Credit(s) 0
        </p>

        <p align="right">
            <audio controls>
                <source src="audio/01%20TITLE.ogg" type="audio/ogg"/>
            </audio>
        </p>
    </div>
</div>
</body>
</html>