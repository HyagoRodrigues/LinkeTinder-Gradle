package Linketinder.Gradle.Services

import Linketinder.Gradle.Model.Classes.PessoaFisica
import Linketinder.Gradle.Model.DAO.CandidatoDAO
import Linketinder.Gradle.Model.Metodos.ConectionFactory
import Linketinder.Gradle.Model.Metodos.Utils
import groovy.sql.Sql

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = "/cadastro-candidatos")
class CadastroCandidato extends HttpServlet {
    CandidatoDAO candidatoDAO = new CandidatoDAO()
    @Override
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PessoaFisica candidato = new PessoaFisica()
        candidato.nome = req.getParameter("nome")
        candidato.sobrenome = req.getParameter("sobrenome")
        def data = req.getParameter("data_nascimento")
        candidato.data_nascimento = Utils.formata_data(candidato.data_nascimento, data)
        candidato.email = req.getParameter("email")
        candidato.cpf = req.getParameter("cpf")
        candidato.pais = req.getParameter("pais")
        candidato.cep = req.getParameter("cep")
        candidato.descricao = req.getParameter("descricao")
        candidato.senha = req.getParameter("senha")

        candidatoDAO.inserirCandidatoApi(candidato);

    }
}
