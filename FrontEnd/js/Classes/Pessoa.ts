export class Pessoa {
    nome: string
    email: string
    pais: string
    estado: string
    cep: string
    descricao: string
    skills: string[];
    constructor(nome: string, email: string, pais: string, estado: string, cep:string, descricao: string, skills: Array<string>) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.descricao = descricao;
        this.skills = skills;
    }
}

