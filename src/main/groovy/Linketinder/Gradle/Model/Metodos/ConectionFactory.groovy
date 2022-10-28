package Linketinder.Gradle.Model.Metodos

import groovy.sql.Sql

class ConectionFactory implements IConection {

    Sql conect() {
        def url = "jdbc:postgresql://localhost:5432/linketinder";
        def user = "hyago";
        def password = "acesso123";
        def driver = "org.postgresql.Driver";
        Sql sql = Sql.newInstance(url, user, password, driver)
        return sql
    }

}
