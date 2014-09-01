
<%@ page import="br.org.Ocorrencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ocorrencia.label', default: 'Ocorrencia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ocorrencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="map" action="map"><g:message code="Ocorrencia Map" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ocorrencia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="filename" title="${message(code: 'ocorrencia.filename.label', default: 'Filename')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'ocorrencia.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="data" title="${message(code: 'ocorrencia.data.label', default: 'Data')}" />
					
						<g:sortableColumn property="latitude" title="${message(code: 'ocorrencia.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'ocorrencia.longitude.label', default: 'Longitude')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ocorrenciaInstanceList}" status="i" var="ocorrenciaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ocorrenciaInstance.id}">${fieldValue(bean: ocorrenciaInstance, field: "filename")}</g:link></td>
					
						<td>${fieldValue(bean: ocorrenciaInstance, field: "status")}</td>
					
						<td><g:formatDate date="${ocorrenciaInstance.data}" /></td>
					
						<td>${fieldValue(bean: ocorrenciaInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: ocorrenciaInstance, field: "longitude")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ocorrenciaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
