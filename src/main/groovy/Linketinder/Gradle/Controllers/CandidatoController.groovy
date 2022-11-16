package Linketinder.Gradle.Controllers

import Linketinder.Gradle.Model.Classes.PessoaFisica
import Linketinder.Gradle.Model.DAO.CandidatoDAO
import Linketinder.Gradle.Model.Metodos.ConectionFactory
import Linketinder.Gradle.Model.Metodos.Utils

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(urlPatterns = "/candidatos")
class CandidatoController extends HttpServlet {
    CandidatoDAO candidatoDAO = new CandidatoDAO();
    private ConectionFactory conexao = new ConectionFactory()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        PrintWriter out = res.getWriter();
        def sql = conexao.conect();
        sql.eachRow('SELECT * FROM candidatos') {
            lista_candidato ->
                out.println("${lista_candidato.nome}" + " ${lista_candidato.sobrenome}")

        }
        sql.close();
    }

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
