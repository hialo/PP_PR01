package br.org

class Instituicao {

	String nome
	String endereco
	String telefone

    static constraints = {
    }

    String toString() { "$nome" }
}
