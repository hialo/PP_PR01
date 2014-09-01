
<%@ page import="br.org.Tecnico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tecnico.label', default: 'Tecnico')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tecnico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tecnico" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'tecnico.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'tecnico.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'tecnico.telefone.label', default: 'Telefone')}" />
					
						<th><g:message code="tecnico.instituicao.label" default="Instituicao" /></th>
					
						<g:sortableColumn property="matricula" title="${message(code: 'tecnico.matricula.label', default: 'Matricula')}" />
										
					</tr>
				</thead>
				<tbody>
				<g:each in="${tecnicoInstanceList}" status="i" var="tecnicoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tecnicoInstance.email}">${fieldValue(bean: tecnicoInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: tecnicoInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: tecnicoInstance, field: "telefone")}</td>
					
						<td>${fieldValue(bean: tecnicoInstance, field: "instituicao")}</td>
					
						<td>${fieldValue(bean: tecnicoInstance, field: "matricula")}</td>
										
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tecnicoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
