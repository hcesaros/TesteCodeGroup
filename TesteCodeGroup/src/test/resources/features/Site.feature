# language: pt

#Autor: Henrique Cesar
#Data: 02/05/2025

Funcionalidade: Validacoes do Site Amazon

@Teste01
Cenario: Exibir sugestões ao digitar
    Dado que estou na página inicial
    Quando digito "notebook" na barra de pesquisa
    Então devem aparecer sugestões relacionadas abaixo do campo

@Teste02
Cenario: Não exibir sugestões para termos irrelevantes
    Dado que estou na página inicial
    Quando digito "#@!" na barra de pesquisa
    Então nenhuma sugestão deve ser exibida

@Teste05
Cenario: Selecionar sugestão usando mouse
    Dado que estou na página inicial
    Quando digito "notebook" na barra de pesquisa
    E clico em uma das sugestões
    Então devo ser redirecionado corretamente

@Teste06
Cenario: Exibir menu corretamente no desktop
    Dado que acesso a aplicação com largura de tela maior ou igual a 1024px
    Entao o menu deve estar visível na Amazon

@Teste07
Cenario: Exibir menu hamburger em mobile
    Dado que acesso a aplicação com largura de tela menor ou igual a 768px
    Entao o ícone de menu hamburger deve ser exibido

@Teste08
Cenario: Verificar navegação correta dos links
    Dado que estou na página inicial
    Quando clico em um item do menu
    Entao devo ser redirecionado para a página correspondente


