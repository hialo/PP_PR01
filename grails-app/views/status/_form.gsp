<%@ page import="br.org.Status" %>



<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="status.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${statusInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="status.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${statusInstance?.nome}"/>
</div>

