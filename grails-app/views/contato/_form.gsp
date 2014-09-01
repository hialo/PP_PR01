<%@ page import="br.org.Contato" %>



<div class="fieldcontain ${hasErrors(bean: contatoInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="contato.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${contatoInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contatoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="contato.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${contatoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contatoInstance, field: 'paciente', 'error')} required">
	<label for="paciente">
		<g:message code="contato.paciente.label" default="Paciente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="paciente" name="paciente.id" from="${br.org.Paciente.list()}" optionKey="id" required="" value="${contatoInstance?.paciente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contatoInstance, field: 'telefone', 'error')} ">
	<label for="telefone">
		<g:message code="contato.telefone.label" default="Telefone" />
		
	</label>
	<g:textField name="telefone" value="${contatoInstance?.telefone}"/>
</div>

