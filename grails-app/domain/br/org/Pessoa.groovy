package br.org

class Pessoa {

	String nome
	String email 
	String telefone 
	String senha


    static constraints = {
    	nome (blank:false, nullable:false)
    	email (blank:false, nullable:false,email:true,unique: true)
    	telefone (blank:false,number:true)
    }
}
