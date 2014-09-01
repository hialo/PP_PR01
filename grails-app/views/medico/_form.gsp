<%@ page import="br.org.Medico" %>



<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="medico.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${medicoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="medico.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${medicoInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'telefone', 'error')} required">
	<label for="telefone">
		<g:message code="medico.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefone" required="" value="${medicoInstance?.telefone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'crm', 'error')} ">
	<label for="crm">
		<g:message code="medico.crm.label" default="Crm" />
		
	</label>
	<g:textField name="crm" value="${medicoInstance?.crm}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'pacientes', 'error')} ">
	<label for="pacientes">
		<g:message code="medico.pacientes.label" default="Pacientes" />
		
	</label>
	<g:select name="pacientes" from="${br.org.Paciente.list()}" multiple="multiple" optionKey="id" size="5" value="${medicoInstance?.pacientes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: medicoInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="medico.senha.label" default="Senha" />
		
	</label>
	<g:field type="password" name="senha" value="${medicoInstance?.senha}"/>
</div>

