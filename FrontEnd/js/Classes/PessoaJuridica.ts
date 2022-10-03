import {Pessoa} from "./Pessoa.js";

export class PessoaJuridica extends Pessoa{
    cnpj: string;
    constructor(nome: string, email: string, cnpj:string,pais: string, estado: string, cep:string, descricao: string, skills: Array<string> ) {
        super(nome, email, pais, estado, cep,descricao,skills);
        this.cnpj = cnpj
    }
}