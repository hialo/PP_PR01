
<%@ page import="br.org.Medico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'medico.label', default: 'Medico')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-medico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-medico" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'medico.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'medico.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'medico.telefone.label', default: 'Telefone')}" />
					
						<g:sortableColumn property="crm" title="${message(code: 'medico.crm.label', default: 'Crm')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${medicoInstanceList}" status="i" var="medicoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${medicoInstance.email}">${fieldValue(bean: medicoInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: medicoInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: medicoInstance, field: "telefone")}</td>
					
						<td>${fieldValue(bean: medicoInstance, field: "crm")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${medicoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
