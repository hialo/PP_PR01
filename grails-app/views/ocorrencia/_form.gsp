<%@ page import="br.org.Ocorrencia" %>



<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'file', 'error')} ">
	<label for="file">
		<g:message code="ocorrencia.file.label" default="File" />
		
	</label>
	<input type="file" id="file" name="file" />
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'filename', 'error')} ">
	<label for="filename">
		<g:message code="ocorrencia.filename.label" default="Filename" />
		
	</label>
	<g:textField name="filename" value="${ocorrenciaInstance?.filename}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="ocorrencia.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${ocorrenciaInstance.constraints.status.inList}" required="" value="${ocorrenciaInstance?.status}" valueMessagePrefix="ocorrencia.status"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'data', 'error')} required">
	<label for="data">
		<g:message code="ocorrencia.data.label" default="Data" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="data" precision="day"  value="${ocorrenciaInstance?.data}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'latitude', 'error')} ">
	<label for="latitude">
		<g:message code="ocorrencia.latitude.label" default="Latitude" />
		
	</label>
	<g:textField name="latitude" value="${ocorrenciaInstance?.latitude}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'longitude', 'error')} ">
	<label for="longitude">
		<g:message code="ocorrencia.longitude.label" default="Longitude" />
		
	</label>
	<g:textField name="longitude" value="${ocorrenciaInstance?.longitude}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ocorrenciaInstance, field: 'paciente', 'error')} required">
	<label for="paciente">
		<g:message code="ocorrencia.paciente.label" default="Paciente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="paciente" name="paciente.id" from="${br.org.Paciente.list()}" optionKey="id" required="" value="${ocorrenciaInstance?.paciente?.id}" class="many-to-one"/>
</div>

