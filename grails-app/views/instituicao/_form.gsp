<%@ page import="br.org.Instituicao" %>



<div class="fieldcontain ${hasErrors(bean: instituicaoInstance, field: 'endereco', 'error')} ">
	<label for="endereco">
		<g:message code="instituicao.endereco.label" default="Endereco" />
		
	</label>
	<g:textField name="endereco" value="${instituicaoInstance?.endereco}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instituicaoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="instituicao.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${instituicaoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instituicaoInstance, field: 'telefone', 'error')} ">
	<label for="telefone">
		<g:message code="instituicao.telefone.label" default="Telefone" />
		
	</label>
	<g:textField name="telefone" value="${instituicaoInstance?.telefone}"/>
</div>

