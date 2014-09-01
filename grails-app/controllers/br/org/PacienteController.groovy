package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class PacienteController {

    static allowedMethods = [list:'GET', showbyid:'GET',
        show:'GET',
        EKG:'GET',
        edit:['GET', 'POST'],
        save:'POST',
        update:['POST','PUT'],
        delete:['POST','DELETE']
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def EKG() {
        
    }


    def list() {
        params.max = Math.min(params.max ? params.int('max') : 50, 200)
        def list = Paciente.list(params)
        def listObject = [pacienteInstanceList: list, pacienteInstanceTotal: Paciente.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [pacienteInstance: new Paciente(params)]
    }

    def save() {
        def pacienteInstance = new Paciente(params)
        if (!pacienteInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [pacienteInstance: pacienteInstance])}
                json {
                    response.status = 403
                    render pacienteInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render pacienteInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: pacienteInstance.email)
            }
            json {
                response.status = 201
                render pacienteInstance as JSON
            }
            xml {
                response.status = 201
                render pacienteInstance.id
            }
        }
    }

    def show() {
        def pacienteInstance = Paciente.findByEmail(params.id)
        if (!pacienteInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [pacienteInstance: pacienteInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def showbyid() {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [pacienteInstance: pacienteInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
            redirect(action: "list")
            return
        }
        [pacienteInstance: pacienteInstance]
    }

    def update() {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (pacienteInstance.version > version) {
                pacienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'paciente.label', default: 'Paciente')] as Object[],
                          "Another user has updated this Paciente while you were editing")
                withFormat {
                    html {render(view: "edit", model: [pacienteInstance: pacienteInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        pacienteInstance.properties = params

        if (!pacienteInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [pacienteInstance: pacienteInstance])}
                json {
                    response.status = 403
                    render pacienteInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render pacienteInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])
                redirect(action: "show", id: pacienteInstance.id)
            }
            json {
                response.status = 204
                render pacienteInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            pacienteInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}