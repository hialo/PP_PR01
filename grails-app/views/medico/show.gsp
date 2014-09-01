
<%@ page import="br.org.Medico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'medico.label', default: 'Medico')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-medico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-medico" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list medico">
			
				<g:if test="${medicoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="medico.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${medicoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${medicoInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="medico.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${medicoInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${medicoInstance?.telefone}">
				<li class="fieldcontain">
					<span id="telefone-label" class="property-label"><g:message code="medico.telefone.label" default="Telefone" /></span>
					
						<span class="property-value" aria-labelledby="telefone-label"><g:fieldValue bean="${medicoInstance}" field="telefone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${medicoInstance?.crm}">
				<li class="fieldcontain">
					<span id="crm-label" class="property-label"><g:message code="medico.crm.label" default="Crm" /></span>
					
						<span class="property-value" aria-labelledby="crm-label"><g:fieldValue bean="${medicoInstance}" field="crm"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${medicoInstance?.pacientes}">
				<li class="fieldcontain">
					<span id="pacientes-label" class="property-label"><g:message code="medico.pacientes.label" default="Pacientes" /></span>
					
						<g:each in="${medicoInstance.pacientes}" var="p">
						<span class="property-value" aria-labelledby="pacientes-label"><g:link controller="paciente" action="show" id="${p.email}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${medicoInstance?.id}" />
					<g:link class="edit" action="edit" id="${medicoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
