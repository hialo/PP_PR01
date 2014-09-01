package br.org



import org.junit.*
import grails.test.mixin.*

@TestFor(TecnicoController)
@Mock(Tecnico)
class TecnicoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tecnico/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tecnicoInstanceList.size() == 0
        assert model.tecnicoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tecnicoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tecnicoInstance != null
        assert view == '/tecnico/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tecnico/show/1'
        assert controller.flash.message != null
        assert Tecnico.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnico/list'

        populateValidParams(params)
        def tecnico = new Tecnico(params)

        assert tecnico.save() != null

        params.id = tecnico.id

        def model = controller.show()

        assert model.tecnicoInstance == tecnico
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnico/list'

        populateValidParams(params)
        def tecnico = new Tecnico(params)

        assert tecnico.save() != null

        params.id = tecnico.id

        def model = controller.edit()

        assert model.tecnicoInstance == tecnico
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tecnico/list'

        response.reset()

        populateValidParams(params)
        def tecnico = new Tecnico(params)

        assert tecnico.save() != null

        // test invalid parameters in update
        params.id = tecnico.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tecnico/edit"
        assert model.tecnicoInstance != null

        tecnico.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tecnico/show/$tecnico.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tecnico.clearErrors()

        populateValidParams(params)
        params.id = tecnico.id
        params.version = -1
        controller.update()

        assert view == "/tecnico/edit"
        assert model.tecnicoInstance != null
        assert model.tecnicoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tecnico/list'

        response.reset()

        populateValidParams(params)
        def tecnico = new Tecnico(params)

        assert tecnico.save() != null
        assert Tecnico.count() == 1

        params.id = tecnico.id

        controller.delete()

        assert Tecnico.count() == 0
        assert Tecnico.get(tecnico.id) == null
        assert response.redirectedUrl == '/tecnico/list'
    }
}
