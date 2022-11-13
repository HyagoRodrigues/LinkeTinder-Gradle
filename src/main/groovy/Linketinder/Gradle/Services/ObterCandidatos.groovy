package Linketinder.Gradle.Services

import Linketinder.Gradle.Model.Metodos.ConectionFactory

import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet

@WebServlet(urlPatterns = "/listar-candidatos")
class ObterCandidatos extends HttpServlet {
    private ConectionFactory conexao = new ConectionFactory()

    @Override
    void service(ServletRequest req, ServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        def sql = conexao.conect();
        sql.eachRow('SELECT * FROM candidatos') {
            lista_candidato ->
                out.println("${lista_candidato.nome}" + " ${lista_candidato.sobrenome}")

        }
        sql.close();
    }


}
