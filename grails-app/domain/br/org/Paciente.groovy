package br.org

class Paciente extends Pessoa{

	String sexo
	Date nascimento

    static constraints = {
	    sexo blank: false, inList: ["Masculino", "Feminino"]
    }

    String toString() { "$nome" }
}
