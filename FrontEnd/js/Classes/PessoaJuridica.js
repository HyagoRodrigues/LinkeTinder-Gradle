import { Pessoa } from "./Pessoa.js";
export class PessoaJuridica extends Pessoa {
    constructor(nome, email, cnpj, pais, estado, cep, descricao, skills) {
        super(nome, email, pais, estado, cep, descricao, skills);
        this.cnpj = cnpj;
    }
}
