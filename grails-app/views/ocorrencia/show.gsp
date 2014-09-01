
<%@ page import="br.org.Ocorrencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ocorrencia.label', default: 'Ocorrencia')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ocorrencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ocorrencia" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ocorrencia">
				
				<g:if test="${ocorrenciaInstance?.file}">
				<li class="fieldcontain">
					<span id="file-label" class="property-label"><g:message code="ocorrencia.file.label" default="File" /></span>
					<span class="property-value" aria-labelledby="filename-label">
					<g:link controller="ocorrencia" action="showPayload" id= "${ocorrenciaInstance.id}">Download</g:link><br>
					</span>
				</li>				
				</g:if>

				<g:if test="${ocorrenciaInstance?.filename}">
				<li class="fieldcontain">
					<span id="filename-label" class="property-label"><g:message code="ocorrencia.filename.label" default="Filename" /></span>
					
						<span class="property-value" aria-labelledby="filename-label"><g:fieldValue bean="${ocorrenciaInstance}" field="filename"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ocorrenciaInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="ocorrencia.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${ocorrenciaInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ocorrenciaInstance?.data}">
				<li class="fieldcontain">
					<span id="data-label" class="property-label"><g:message code="ocorrencia.data.label" default="Data" /></span>
					
						<span class="property-value" aria-labelledby="data-label"><g:formatDate date="${ocorrenciaInstance?.data}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ocorrenciaInstance?.latitude}">
				<li class="fieldcontain">
					<span id="latitude-label" class="property-label"><g:message code="ocorrencia.latitude.label" default="Latitude" /></span>
					
						<span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${ocorrenciaInstance}" field="latitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ocorrenciaInstance?.longitude}">
				<li class="fieldcontain">
					<span id="longitude-label" class="property-label"><g:message code="ocorrencia.longitude.label" default="Longitude" /></span>
					
						<span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${ocorrenciaInstance}" field="longitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ocorrenciaInstance?.paciente}">
				<li class="fieldcontain">
					<span id="paciente-label" class="property-label"><g:message code="ocorrencia.paciente.label" default="Paciente" /></span>
					
						<span class="property-value" aria-labelledby="paciente-label"><g:link controller="paciente" action="show" id="${ocorrenciaInstance?.paciente?.email}">${ocorrenciaInstance?.paciente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ocorrenciaInstance?.id}" />
					<g:link class="edit" action="edit" id="${ocorrenciaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
