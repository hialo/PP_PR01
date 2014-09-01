package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class ContatoController {

    static allowedMethods = [list:'GET', listbyid: 'GET',
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
        def list = Contato.list(params)
        def listObject = [contatoInstanceList: list, contatoInstanceTotal: Contato.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def listbyid() {
        params.max = Math.min(params.max ? params.int('max') : 50, 200)
        def paciente = Paciente.get(params.id)
        def list = Contato.findAllByPaciente(paciente)

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

        def listObject = [contatoInstanceList: list, contatoInstanceTotal: Contato.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [contatoInstance: new Contato(params)]
    }

    def save() {
        def contatoInstance = new Contato(params)
        if (!contatoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [contatoInstance: contatoInstance])}
                json {
                    response.status = 403
                    render contatoInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render contatoInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'contato.label', default: 'Contato'), contatoInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: contatoInstance.id)
            }
            json {
                response.status = 201
                render contatoInstance as JSON
            }
            xml {
                response.status = 201
                render contatoInstance.id
            }
        }
    }

    def show() {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [contatoInstance: contatoInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
            redirect(action: "list")
            return
        }
        [contatoInstance: contatoInstance]
    }

    def update() {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (contatoInstance.version > version) {
                contatoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contato.label', default: 'Contato')] as Object[],
                          "Another user has updated this Contato while you were editing")
                withFormat {
                    html {render(view: "edit", model: [contatoInstance: contatoInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        contatoInstance.properties = params

        if (!contatoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [contatoInstance: contatoInstance])}
                json {
                    response.status = 403
                    render contatoInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render contatoInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contato.label', default: 'Contato'), contatoInstance.id])
                redirect(action: "show", id: contatoInstance.id)
            }
            json {
                response.status = 204
                render contatoInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            contatoInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}