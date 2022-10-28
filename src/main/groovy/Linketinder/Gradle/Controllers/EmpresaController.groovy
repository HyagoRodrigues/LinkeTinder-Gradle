package Linketinder.Gradle.Controllers

import Linketinder.Gradle.Model.DAO.EmpresaDAO

class EmpresaController {
    EmpresaDAO empresa = new EmpresaDAO();

    void inserir_empresa(){
        empresa.menu_empresa()
    }

    void listar_empresas(){
        empresa.listar_empresas()
    }
}
