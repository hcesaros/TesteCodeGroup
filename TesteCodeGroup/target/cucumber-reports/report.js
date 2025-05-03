$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Site.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# language: pt"
    },
    {
      "line": 3,
      "value": "#Autor: Henrique Cesar"
    },
    {
      "line": 4,
      "value": "#Data: 02/05/2025"
    }
  ],
  "line": 6,
  "name": "Validacoes do Site Amazon",
  "description": "",
  "id": "validacoes-do-site-amazon",
  "keyword": "Funcionalidade"
});
formatter.scenario({
  "line": 38,
  "name": "Verificar navegação correta dos links",
  "description": "",
  "id": "validacoes-do-site-amazon;verificar-navegação-correta-dos-links",
  "type": "scenario",
  "keyword": "Cenario",
  "tags": [
    {
      "line": 37,
      "name": "@Teste08"
    }
  ]
});
formatter.step({
  "line": 39,
  "name": "que estou na página inicial",
  "keyword": "Dado "
});
formatter.step({
  "line": 40,
  "name": "clico em um item do menu",
  "keyword": "Quando "
});
formatter.step({
  "line": 41,
  "name": "devo ser redirecionado para a página correspondente",
  "keyword": "Entao "
});
formatter.match({
  "location": "StepDefinition.que_estou_na_pagina_inicial()"
});
formatter.result({
  "duration": 1653216125,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.clicoEmUmItemDoMenu()"
});
formatter.result({
  "duration": 893684917,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.devoSerRedirecionadoParaAPaginaCorrespondente()"
});
formatter.result({
  "duration": 103241500,
  "status": "passed"
});
});