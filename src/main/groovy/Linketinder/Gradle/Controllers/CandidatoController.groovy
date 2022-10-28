package Linketinder.Gradle.Controllers

import Linketinder.Gradle.Model.DAO.CandidatoDAO

class CandidatoController {
    CandidatoDAO candidato = new CandidatoDAO();

    void inserir_candidato(){
        candidato.menu_candidato();
    }

    void listar_candidatos(){
        candidato.listar_candidatos()
    }
}
