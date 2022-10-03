package Linketinder.Gradle.Classes

class Listas {
    static List<PessoaFisica> listaPessoaFisica() {
        PessoaFisica pf1 = new PessoaFisica(nome: "José", email: "j@email.com", descricao: "Desenvolvedor Back-end", estado: "GO", pais: "Brasil", cep: "72322025", cpf: "048.453.113-12", idade: 30, skills: ["Java", "C#", "Python"])
        PessoaFisica pf2 = new PessoaFisica(nome: "Maria", email: "ma@email.com", descricao: "Desenvolvedora Front-end", estado: "MG", pais: "Brasil", cep: "72322025", cpf: "048.453.113-12", idade: 30, skills: ["JavaScript", "HTML", "CSS"])
        PessoaFisica pf3 = new PessoaFisica(nome: "Ana", email: "a@email.com", descricao: "DevFullStack", estado: "SP", pais: "Brasil", cep: "12345", cpf: "123456789", idade: 33, skills: ["Javascript", "CSS", "HTML", "MongoDB"])
        PessoaFisica pf4 = new PessoaFisica(nome: "Paulo", email: "p@email.com", descricao: "Desenvolvedor Back-end", estado: "RJ", pais: "Brasil", cep: "72322025", cpf: "048.453.113-12", idade: 30, skills: ["Java", "C#", "Python"])
        PessoaFisica pf5 = new PessoaFisica(nome: "Pedro", email: "pp@email.com", descricao: "Desenvolvedor Back-end", estado: "SP", pais: "Brasil", cep: "72322025", cpf: "048.453.113-12", idade: 30, skills: ["Java", "C#", "Python"])

        return [pf1, pf2, pf3, pf4, pf5]
    }


    static List<PessoaJuridica> listaPessoaJuridica() {
        PessoaJuridica pj1 = new PessoaJuridica(nome: "Google", email: "g@email.com", descricao: "Empresa de busca", estado: "SP", pais: "Brasil", cep: "72322025", cnpj: "123456789", skills: ["Java", "C#", "Python"])
        PessoaJuridica pj2 = new PessoaJuridica(nome: "Facebook", email: "f@email.com", descricao: "Rede social", estado: "SP", pais: "Brasil", cep: "72322025", cnpj: "123456789", skills: ["Java", "C#", "Python"])
        PessoaJuridica pj3 = new PessoaJuridica(nome: "Microsoft", email: "m@email.com", descricao: "Empresa de TI", estado: "SP", pais: "Brasil", cep: "72322025", cnpj: "123456789", skills: ["Java", "C#", "Python"])
        PessoaJuridica pj4 = new PessoaJuridica(nome: "Amazon", email: "a@email.com", descricao: "Empresa de e-commerce", estado: "SP", pais: "Brasil", cep: "72322025", cnpj: "123456789", skills: ["Java", "C#", "Python"])
        PessoaJuridica pj5 = new PessoaJuridica(nome: "Apple", email: "ap@email.com", descricao: "Empresa de tecnologia", estado: "SP", pais: "Brasil", cep: "72322025", cnpj: "123456789", skills: ["Java", "C#", "Python"])
        return [pj1, pj2, pj3, pj4, pj5]

    }
}
