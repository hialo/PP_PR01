package br.org



import org.junit.*
import grails.test.mixin.*

@TestFor(ContatoController)
@Mock(Contato)
class ContatoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contato/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contatoInstanceList.size() == 0
        assert model.contatoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contatoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contatoInstance != null
        assert view == '/contato/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contato/show/1'
        assert controller.flash.message != null
        assert Contato.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contato/list'

        populateValidParams(params)
        def contato = new Contato(params)

        assert contato.save() != null

        params.id = contato.id

        def model = controller.show()

        assert model.contatoInstance == contato
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contato/list'

        populateValidParams(params)
        def contato = new Contato(params)

        assert contato.save() != null

        params.id = contato.id

        def model = controller.edit()

        assert model.contatoInstance == contato
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contato/list'

        response.reset()

        populateValidParams(params)
        def contato = new Contato(params)

        assert contato.save() != null

        // test invalid parameters in update
        params.id = contato.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contato/edit"
        assert model.contatoInstance != null

        contato.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contato/show/$contato.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contato.clearErrors()

        populateValidParams(params)
        params.id = contato.id
        params.version = -1
        controller.update()

        assert view == "/contato/edit"
        assert model.contatoInstance != null
        assert model.contatoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contato/list'

        response.reset()

        populateValidParams(params)
        def contato = new Contato(params)

        assert contato.save() != null
        assert Contato.count() == 1

        params.id = contato.id

        controller.delete()

        assert Contato.count() == 0
        assert Contato.get(contato.id) == null
        assert response.redirectedUrl == '/contato/list'
    }
}
