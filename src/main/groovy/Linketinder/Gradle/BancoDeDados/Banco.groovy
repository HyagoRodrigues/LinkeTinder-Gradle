package Linketinder.Gradle.BancoDeDados
import groovy.sql.Sql

class Banco {

    static void listar_candidatos() {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.eachRow('SELECT * FROM candidatos') {
            tp ->
                println("\nNome: ${tp.nome} ${tp.sobrenome}" +
                        "\nDescrição: ${tp.descricao}")
                println("Competências: ")
                listar_competencias_candidato(tp.id)
                println("------------------------------")
        }
        sql.close();
    }

    static void listar_competencias_candidato(id) {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.eachRow('SELECT com.nome as nome\n' +
                'FROM candidato_competencias ccom\n' +
                'JOIN candidatos c ON c.id = ccom.id_candidato\n' +
                'JOIN competencias com ON com.id = ccom.id_competencias\n' +
                'WHERE c.id = ' + id + '') {
            comp ->
                println(comp.nome)
        }
        sql.close();
    }

    static void listar_empresas() {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )

        sql.eachRow('SELECT * FROM empresas') {
            tp ->
                println("\nNome: ${tp.nome}" +
                        " \nDescrição: ${tp.descricao}" +
                        "\n------------------------------"
                )
        }

        sql.close();
    }

    static void listar_vagas() {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )

        sql.eachRow('SELECT * FROM vagas') {
            tp ->
                println("\nNome: ${tp.nome}" +
                        " \nDescrição: ${tp.descricao}" +
                        " \nDescrição: ${tp.localizacao}")
                println("Competencias Mínimas: ")
                listar_competencias_vagas(tp.id)
                println("------------------------------")
        }

        sql.close();
    }

    static void listar_competencias_vagas(id){
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.eachRow('SELECT com.nome as nome\n' +
                'FROM vagas_competencias ccom\n' +
                'JOIN vagas c ON c.id = ccom.id_vagas\n' +
                'JOIN competencias com ON com.id = ccom.id_competencias\n' +
                'WHERE c.id = ' + id + '') {
            comp ->
                println(comp.nome)
        }
        sql.close();
    }

    static void inserir_candidato() {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )

        sql.connection.autoCommit = false

        println "Digite o nome do Candidato"
        def nome = System.in.newReader().readLine();
        println "Digite o sobrenome do Candidato"
        def sobrenome = System.in.newReader().readLine();
        println "Digite a data de nascimento do Candidato"
        def data_nascimento = System.in.newReader().readLine();
        println "Digite o email do Candidato"
        def email = System.in.newReader().readLine();
        println "Digite o cpf do Candidato"
        def cpf = System.in.newReader().readLine();
        println "Digite o país do Candidato"
        def pais = System.in.newReader().readLine();
        println "Digite o CEP do Candidato"
        def cep = System.in.newReader().readLine();
        println "Digite a descrição do Candidato"
        def descricao = System.in.newReader().readLine();
        println "Digite a senha do Candidato"
        def senha = System.in.newReader().readLine();

        def sqlstr = "INSERT INTO candidatos(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES " + "('${nome}', '${sobrenome}', '${data_nascimento}', '${email}', '${cpf}', '${pais}', '${cep}', '${descricao}', '${senha}' )"
        def candID

        try {
            sql.execute(sqlstr);
            sql.commit()
            sql.eachRow("SELECT * FROM candidatos WHERE cpf = $cpf") {
                cand ->
                    candID = cand.id
            }
            inserir_competencias_candidato(candID)
            println("Candidato Cadastrado com sucesso!")
        } catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()
    }

    static void inserir_competencias_candidato(candID) {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.connection.autoCommit = false
        println "Escolha a competência: "
        int op = 1;
        while (op != 9) {
            sql.eachRow("SELECT * FROM competencias") {
                comp ->
                    println "${comp.id} - ${comp.nome}"
            }
            int opS = System.in.newReader().readLine().toInteger();
            def insertComp = "INSERT INTO candidato_competencias(id_candidato, id_competencias) VALUES ($candID,$opS)"
            sql.execute(insertComp)
            println("Digite 0 para continuar ou 9 para sair: ");
            op = System.in.newReader().readLine().toInteger();
        }
        sql.commit()
        sql.close()

    }

    static void inserir_empresa() {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.connection.autoCommit = false
        int op = 0;
        println "Digite o nome da Empresa"
        def nome = System.in.newReader().readLine();
        println "Digite o CNPJ da Empresa"
        def cnpj = System.in.newReader().readLine();
        println "Digite o email Corporativo"
        def email = System.in.newReader().readLine();
        println "Digite a descrição da Empresa"
        def descricao = System.in.newReader().readLine();
        println "Digite o país onde a Empresa se encontra"
        def pais = System.in.newReader().readLine();
        println "Digite o CEP da Empresa"
        def cep = System.in.newReader().readLine();
        println "Digite a senha da Empresa"
        def senha = System.in.newReader().readLine();

        def empID
        def sqlstr = "INSERT INTO empresas(nome, cnpj, email, descricao, pais, cep, senha) VALUES " + "('${nome}', '${cnpj}', '${email}', '${descricao}', '${pais}', '${cep}', '${senha}' )"


        try {
            sql.execute(sqlstr);
            sql.commit()
            sql.eachRow("SELECT * FROM empresas WHERE cnpj = $cnpj") {
                cand ->
                    empID = cand.id
            }
            println "Deseja adicionar uma vaga para esta Empresa? 1- Sim / 2- Não"
            op = System.in.newReader().readLine().toInteger();
            if(op == 1){
                inserir_vaga(empID)
            }
            println("Empresa Cadastrada com sucesso!")
        } catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()
    }

    static void inserir_vaga(empID) {
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.connection.autoCommit = false

        println "Digite o nome da Vaga"
        def nome = System.in.newReader().readLine();
        println "Digite a Descrição da Vaga"
        def descricao = System.in.newReader().readLine();
        println "Digite o local da Vaga"
        def localizacao = System.in.newReader().readLine();
        def vagaID
        def sqlstr = "INSERT INTO vagas(nome, descricao, localizacao, id_empresas) VALUES" +
                "('${nome}','${descricao}','${localizacao}',$empID)"

        try{
            sql.execute(sqlstr);
            sql.commit();
            sql.eachRow("SELECT * FROM vagas WHERE descricao = $descricao") {
                vaga ->
                    vagaID = vaga.id
            }
            inserir_competencia_vaga(vagaID);
            println("Vaga Cadastrada com Sucesso")
        }catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()

    }

    static void inserir_competencia_vaga(vagaID){
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/linketinder",
                "hyago",
                "acesso123",
                "org.postgresql.Driver"
        )
        sql.connection.autoCommit = false
        println "Escolha a competência: "
        int op = 1;
        while (op != 9) {
            sql.eachRow("SELECT * FROM competencias") {
                comp ->
                    println "${comp.id} - ${comp.nome}"
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

}
