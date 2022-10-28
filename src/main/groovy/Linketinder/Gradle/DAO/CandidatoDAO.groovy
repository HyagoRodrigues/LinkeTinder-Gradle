package Linketinder.Gradle.DAO

import Linketinder.Gradle.Classes.PessoaFisica
import Linketinder.Gradle.Metodos.ConectionFactory
import Linketinder.Gradle.Metodos.Utils
import groovy.sql.Sql

class CandidatoDAO {

    private ConectionFactory conexao = new ConectionFactory()

    void listar_candidatos() {
        def sql = conexao.conect()
        sql.eachRow('SELECT * FROM candidatos') {
            lista_candidato ->
                println("\nNome: ${lista_candidato.nome} ${lista_candidato.sobrenome}" +
                        "\nDescrição: ${lista_candidato.descricao}")
                println("Competências: ")
                listar_competencias_candidato(lista_candidato.id)
                println("------------------------------")
        }
        sql.close();
    }

    void listar_competencias_candidato(id) {
        def sql = conexao.conect();
        sql.eachRow('SELECT com.nome as nome\n' +
                'FROM candidato_competencias ccom\n' +
                'JOIN candidatos c ON c.id = ccom.id_candidato\n' +
                'JOIN competencias com ON com.id = ccom.id_competencias\n' +
                'WHERE c.id = ' + id + '') {
            competencia_candidato ->
                println(competencia_candidato.nome)
        }
        sql.close();
    }

    void inserir_candidato(candidato) {
        Sql sql = conexao.conect()
        sql.connection.autoCommit = false
        Date data_nascimento = Utils.converte_data_type(candidato.data_nascimento);
        List<String> parametros = [candidato.nome, candidato.sobrenome, data_nascimento, candidato.email, candidato.cpf, candidato.pais, candidato.cep, candidato.descricao, candidato.senha]
        def candidato_ID
        try {
            sql.executeInsert("INSERT INTO candidatos(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES (?,?,?,?,?,?,?,?,?)", parametros)
            sql.commit()
            sql.eachRow("SELECT * FROM candidatos WHERE cpf = $candidato.cpf") {
                cand ->
                    candidato_ID = cand.id
            }
            inserir_competencias_candidato(candidato_ID)
            println("Candidato Cadastrado com sucesso!")
        } catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()
    }

    void inserir_competencias_candidato(candidato_ID) {
        Sql sql = conexao.conect()
        sql.connection.autoCommit = false;
        println "Escolha a competência: "
        int opcao = 1;
        while (opcao != 9) {
            sql.eachRow("SELECT * FROM competencias") {
                competencia_candidato ->
                    println "${competencia_candidato.id} - ${competencia_candidato.nome}"
            }
            int opcaoCompetencia = System.in.newReader().readLine().toInteger();
            def inserirCompetencia = "INSERT INTO candidato_competencias(id_candidato, id_competencias) VALUES ($candidato_ID,$opcaoCompetencia)"
            sql.execute(inserirCompetencia)
            println("Digite 0 para continuar ou 9 para sair: ");
            opcao = System.in.newReader().readLine().toInteger();
        }
        sql.commit()
        sql.close()

    }

    void menu_candidato() {
        PessoaFisica candidato = new PessoaFisica()
        println "Digite o nome do Candidato"
        candidato.nome = System.in.newReader().readLine();
        println "Digite o sobrenome do Candidato"
        candidato.sobrenome = System.in.newReader().readLine();
        println "Digite a data de nascimento do Candidato Ex: 00/00/0000"
        def data_input = System.in.newReader().readLine();
        println "Digite o email do Candidato"
        candidato.email = System.in.newReader().readLine();
        println "Digite o cpf do Candidato"
        candidato.cpf = System.in.newReader().readLine();
        println "Digite o país do Candidato"
        candidato.pais = System.in.newReader().readLine();
        println "Digite o CEP do Candidato"
        candidato.cep = System.in.newReader().readLine();
        println "Digite a descrição do Candidato"
        candidato.descricao = System.in.newReader().readLine();
        println "Digite a senha do Candidato"
        candidato.senha = System.in.newReader().readLine();

        candidato.data_nascimento = Utils.formata_data(candidato.data_nascimento, data_input)
        inserir_candidato(candidato)
    }

}
