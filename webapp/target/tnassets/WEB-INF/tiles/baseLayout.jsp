<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    	<!-- DEFINE app context relative path as the href base -->
        <c:set var="url">${pageContext.request.requestURL}</c:set>
    	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link type="text/css" rel="stylesheet" href="resources/css/tnassets.css">
        <link type="text/css" rel="stylesheet" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
		<script type="text/javascript" src="resources/js/tnassets-core.js"></script>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    </head>

    <body>
    <div style="position:absolute; top: 20px; right: 10px; color: white;">
			Version 0.1.1-alpha
	</div>
        <table style="width:100%; height:100%;" border="0" cellpadding="0" cellspacing="0" align="center">
            <tr>
                <td style="padding: 0px;">
					<div align="center" style="background: black; padding:10px 10px 10px 10px; box-shadow: 0 3px 6px rgba(0,0,0,0.16);">
						<table style="width:100%;">
							<tr>
								<td style="width:100%; height: 25px; background-image:url('<tiles:insertAttribute name="header"/>'); background-repeat:no-repeat;" align="center">
								<!-- 
									<div class="menu" style="margin:10px 0px 0px 0px;">
										<a href="/tnassets/">Menu</a> |
										<a href="instances">Instances</a> |
									</div>
 -->									
								</td>
							</tr>
						</table>
					</div>
                </td>
            </tr>
            <tr>
                <td valign="top" style="width:100%; height:100%; padding: 10px;">
	                   <tiles:insertAttribute name="body" />
                </td>
            </tr>

        </table>
    </body>
</html>