package br.org



import org.junit.*
import grails.test.mixin.*

@TestFor(OcorrenciaController)
@Mock(Ocorrencia)
class OcorrenciaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ocorrencia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ocorrenciaInstanceList.size() == 0
        assert model.ocorrenciaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ocorrenciaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ocorrenciaInstance != null
        assert view == '/ocorrencia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ocorrencia/show/1'
        assert controller.flash.message != null
        assert Ocorrencia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ocorrencia/list'

        populateValidParams(params)
        def ocorrencia = new Ocorrencia(params)

        assert ocorrencia.save() != null

        params.id = ocorrencia.id

        def model = controller.show()

        assert model.ocorrenciaInstance == ocorrencia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ocorrencia/list'

        populateValidParams(params)
        def ocorrencia = new Ocorrencia(params)

        assert ocorrencia.save() != null

        params.id = ocorrencia.id

        def model = controller.edit()

        assert model.ocorrenciaInstance == ocorrencia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ocorrencia/list'

        response.reset()

        populateValidParams(params)
        def ocorrencia = new Ocorrencia(params)

        assert ocorrencia.save() != null

        // test invalid parameters in update
        params.id = ocorrencia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ocorrencia/edit"
        assert model.ocorrenciaInstance != null

        ocorrencia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ocorrencia/show/$ocorrencia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ocorrencia.clearErrors()

        populateValidParams(params)
        params.id = ocorrencia.id
        params.version = -1
        controller.update()

        assert view == "/ocorrencia/edit"
        assert model.ocorrenciaInstance != null
        assert model.ocorrenciaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ocorrencia/list'

        response.reset()

        populateValidParams(params)
        def ocorrencia = new Ocorrencia(params)

        assert ocorrencia.save() != null
        assert Ocorrencia.count() == 1

        params.id = ocorrencia.id

        controller.delete()

        assert Ocorrencia.count() == 0
        assert Ocorrencia.get(ocorrencia.id) == null
        assert response.redirectedUrl == '/ocorrencia/list'
    }
}
