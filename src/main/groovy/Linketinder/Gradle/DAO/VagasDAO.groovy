package Linketinder.Gradle.DAO

import Linketinder.Gradle.Classes.Vaga
import Linketinder.Gradle.Metodos.ConectionFactory
import groovy.sql.Sql

class VagasDAO {
    static void listar_vagas(){
        Sql sql = ConectionFactory.conect();
        sql.eachRow('SELECT * FROM vagas') {
            lista_vaga ->
                println("\nNome: ${lista_vaga.nome}" +
                        " \nDescrição: ${lista_vaga.descricao}" +
                        " \nDescrição: ${lista_vaga.localizacao}")
                println("Competencias Mínimas: ")
                listar_competencias_vagas(lista_vaga.id)
                println("------------------------------")
        }

        sql.close();
    }

    static void listar_competencias_vagas(id){
        Sql sql = ConectionFactory.conect();
        sql.eachRow('SELECT com.nome as nome\n' +
                'FROM vagas_competencias ccom\n' +
                'JOIN vagas c ON c.id = ccom.id_vagas\n' +
                'JOIN competencias com ON com.id = ccom.id_competencias\n' +
                'WHERE c.id = ' + id + '') {
            competencia_vaga ->
                println(competencia_vaga.nome)
        }
        sql.close();
    }

    static void inserir_competencia_vaga(vagaID){
        Sql sql = ConectionFactory.conect();
        sql.connection.autoCommit = false
        println "Escolha a competência: "
        int op = 1;
        while (op != 9) {
            sql.eachRow("SELECT * FROM competencias") {
                competencia ->
                    println "${competencia.id} - ${competencia.nome}"
            }
            int opS = System.in.newReader().readLine().toInteger();
            def insertComp = "INSERT INTO vagas_competencias(id_vagas, id_competencias) VALUES ($vagaID,$opS)"
            sql.execute(insertComp)
            println("Digite 0 para continuar ou 9 para sair: ");
            op = System.in.newReader().readLine().toInteger();
        }
        sql.commit()
        sql.close()
    }

    static void menu_vaga(empresa_ID){
        Vaga vaga = new Vaga();
        println "Digite o nome da Vaga"
        vaga.nome = System.in.newReader().readLine();
        println "Digite a Descrição da Vaga"
        vaga.descricao = System.in.newReader().readLine();
        println "Digite o local da Vaga"
        vaga.localizacao = System.in.newReader().readLine();

        inserir_vaga(vaga, empresa_ID )
    }

    static void inserir_vaga(Vaga vaga_empresa, empresa_ID) {
        Sql sql = ConectionFactory.conect();
        sql.connection.autoCommit = false
        def vaga_ID

        List<String> parametros = [vaga_empresa.nome,vaga_empresa.descricao, vaga_empresa.localizacao, empresa_ID ]
        try{
            sql.execute("INSERT INTO vagas(nome, descricao, localizacao, id_empresas) VALUES (?,?,?,?)", parametros);
            sql.commit();
            sql.eachRow("SELECT * FROM vagas WHERE descricao = $vaga_empresa.descricao") {
                vaga ->
                    vaga_ID = vaga.id
            }
            inserir_competencia_vaga(vaga_ID);
            println("Vaga Cadastrada com Sucesso")
        }catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()

    }
}
