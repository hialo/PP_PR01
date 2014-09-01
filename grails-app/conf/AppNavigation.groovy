navigation = {
    // Declare the "app" scope, used by default in tags
    app {
     
        // A nav item pointing to HomeController, using the default action
        home(url: 'http://localhost:8081/sahh/')

        // Items pointing to ContentController, using the specific action
        ocorrencia(controller:'ocorrencia', action:'list, create')
        paciente(controller:'paciente', action:'list, create')
        medico(controller:'medico', action:'list, create')
        tecnico(controller:'tecnico', action:'list, create')
        status(controller:'status', action:'list, create')
        contato(controller:'contato', action:'list, create')
        instituicao(controller:'instituicao', action:'list, create')
    }
}