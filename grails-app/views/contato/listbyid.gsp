
<%@ page import="br.org.Contato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contato.label', default: 'Contato')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-contato" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-contato" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="email" title="${message(code: 'contato.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'contato.nome.label', default: 'Nome')}" />
					
						<th><g:message code="contato.paciente.label" default="Paciente" /></th>
					
						<g:sortableColumn property="telefone" title="${message(code: 'contato.telefone.label', default: 'Telefone')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contatoInstanceList}" status="i" var="contatoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contatoInstance.id}">${fieldValue(bean: contatoInstance, field: "email")}</g:link></td>
					
						<td>${fieldValue(bean: contatoInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: contatoInstance, field: "paciente")}</td>
					
						<td>${fieldValue(bean: contatoInstance, field: "telefone")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contatoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
