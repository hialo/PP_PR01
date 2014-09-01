<%@ page import="br.org.Tecnico" %>



<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="tecnico.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${tecnicoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="tecnico.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${tecnicoInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'telefone', 'error')} required">
	<label for="telefone">
		<g:message code="tecnico.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefone" required="" value="${tecnicoInstance?.telefone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'instituicao', 'error')} required">
	<label for="instituicao">
		<g:message code="tecnico.instituicao.label" default="Instituicao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="instituicao" name="instituicao.id" from="${br.org.Instituicao.list()}" optionKey="id" required="" value="${tecnicoInstance?.instituicao?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'matricula', 'error')} ">
	<label for="matricula">
		<g:message code="tecnico.matricula.label" default="Matricula" />
		
	</label>
	<g:textField name="matricula" value="${tecnicoInstance?.matricula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tecnicoInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="tecnico.senha.label" default="Senha" />
		
	</label>
	<g:field type="password" name="senha" value="${tecnicoInstance?.senha}"/>
</div>

