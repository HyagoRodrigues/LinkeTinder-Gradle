package Linketinder.Gradle.Services

import Linketinder.Gradle.Model.Metodos.ConectionFactory

import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet

@WebServlet(urlPatterns = "/lista-empresas")
class ObterEmpresas extends HttpServlet{
    private ConectionFactory conexao = new ConectionFactory()

    @Override
    void service(ServletRequest req, ServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        def sql = conexao.conect();
        sql.eachRow('SELECT * FROM empresas') {
            lista_competencias ->
                out.println("${lista_competencias.nome}")

        }
        sql.close();
    }


}
