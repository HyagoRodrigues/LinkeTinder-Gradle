import {PessoaJuridica} from "./Classes/PessoaJuridica.js";
import { candidatos, closeModal} from "./Candidato.js";

export const empresas: Array<PessoaJuridica> = new Array<PessoaJuridica>();

let nome = <HTMLSelectElement>document.querySelector('#NomeInput');
let email = <HTMLSelectElement>document.querySelector('#EmailInput');
let cnpj = <HTMLSelectElement>document.querySelector('#CnpjInput');
let estado = <HTMLSelectElement>document.querySelector('#EstadoInput');
let cep = <HTMLSelectElement>document.querySelector('#CepInput');
let pais = <HTMLSelectElement>document.querySelector('#Paisinput');
let desc = <HTMLSelectElement>document.querySelector('#DescInput');

const empresa1 = new PessoaJuridica(
    "Google",
    "g@email.com",
    "23.982.275/0001-73",
    "Estados Unidos",
    "CA",
    "0021548-548",
    "Multinacional que oferece serviços online e softwares para download.",
    ["Java", "Groovy", "Angular"]
);
const empresa2 = new PessoaJuridica(
    "Amazon",
    "a@email.com",
    "23.982.275/0001-73",
    "Estados Unidos",
    "CA",
    "0021548-548",
    "Multinacional de tecnologia norte-americana com sede em Seattle",
    ["Java", "Groovy", "Angular", "TypeScript"]
);
const empresa3 = new PessoaJuridica(
    "Apple",
    "aa@email.com",
    "23.982.275/0001-73",
    "Estados Unidos",
    "CA",
    "0021548-548",
    "Multinacional norte-americana de produtos eletrônicos de consumo",
    ["Java", "Groovy", "Angular", "PostgreSQL", "Grails"]
);
const empresa4 = new PessoaJuridica(
    "Microsoft",
    "m@email.com",
    "23.982.275/0001-73",
    "Estados Unidos",
    "CA",
    "0021548-548",
    "Empresa transnacional dos Estados Unidos com sede em Redmond",
    ["JavaScript","TypeScript" ,"Angular"]
);
const empresa5 = new PessoaJuridica(
    "Dell",
    "dell@email.com",
    "23.982.275/0001-73",
    "Estados Unidos",
    "CA",
    "0021548-548",
    "Empresa de hardware de computador, com foco no desenvolvimento de PCs",
    ["Java", "Groovy", "Angular", "PostgreSQL", "JavaScript"]
);

empresas.push(empresa1, empresa2, empresa3, empresa4, empresa5);


//Máscaras
function maskCNPJ(){
    let cnpj = document.querySelector('#CnpjInput');
    if(cnpj.value.length == 2 || cnpj.value.length == 6){
        cnpj.value += "."
    }else if(cnpj.value.length == 10){
        cnpj.value += "/"
    }else if (cnpj.value.length == 15){
        cnpj.value += "-"
    }
}

function maskCep(){
    let cep = document.querySelector('#CepInput');
    if(cep.value.length == 5){
        cep.value += "-"
    }
}

//validação
function validaCadastro(){
    const checkboxes = document.querySelectorAll('.form-check-input');
    const checks = Array.from(checkboxes);
    let valido = false;
    let inputs = [
        {
            nome: <HTMLSelectElement>document.querySelector('#NomeInput'),
            validado: (/(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ][A-Z][a-z]+)*$/).test(nome.value),
            erro: <HTMLSelectElement>document.querySelector('#erroNome'),
        },
        {
            email: <HTMLSelectElement>document.querySelector('#EmailInput'),
            validado: (/(\S+@\w+\.\w{2,6}(\.\w{2})?)/g).test(email.value),
            erro: <HTMLSelectElement>document.querySelector('#erroEmail'),
        },
        {
            cpf: <HTMLSelectElement>document.querySelector('#CnpjInput'),
            validado: (/^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/).test(cnpj.value),
            erro: <HTMLSelectElement>document.querySelector('#erroCnpj'),
        },
        {
            estado:<HTMLSelectElement>document.querySelector('#EstadoInput'),
            validado: (/(?:^|\s)(?!da|de|do)\S/g).test(estado.value),
            erro: <HTMLSelectElement>document.querySelector('#erroEstado'),
        },
        {
            cep: <HTMLSelectElement>document.querySelector('#CepInput'),
            validado: (/^\d{2}\.?\d{3}\-\d{3}/).test(cep.value),
            erro: <HTMLSelectElement>document.querySelector('#erroCep'),
        },
        {
            pais: <HTMLSelectElement>document.querySelector('#Paisinput') ,
            validado: (/(?:^|\s)(?!da|de|do)\S/g).test(pais.value),
            erro: <HTMLSelectElement>document.querySelector('#erroPais'),
        },
        {
            desc: <HTMLSelectElement>document.querySelector('#DescInput'),
            validado: (/\w{1,15}/g).test(desc.value),
            erro: <HTMLSelectElement>document.querySelector('#erroDesc'),
        },
        {
            checks,
            validado: checks.some(i => i.checked === true),
            erro: <HTMLSelectElement>document.querySelector('#erroSkill')
        }
    ]

    inputs.map(i => {
        if (!i.validado) {
            i.erro.style.display = "block"
            valido = false
        }else{
            i.erro.style.display = "none"
            valido = true
        }
    })

    let retorno = inputs.every((valido) => valido.validado === true )
    if(retorno){
        return valido
    }
}

//Cadastro de Empresa
function CadastrarEmpresa() {
    let skills = [];
    let checkboxes = document.querySelectorAll('.form-check-input');
    for (let checkbox of checkboxes) {
        if (checkbox.checked) {
            skills.push(checkbox.value);
        }
    }
    const empresa = new PessoaJuridica(
        nome.value,
        email.value,
        cnpj.value,
        estado.value,
        pais.value,
        cep.value,
        desc.value,
        skills
    )
    empresas.push(empresa);
    document.getElementById('modal-form').reset();
    closeModal();
    console.log(empresas);
}

function salvaCadastro(e) {
    e.preventDefault();
    if (validaCadastro()) {
        CadastrarEmpresa();
    }
}

//Listagem
let contador = 0;
function listarCandidatos() {
    if(contador == 0) {
        candidatos.map(cand => {
            const nRow = document.createElement('tr');
            nRow.innerHTML = `
            <td>${cand.descricao}</td>
            <td>${cand.skills}</td>
            <td>
                <button class="btn btn-outline-success"> Curtir </button>
            </td>
        `;
            document.querySelector('#tbody').appendChild(nRow);
        })
        contador++
    }
}

//Gráfico
function montarArray(){
    let qntdSkills = []

    const skills = [
        "Python",
        "Java",
        "JavaScript",
        "TypeScript",
        "Groovy",
        "Grails",
        "Angular",
        "PostgreSQL"
    ]

    for (var i = 0; i < skills.length; i++) {
        for (var j = 0; j < candidatos.length; j++) {

            if (candidatos[j].skills.includes(skills[i])) {

                let encontrouSkill = false
                for (var k = 0; k < qntdSkills.length; k++) {

                    if (skills[i] === qntdSkills[k].skill) {

                        encontrouSkill = true;

                        qntdSkills[k].qntd += 1
                        break;
                    }

                }
                if (!encontrouSkill) {

                    qntdSkills.push({
                        skill: skills[i],
                        qntd: 1
                    })
                }

            }
        }
    }

    qntdSkills.sort(function (a, b) {
        var keyA = a.skill,
            keyB = b.skill;
        if (keyA < keyB) return -1;
        if (keyA > keyB) return 1;
        return 0;
    });

    return qntdSkills
}

function gerarGrafico() {
    let ctx = <HTMLSelectElement>document.querySelector('#myChart');
    let qntdSkills = montarArray()
    const skillsLabel = qntdSkills.map(i => i.skill)
    const skillsQnt = qntdSkills.map(i => i.qntd)

    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: skillsLabel,
            datasets: [{
                label: 'Competências',
                data: skillsQnt,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.8)',
                    'rgb(246,176,110, 0.8)',
                    'rgba(94,151,182,0.8)',
                    'rgba(238,135,11,0.8)',
                    'rgb(239,216,29, 0.8)',
                    'rgb(47,116,192, 0.8)',
                    'rgba(0,26,47,0.8)',
                    'rgb(119,123,179, 0.8)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(246,176,110, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgb(240,147,28, 1)',
                    'rgb(239,216,29, 1)',
                    'rgb(47,116,192,1)',
                    'rgba(0,26,47,1)',
                    'rgb(119,123,179, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

}


// Eventos
<HTMLSelectElement> document.getElementById('CepInput')?.addEventListener('keyup',maskCep);
<HTMLSelectElement> document.getElementById('CnpjInput')?.addEventListener('keyup',maskCNPJ);
<HTMLSelectElement> document.getElementById('gerar-grafico')?.addEventListener('click',gerarGrafico);
<HTMLSelectElement> document.getElementById('listar-candidatos')?.addEventListener('click',listarCandidatos);
<HTMLSelectElement > document.getElementById('modal-form')?.addEventListener('submit', salvaCadastro);