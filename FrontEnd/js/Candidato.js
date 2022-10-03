var _a, _b, _c, _d, _e;
import { PessoaFisica } from "./Classes/PessoaFisica.js";
import { empresas } from "./Empresa.js";
export const candidatos = new Array();
let nome = document.querySelector('#NomeInputC');
let email = document.querySelector('#EmailInputC');
let telefone = document.querySelector('#TelInputC');
let linkedin = document.querySelector('#LinkInputC');
let cpf = document.querySelector('#CpfInputC');
let idade = document.querySelector('#IdadeInputC');
let estado = document.querySelector('#EstadoInputC');
let cep = document.querySelector('#CepInputC');
let pais = document.querySelector('#PaisinputC');
let desc = document.querySelector('#DescInputC');
const canditado1 = new PessoaFisica("Eduardo", "e@email.com", "(61) 9888-5555", "https://www.linkedin.com/in/eduardo-457", "816.171.166-61", 22, "Brasil", "DF", "72590-000", "Desenvolvedor Junior", ["Java", "Groovy", "Angular", "TypeScript"]);
const canditado2 = new PessoaFisica("Maria Alice", "maria.alice@email.com", "(62) 9888-5555", "https://www.linkedin.com/in/maria-897", "361.204.631-43", 22, "Brasil", "GO", "72590-000", "Desenvolvedora Pleno", ["Java", "Groovy", "PostgreSQL", "Python", "Angular"]);
const canditado3 = new PessoaFisica("Gabriel Fernandes", "maria.alice@email.com", "(64) 9888-8755", "https://www.linkedin.com/in/gh-457", "361.204.631-43", 22, "Brasil", "GO", "72590-000", "Desenvolvedor Junior", ["JavaScript", "Typescript", "Angular", "Grails",]);
const canditado4 = new PessoaFisica("Josefina", "j@email.com", "(63) 9888-5555", "https://www.linkedin.com/in/josefina-457", "361.204.631-43", 22, "Brasil", "GO", "72590-000", "Desenvolvedora Sênior", ["Java", "Groovy", "Angular", "PostgreSQL", "Grails",]);
const canditado5 = new PessoaFisica("Gustavo", "g@email.com", "(61) 9888-5555", "https://www.linkedin.com/in/gus-457", "361.204.631-43", 22, "Brasil", "GO", "72590-000", "Desenvolvedora Pleno", ["Java", "Angular", "PostgreSQL", "JavaScript"]);
candidatos.push(canditado1, canditado2, canditado3, canditado4, canditado5);
//fechar modal
export function closeModal() {
    const modal = document.querySelector('.modal');
    var mod = bootstrap.Modal.getInstance(modal);
    mod.hide();
}
//Máscaras
function maskCPF() {
    let cpf = document.querySelector('#CpfInputC');
    if (cpf.value.length == 3 || cpf.value.length == 7) {
        cpf.value += ".";
    }
    else if (cpf.value.length == 11) {
        cpf.value += "-";
    }
}
function maskTelefone() {
    let telefone = document.querySelector('#TelInputC');
    if (telefone.value && telefone.value.length <= 1) {
        let valor = telefone.value;
        telefone.value = "(" + valor;
    }
    else if (telefone.value.length == 3) {
        telefone.value += ")";
    }
    else if (telefone.value.length == 9) {
        telefone.value += "-";
    }
}
function maskCep() {
    let cep = document.querySelector('#CepInputC');
    if (cep.value.length == 5) {
        cep.value += "-";
    }
}
//sessão de validação
function validaCadastro() {
    const checkboxes = document.querySelectorAll('.form-check-input');
    const checks = Array.from(checkboxes);
    let valido = false;
    let inputs = [
        {
            nome: document.querySelector('#NomeInputC'),
            validado: (/(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ][A-Z][a-z]+)*$/).test(nome.value),
            erro: document.querySelector('#erro-nome'),
        },
        {
            email: document.querySelector('#EmailInputC'),
            validado: (/(\S+@\w+\.\w{2,6}(\.\w{2})?)/g).test(email.value),
            erro: document.querySelector('#erro-email'),
        },
        {
            linkedin: document.querySelector('#LinkInputC'),
            validado: (/(https?:\/\/(www.)|(www.))?linkedin.com\/(mwlite\/|m\/)?in\/[a-zA-Z0-9_.-]+\/?/).test(linkedin.value),
            erro: document.querySelector('#erro-linkedin'),
        },
        {
            telefone: document.querySelector('#TelInputC'),
            validado: (/(\(?\d{2}\)?\s)?(\d{4,5}\-\d{4})/g).test(telefone.value),
            erro: document.querySelector('#erro-telefone'),
        },
        {
            idade: document.querySelector('#IdadeInputC'),
            validado: idade.value >= 18,
            erro: document.querySelector('#erro-idade'),
        },
        {
            cpf: document.querySelector('#CpfInputC'),
            validado: (/^\d{3}\.\d{3}\.\d{3}\-\d{2}$/).test(cpf.value),
            erro: document.querySelector('#erro-cpf'),
        },
        {
            estado: document.querySelector('#EstadoInputC'),
            validado: (/(?:^|\s)(?!da|de|do)\S/g).test(estado.value),
            erro: document.querySelector('#erro-estado'),
        },
        {
            cep: document.querySelector('#CepInputC'),
            validado: (/^\d{2}\.?\d{3}\-\d{3}/).test(cep.value),
            erro: document.querySelector('#erro-cep'),
        },
        {
            pais: document.querySelector('#PaisinputC'),
            validado: (/(?:^|\s)(?!da|de|do)\S/g).test(pais.value),
            erro: document.querySelector('#erro-pais'),
        },
        {
            desc: document.querySelector('#DescInputC'),
            validado: (/\w{1,25}/g).test(desc.value),
            erro: document.querySelector('#erro-desc'),
        },
        {
            checks,
            validado: checks.some(i => i.checked === true),
            erro: document.querySelector('#erro-skill')
        }
    ];
    inputs.map(i => {
        if (!i.validado) {
            i.erro.style.display = "block";
            valido = false;
        }
        else {
            i.erro.style.display = "none";
            valido = true;
        }
    });
    let retorno = inputs.every((valido) => valido.validado === true);
    if (retorno) {
        return valido;
    }
}
//Cadastro de Candidato
export function CadastraCandidato() {
    let skills = [];
    let checkboxes = document.querySelectorAll('.form-check-input');
    for (let checkbox of checkboxes) {
        if (checkbox.checked) {
            skills.push(checkbox.value);
        }
    }
    const candidato = new PessoaFisica(nome.value, email.value, telefone.value, linkedin.value, cpf.value, idade.value, pais.value, estado.value, cep.value, desc.value, skills);
    candidatos.push(candidato);
    document.getElementById('modal-form').reset();
    closeModal();
    console.log(candidatos);
}
function salvaCadastro(e) {
    e.preventDefault();
    if (validaCadastro()) {
        CadastraCandidato();
    }
}
//Listar Empresas
var contador = 0;
function listarEmpresas() {
    if (contador == 0) {
        empresas.map(emp => {
            const nRow = document.createElement('tr');
            nRow.innerHTML = `
            <td>${emp.descricao}</td>
            <td>${emp.skills}</td>
            <td>
                <button class="btn btn-outline-success"> Curtir </button>
            </td>
        `;
            document.querySelector('#tbody').appendChild(nRow);
        });
        contador++;
    }
}
//eventos
(_a = document.getElementById('CepInputC')) === null || _a === void 0 ? void 0 : _a.addEventListener('keyup', maskCep);
(_b = document.getElementById('TelInputC')) === null || _b === void 0 ? void 0 : _b.addEventListener('keyup', maskTelefone);
(_c = document.getElementById('CpfInputC')) === null || _c === void 0 ? void 0 : _c.addEventListener('keyup', maskCPF);
(_d = document.getElementById('listar-empresas')) === null || _d === void 0 ? void 0 : _d.addEventListener("click", listarEmpresas);
(_e = document.getElementById('modal-form')) === null || _e === void 0 ? void 0 : _e.addEventListener('submit', salvaCadastro);
