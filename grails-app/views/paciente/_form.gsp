<%@ page import="br.org.Paciente" %>



<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="paciente.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${pacienteInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="paciente.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${pacienteInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'telefone', 'error')} required">
	<label for="telefone">
		<g:message code="paciente.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefone" required="" value="${pacienteInstance?.telefone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'sexo', 'error')} required">
	<label for="sexo">
		<g:message code="paciente.sexo.label" default="Sexo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sexo" from="${pacienteInstance.constraints.sexo.inList}" required="" value="${pacienteInstance?.sexo}" valueMessagePrefix="paciente.sexo"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'nascimento', 'error')} required">
	<label for="nascimento">
		<g:message code="paciente.nascimento.label" default="Nascimento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="nascimento" precision="day"  value="${pacienteInstance?.nascimento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pacienteInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="paciente.senha.label" default="Senha" />
		
	</label>
	<g:field type="password" name="senha" value="${pacienteInstance?.senha}"/>
</div>

