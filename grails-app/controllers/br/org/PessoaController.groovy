package br.org

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.XML
import grails.converters.JSON

class PessoaController {

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
        def list = Pessoa.list(params)
        def listObject = [pessoaInstanceList: list, pessoaInstanceTotal: Pessoa.count()]
        withFormat {
            html listObject
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }

    def create() {
        [pessoaInstance: new Pessoa(params)]
    }

    def save() {
        def pessoaInstance = new Pessoa(params)
        if (!pessoaInstance.save(flush: true)) {
            withFormat {
                html {render(view: "create", model: [pessoaInstance: pessoaInstance])}
                json {
                    response.status = 403
                    render pessoaInstance.errors as JSON
                }
                xml {
                    response.status =403
                    render pessoaInstance.errors as XML
                }
            }
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoaInstance.id])
        withFormat {
            html {
                redirect(action: "show", id: pessoaInstance.id)
            }
            json {
                response.status = 201
                render pessoaInstance as JSON
            }
            xml {
                response.status = 201
                render pessoaInstance.id
            }
        }
    }

    def show() {
        def pessoaInstance = Pessoa.get(params.id)
        if (!pessoaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        def object = [pessoaInstance: pessoaInstance]
        withFormat {
            html {object}
            json { render object as JSON }
            xml { render object as XML }
        }
    }

    def edit() {
        def pessoaInstance = Pessoa.get(params.id)
        if (!pessoaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
            redirect(action: "list")
            return
        }
        [pessoaInstance: pessoaInstance]
    }

    def update() {
        def pessoaInstance = Pessoa.get(params.id)
        if (!pessoaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                    redirect(action:"list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (pessoaInstance.version > version) {
                pessoaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pessoa.label', default: 'Pessoa')] as Object[],
                          "Another user has updated this Pessoa while you were editing")
                withFormat {
                    html {render(view: "edit", model: [pessoaInstance: pessoaInstance])}
                    json { response.sendError(409) }
                    xml { response.sendError(409) }
                }
                return
            }
        }

        pessoaInstance.properties = params

        if (!pessoaInstance.save(flush: true)) {
            withFormat {
                html {render(view: "edit", model: [pessoaInstance: pessoaInstance])}
                json {
                    response.status = 403
                    render pessoaInstance.errors as JSON
                }
                xml {
                    response.status = 403
                    render pessoaInstance.errors as XML
                }
            }
            return
        }
        withFormat {
            html {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoaInstance.id])
                redirect(action: "show", id: pessoaInstance.id)
            }
            json {
                response.status = 204
                render pessoaInstance as JSON
            }
            xml {
                response.status = 204
                render ''
            }
        }
    }

    def delete() {
        def pessoaInstance = Pessoa.get(params.id)
        if (!pessoaInstance) {
            withFormat {
                html {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                    redirect(action: "list")
                }
                json { response.sendError(404) }
                xml { response.sendError(404) }
            }
            return
        }
        try {
            pessoaInstance.delete(flush: true)
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
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
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                    redirect(action: "show", id: params.id)
                }
                json { response.sendError(500) }
                xml { response.sendError(500) }
            }
        }
    }

}