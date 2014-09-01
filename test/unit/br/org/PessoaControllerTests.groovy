package br.org



import org.junit.*
import grails.test.mixin.*

@TestFor(PessoaController)
@Mock(Pessoa)
class PessoaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pessoa/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pessoaInstanceList.size() == 0
        assert model.pessoaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pessoaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pessoaInstance != null
        assert view == '/pessoa/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pessoa/show/1'
        assert controller.flash.message != null
        assert Pessoa.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoa/list'

        populateValidParams(params)
        def pessoa = new Pessoa(params)

        assert pessoa.save() != null

        params.id = pessoa.id

        def model = controller.show()

        assert model.pessoaInstance == pessoa
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoa/list'

        populateValidParams(params)
        def pessoa = new Pessoa(params)

        assert pessoa.save() != null

        params.id = pessoa.id

        def model = controller.edit()

        assert model.pessoaInstance == pessoa
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoa/list'

        response.reset()

        populateValidParams(params)
        def pessoa = new Pessoa(params)

        assert pessoa.save() != null

        // test invalid parameters in update
        params.id = pessoa.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pessoa/edit"
        assert model.pessoaInstance != null

        pessoa.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pessoa/show/$pessoa.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pessoa.clearErrors()

        populateValidParams(params)
        params.id = pessoa.id
        params.version = -1
        controller.update()

        assert view == "/pessoa/edit"
        assert model.pessoaInstance != null
        assert model.pessoaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pessoa/list'

        response.reset()

        populateValidParams(params)
        def pessoa = new Pessoa(params)

        assert pessoa.save() != null
        assert Pessoa.count() == 1

        params.id = pessoa.id

        controller.delete()

        assert Pessoa.count() == 0
        assert Pessoa.get(pessoa.id) == null
        assert response.redirectedUrl == '/pessoa/list'
    }
}
