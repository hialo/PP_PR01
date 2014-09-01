
<%@ page import="br.org.Paciente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-paciente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-paciente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'paciente.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'paciente.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'paciente.telefone.label', default: 'Telefone')}" />
					
						<g:sortableColumn property="sexo" title="${message(code: 'paciente.sexo.label', default: 'Sexo')}" />
					
						<g:sortableColumn property="nascimento" title="${message(code: 'paciente.nascimento.label', default: 'Nascimento')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pacienteInstanceList}" status="i" var="pacienteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pacienteInstance.email}">${fieldValue(bean: pacienteInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: pacienteInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: pacienteInstance, field: "telefone")}</td>
					
						<td>${fieldValue(bean: pacienteInstance, field: "sexo")}</td>
					
						<td><g:formatDate date="${pacienteInstance.nascimento}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pacienteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
