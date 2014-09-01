package br.org



import org.junit.*
import grails.test.mixin.*

@TestFor(InstituicaoController)
@Mock(Instituicao)
class InstituicaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/instituicao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.instituicaoInstanceList.size() == 0
        assert model.instituicaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.instituicaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.instituicaoInstance != null
        assert view == '/instituicao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/instituicao/show/1'
        assert controller.flash.message != null
        assert Instituicao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/instituicao/list'

        populateValidParams(params)
        def instituicao = new Instituicao(params)

        assert instituicao.save() != null

        params.id = instituicao.id

        def model = controller.show()

        assert model.instituicaoInstance == instituicao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/instituicao/list'

        populateValidParams(params)
        def instituicao = new Instituicao(params)

        assert instituicao.save() != null

        params.id = instituicao.id

        def model = controller.edit()

        assert model.instituicaoInstance == instituicao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/instituicao/list'

        response.reset()

        populateValidParams(params)
        def instituicao = new Instituicao(params)

        assert instituicao.save() != null

        // test invalid parameters in update
        params.id = instituicao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/instituicao/edit"
        assert model.instituicaoInstance != null

        instituicao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/instituicao/show/$instituicao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        instituicao.clearErrors()

        populateValidParams(params)
        params.id = instituicao.id
        params.version = -1
        controller.update()

        assert view == "/instituicao/edit"
        assert model.instituicaoInstance != null
        assert model.instituicaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/instituicao/list'

        response.reset()

        populateValidParams(params)
        def instituicao = new Instituicao(params)

        assert instituicao.save() != null
        assert Instituicao.count() == 1

        params.id = instituicao.id

        controller.delete()

        assert Instituicao.count() == 0
        assert Instituicao.get(instituicao.id) == null
        assert response.redirectedUrl == '/instituicao/list'
    }
}
