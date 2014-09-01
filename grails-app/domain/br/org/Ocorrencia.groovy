package br.org

class Ocorrencia {

	Date data = new Date()
	String latitude
	String longitude
	String status
	byte[] file
	String filename

	static belongsTo = [paciente:Paciente]

    static constraints = {
    	file(nullable:true, maxSize:  5 * 1024 * 1024)
    	filename(blank:true, nullable:true)
    	status(blank: false, inList: ["Aberta", "Em atendimento","Finalizada"])
    }
}
