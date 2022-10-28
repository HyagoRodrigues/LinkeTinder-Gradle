package Linketinder.Gradle.Model.Metodos

import groovy.sql.Sql

interface IConection {
    Sql conect();
}