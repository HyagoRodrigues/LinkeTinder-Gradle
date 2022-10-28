package Linketinder.Gradle.Controllers

import Linketinder.Gradle.Model.DAO.VagasDAO

class VagaController {
    VagasDAO vaga = new VagasDAO();
        void listar_vagas(){
            vaga.listar_vagas()
        }
}
