package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class MedicoController {

    static allowedMethods = [list:'GET', listallPatient:'GET',
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
        def list = Medico.list(params)
        def listObject = [medicoInstanceList: list, medicoInstanceTotal: Medico.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def listallPatient(){
        def medicoInstance = Medico.get(params.id)
        def pacientes = Paciente.getAll(medicoInstance.pacientes.id)

        if (!pacientes) {
            withFormat {
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        def listObject = [pacienteInstanceList: pacientes, pacienteInstanceTotal: pacientes.size()]
        withFormat {
            json { render list as JSON }
            xml { render listObject as XML }
        }

    }

    def create() {
        [medicoInstance: new Medico(params)]
    }

    def save() {
        def medicoInstance = new Medico(params)
        if (!medicoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [medicoInstance: medicoInstance])}
                json {
                    response.status = 403
                    render medicoInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render medicoInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'medico.label', default: 'Medico'), medicoInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: medicoInstance.email)
            }
            json {
                response.status = 201
                render medicoInstance as JSON
            }
            xml {
                response.status = 201
                render medicoInstance.id
            }
        }
    }

    def show() {
        def medicoInstance = Medico.findByEmail(params.id)
        if (!medicoInstance) {
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
        def object = [medicoInstance: medicoInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def medicoInstance = Medico.get(params.id)
        if (!medicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'medico.label', default: 'Medico'), params.id])
            redirect(action: "list")
            return
        }
        [medicoInstance: medicoInstance]
    }

    def update() {
        def medicoInstance = Medico.get(params.id)
        if (!medicoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'medico.label', default: 'Medico'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (medicoInstance.version > version) {
                medicoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'medico.label', default: 'Medico')] as Object[],
                          "Another user has updated this Medico while you were editing")
                withFormat {
                    html {render(view: "edit", model: [medicoInstance: medicoInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        medicoInstance.properties = params

        if (!medicoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [medicoInstance: medicoInstance])}
                json {
                    response.status = 403
                    render medicoInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render medicoInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'medico.label', default: 'Medico'), medicoInstance.id])
                redirect(action: "show", id: medicoInstance.id)
            }
            json {
                response.status = 204
                render medicoInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def medicoInstance = Medico.get(params.id)
        if (!medicoInstance) {
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
        try {
            medicoInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'medico.label', default: 'Medico'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'medico.label', default: 'Medico'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}