<%@ page import="br.org.Pessoa" %>



<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="pessoa.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${pessoaInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="pessoa.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${pessoaInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'telefone', 'error')} required">
	<label for="telefone">
		<g:message code="pessoa.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefone" required="" value="${pessoaInstance?.telefone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="pessoa.senha.label" default="Senha" />
		
	</label>
	<g:textField name="senha" value="${pessoaInstance?.senha}"/>
</div>

