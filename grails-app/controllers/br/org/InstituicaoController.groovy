package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class InstituicaoController {

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
        def list = Instituicao.list(params)
        def listObject = [instituicaoInstanceList: list, instituicaoInstanceTotal: Instituicao.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [instituicaoInstance: new Instituicao(params)]
    }

    def save() {
        def instituicaoInstance = new Instituicao(params)
        if (!instituicaoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [instituicaoInstance: instituicaoInstance])}
                json {
                    response.status = 403
                    render instituicaoInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render instituicaoInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), instituicaoInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: instituicaoInstance.id)
            }
            json {
                response.status = 201
                render instituicaoInstance as JSON
            }
            xml {
                response.status = 201
                render instituicaoInstance.id
            }
        }
    }

    def show() {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [instituicaoInstance: instituicaoInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
            redirect(action: "list")
            return
        }
        [instituicaoInstance: instituicaoInstance]
    }

    def update() {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (instituicaoInstance.version > version) {
                instituicaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'instituicao.label', default: 'Instituicao')] as Object[],
                          "Another user has updated this Instituicao while you were editing")
                withFormat {
                    html {render(view: "edit", model: [instituicaoInstance: instituicaoInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        instituicaoInstance.properties = params

        if (!instituicaoInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [instituicaoInstance: instituicaoInstance])}
                json {
                    response.status = 403
                    render instituicaoInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render instituicaoInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), instituicaoInstance.id])
                redirect(action: "show", id: instituicaoInstance.id)
            }
            json {
                response.status = 204
                render instituicaoInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def instituicaoInstance = Instituicao.get(params.id)
        if (!instituicaoInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            instituicaoInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'instituicao.label', default: 'Instituicao'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}