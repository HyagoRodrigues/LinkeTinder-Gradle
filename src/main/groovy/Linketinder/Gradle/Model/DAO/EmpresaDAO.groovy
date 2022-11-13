package Linketinder.Gradle.Model.DAO


import Linketinder.Gradle.Model.Metodos.ConectionFactory
import Linketinder.Gradle.Model.Classes.PessoaJuridica
import groovy.sql.Sql

class EmpresaDAO {
    VagasDAO vaga = new VagasDAO();
    private ConectionFactory conexao = new ConectionFactory()

    void inserir_empresa(empresa) {
        Sql sql = conexao.conect();
        sql.connection.autoCommit = false
        int opcao = 0;
        def empresa_ID
        List<String> parametros = [empresa.nome, empresa.cnpj, empresa.email, empresa.descricao, empresa.pais, empresa.cep, empresa.senha]
        try {
            sql.executeInsert("INSERT INTO empresas(nome, cnpj, email, descricao, pais, cep, senha) VALUES(?,?,?,?,?,?,?)", parametros);
            sql.commit()
            sql.eachRow("SELECT * FROM empresas WHERE cnpj = $empresa.cnpj") {
                emp ->
                    empresa_ID = emp.id
            }
            println "Deseja adicionar uma vaga para esta Empresa? 1- Sim / 2- Não"
            opcao = System.in.newReader().readLine().toInteger();
            if (opcao == 1) {
                vaga.menu_vaga(empresa_ID)
            }
            println("Empresa Cadastrada com sucesso!")
        } catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()
    }

    void inserirEmpresaApi(empresa){
        Sql sql = conexao.conect();
        sql.connection.autoCommit = false
        List<String> parametros = [empresa.nome, empresa.cnpj, empresa.email, empresa.descricao, empresa.pais, empresa.cep, empresa.senha]
        try{
            sql.executeInsert("INSERT INTO empresas(nome, cnpj, email, descricao, pais, cep, senha) VALUES(?,?,?,?,?,?,?)", parametros);
            sql.commit()
            println("Empresa Cadastrada com sucesso!")
        }catch (Exception ex) {
            sql.rollback()
            println(ex)
        }
        sql.close()
    }

    void listar_empresas() {
        Sql sql = conexao.conect();
        sql.eachRow('SELECT * FROM empresas') {
            lista_empresa ->
                println("\nNome: ${lista_empresa.nome}" +
                        " \nDescrição: ${lista_empresa.descricao}" +
                        "\n------------------------------"
                )
        }

        sql.close();
    }


    void menu_empresa() {
        PessoaJuridica empresa = new PessoaJuridica();
        println "Digite o nome da Empresa"
        empresa.nome = System.in.newReader().readLine();
        println "Digite o CNPJ da Empresa"
        empresa.cnpj = System.in.newReader().readLine();
        println "Digite o email Corporativo"
        empresa.email = System.in.newReader().readLine();
        println "Digite a descrição da Empresa"
        empresa.descricao = System.in.newReader().readLine();
        println "Digite o país onde a Empresa se encontra"
        empresa.pais = System.in.newReader().readLine();
        println "Digite o CEP da Empresa"
        empresa.cep = System.in.newReader().readLine();
        println "Digite a senha da Empresa"
        empresa.senha = System.in.newReader().readLine();

        inserir_empresa(empresa);
    }
}
