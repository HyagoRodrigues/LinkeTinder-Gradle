package Linketinder.Gradle.Services

import Linketinder.Gradle.Model.Classes.PessoaFisica
import Linketinder.Gradle.Model.Classes.PessoaJuridica
import Linketinder.Gradle.Model.DAO.CandidatoDAO
import Linketinder.Gradle.Model.DAO.EmpresaDAO
import Linketinder.Gradle.Model.Metodos.Utils

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = "/cadastrar-empresa")
class CadastroEmpresa extends HttpServlet {
    EmpresaDAO empresaDAO = new EmpresaDAO()

    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PessoaJuridica empresa = new PessoaJuridica();
        empresa.nome = req.getParameter("nome")
        empresa.cnpj = req.getParameter("cnpj")
        empresa.email = req.getParameter("email")
        empresa.descricao = req.getParameter("descricao")
        empresa.pais = req.getParameter("pais")
        empresa.cep = req.getParameter("cep")
        empresa.senha= req.getParameter("senha")

        empresaDAO.inserirEmpresaApi(empresa);

    }
}
