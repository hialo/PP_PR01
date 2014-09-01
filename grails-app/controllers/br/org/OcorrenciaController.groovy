package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class OcorrenciaController {

    static allowedMethods = [list:'GET', listbyid:'GET',
        show:'GET',
        edit:['GET', 'POST'],
        save:'POST',
        update:['POST','PUT'],
        delete:['POST','DELETE']
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 50, 200)
        def list = Ocorrencia.list(params)
        def listObject = [ocorrenciaInstanceList: list, ocorrenciaInstanceTotal: Ocorrencia.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def listbyid() {
        params.max = Math.min(params.max ? params.int('max') : 50, 200)
        def paciente = Paciente.get(params.id)
        def list = Ocorrencia.findAllByPaciente(paciente)

        if (!list) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'medico.label', default: 'Medico'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        def listObject = [ocorrenciaInstanceList: list, ocorrenciaInstanceTotal: Ocorrencia.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [ocorrenciaInstance: new Ocorrencia(params)]
    }

    def save() {
        
        def ocorrenciaInstance = new Ocorrencia(params)
        def uploadedFile = request.getFile("file")

        ocorrenciaInstance.file = uploadedFile.getBytes()
        ocorrenciaInstance.filename = uploadedFile.getOriginalFilename()


        if (!ocorrenciaInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [ocorrenciaInstance: ocorrenciaInstance])}
                json {
                    response.status = 403
                    render ocorrenciaInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render ocorrenciaInstance.errors as XML
                }
            }
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), ocorrenciaInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: ocorrenciaInstance.id)
            }
            json {
                response.status = 201
                render ocorrenciaInstance as JSON
            }
            xml {
                response.status = 201
                render ocorrenciaInstance.id
            }
        }
    }

    def showPayload() {
        def ocorrenciaInstance = Ocorrencia.get(params.id)
        def bytes = ocorrenciaInstance.file
    
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${ocorrenciaInstance.filename}")
        response.outputStream << bytes
        //response.outputStream.flush()
        return
    }

    def show() {
        def ocorrenciaInstance = Ocorrencia.get(params.id)
        if (!ocorrenciaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [ocorrenciaInstance: ocorrenciaInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def ocorrenciaInstance = Ocorrencia.get(params.id)
        if (!ocorrenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
            redirect(action: "list")
            return
        }
        [ocorrenciaInstance: ocorrenciaInstance]
    }

    def update() {
        def ocorrenciaInstance = Ocorrencia.get(params.id)
        def bytes = ocorrenciaInstance.file

        if (!ocorrenciaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (ocorrenciaInstance.version > version) {
                ocorrenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ocorrencia.label', default: 'Ocorrencia')] as Object[],
                          "Another user has updated this Ocorrencia while you were editing")
                withFormat {
                    html {render(view: "edit", model: [ocorrenciaInstance: ocorrenciaInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }
        
        ocorrenciaInstance.properties = params

        if (!ocorrenciaInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [ocorrenciaInstance: ocorrenciaInstance])}
                json {
                    response.status = 403
                    render ocorrenciaInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render ocorrenciaInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), ocorrenciaInstance.id])
                redirect(action: "show", id: ocorrenciaInstance.id)
            }
            json {
                response.status = 204
                render ocorrenciaInstance as JSON
            }
            xml {
                response.status = 204
                render ocorrenciaInstance as XML
            }
        }
    }

    def map = {
        [ocorrenciaList: Ocorrencia.list()]
    }


    def delete() {
        def ocorrenciaInstance = Ocorrencia.get(params.id)
        if (!ocorrenciaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            ocorrenciaInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
                    redirect(action: "list")
                }
                json {
                    response.status = 204
                    render ''
                }
                xml {
                    response.status = 204
                    render ''
                }
            }
        }
        catch (DataIntegrityViolationException e) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ocorrencia.label', default: 'Ocorrencia'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}