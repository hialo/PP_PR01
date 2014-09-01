package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class TecnicoController {

    static allowedMethods = [list:'GET',
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
        def list = Tecnico.list(params)
        def listObject = [tecnicoInstanceList: list, tecnicoInstanceTotal: Tecnico.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [tecnicoInstance: new Tecnico(params)]
    }

    def save() {
        def tecnicoInstance = new Tecnico(params)
        if (!tecnicoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [tecnicoInstance: tecnicoInstance])}
                json {
                    response.status = 403
                    render tecnicoInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render tecnicoInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), tecnicoInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: tecnicoInstance.email)
            }
            json {
                response.status = 201
                render tecnicoInstance as JSON
            }
            xml {
                response.status = 201
                render tecnicoInstance.id
            }
        }
    }

    def show() {
        def tecnicoInstance = Tecnico.findByEmail(params.id)
        if (!tecnicoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [tecnicoInstance: tecnicoInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def tecnicoInstance = Tecnico.get(params.id)
        if (!tecnicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
            redirect(action: "list")
            return
        }
        [tecnicoInstance: tecnicoInstance]
    }

    def update() {
        def tecnicoInstance = Tecnico.get(params.id)
        if (!tecnicoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (tecnicoInstance.version > version) {
                tecnicoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tecnico.label', default: 'Tecnico')] as Object[],
                          "Another user has updated this Tecnico while you were editing")
                withFormat {
                    html {render(view: "edit", model: [tecnicoInstance: tecnicoInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        tecnicoInstance.properties = params

        if (!tecnicoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [tecnicoInstance: tecnicoInstance])}
                json {
                    response.status = 403
                    render tecnicoInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render tecnicoInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), tecnicoInstance.id])
                redirect(action: "show", id: tecnicoInstance.id)
            }
            json {
                response.status = 204
                render tecnicoInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def tecnicoInstance = Tecnico.get(params.id)
        if (!tecnicoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            tecnicoInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tecnico.label', default: 'Tecnico'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}