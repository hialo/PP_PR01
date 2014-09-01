package br.org

class Medico extends Pessoa{

	String crm

	static hasMany = [pacientes:Paciente]

    static constraints = {
    }
}
