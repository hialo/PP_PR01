package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class StatusController {

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
        def list = Status.list(params)
        def listObject = [statusInstanceList: list, statusInstanceTotal: Status.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [statusInstance: new Status(params)]
    }

    def save() {
        def statusInstance = new Status(params)
        if (!statusInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [statusInstance: statusInstance])}
                json {
                    response.status = 403
                    render statusInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render statusInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: statusInstance.id)
            }
            json {
                response.status = 201
                render statusInstance as JSON
            }
            xml {
                response.status = 201
                render statusInstance.id
            }
        }
    }

    def show() {
        def statusInstance = Status.get(params.id)
        if (!statusInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [statusInstance: statusInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def statusInstance = Status.get(params.id)
        if (!statusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
            redirect(action: "list")
            return
        }
        [statusInstance: statusInstance]
    }

    def update() {
        def statusInstance = Status.get(params.id)
        if (!statusInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (statusInstance.version > version) {
                statusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'status.label', default: 'Status')] as Object[],
                          "Another user has updated this Status while you were editing")
                withFormat {
                    html {render(view: "edit", model: [statusInstance: statusInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        statusInstance.properties = params

        if (!statusInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [statusInstance: statusInstance])}
                json {
                    response.status = 403
                    render statusInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render statusInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
                redirect(action: "show", id: statusInstance.id)
            }
            json {
                response.status = 204
                render statusInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def statusInstance = Status.get(params.id)
        if (!statusInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            statusInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'status.label', default: 'Status'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}