package Linketinder.Gradle.Model.Metodos

import groovy.sql.Sql

import java.text.ParseException
import java.text.SimpleDateFormat

class Utils {

    static Date converte_data_type(data_nascimento){
        SimpleDateFormat formato_padrao = new SimpleDateFormat("yyyy-MM-dd");
        String dataCandidato = data_nascimento.toString();
        Date nova_data = formato_padrao.parse(dataCandidato)
        return new java.sql.Date(nova_data.getTime())
    }

    static String formata_data(data, data_input){
        SimpleDateFormat padrao_usuario = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat padrao_sql = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String padra_formatado = padrao_sql.format(padrao_usuario.parse(data_input));
            data = padra_formatado
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data
    }
}
