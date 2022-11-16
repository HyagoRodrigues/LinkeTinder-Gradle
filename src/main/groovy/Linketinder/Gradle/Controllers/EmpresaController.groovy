package Linketinder.Gradle.Controllers

import Linketinder.Gradle.Model.Classes.PessoaJuridica
import Linketinder.Gradle.Model.DAO.EmpresaDAO
import Linketinder.Gradle.Model.Metodos.ConectionFactory

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = "/empresas")
class EmpresaController extends HttpServlet {
    EmpresaDAO empresaDAO = new EmpresaDAO()
    private ConectionFactory conexao = new ConectionFactory()

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        def sql = conexao.conect();
        sql.eachRow('SELECT * FROM empresas') {
            lista_competencias ->
                out.println("${lista_competencias.nome}")

        }
        sql.close();
    }
}
