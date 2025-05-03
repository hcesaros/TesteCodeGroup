//Autor: Henrique Cesar
//Data: 02/05/2025

package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinition {
    WebDriver driver = WebDriverManagerUtil.getDriver(); // Usa o driver configurado pelo WebDriverManagerUtil

    @Dado("que estou na página inicial")
    public void que_estou_na_pagina_inicial() {
        //Limpa todos os cookies do navegador para garantir um estado limpo antes de iniciar o teste
        driver.manage().deleteAllCookies();
        //Navega para a página inicial da Amazon do Brasil usando a URL especificada.
        driver.get("http://amazon.com.br/?_encoding=UTF8&ref_=nav_logo");
    }

    @Quando("digito \"([^\"]*)\" na barra de pesquisa")
    public void digito_na_barra_de_pesquisa(String termo) {
        //Localiza a barra de pesquisa pelo ID "twotabsearchtextbox"
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        //limpa qualquer texto existente
        searchBar.clear();
        //digita o termo fornecido
        searchBar.sendKeys(termo);

        // Validação: Confere se o texto foi realmente digitado no campo
        String textoNoCampo = searchBar.getAttribute("value");
        //Valida se o texto digitado no campo de pesquisa corresponde ao termo fornecido
        assert textoNoCampo.equals(termo) : "O texto digitado não corresponde ao valor no campo de pesquisa. Esperado: " + termo + ", mas foi: " + textoNoCampo;
    }

    @Entao("devem aparecer sugestões relacionadas abaixo do campo")
    public void verificar_sugestoes() {
        // Espera até que as sugestões de pesquisa estejam visíveis
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.s-suggestion")));

        // Obtém as sugestões
        List<WebElement> suggestions = driver.findElements(By.cssSelector("div.s-suggestion"));

        // Verifica se há sugestões
        assert suggestions.size() > 0 : "Nenhuma sugestão foi exibida.";

        // Opcional: imprimir as sugestões para garantir que estamos capturando os itens corretos
        for (WebElement suggestion : suggestions) {
            System.out.println("Sugestão: " + suggestion.getText());
        }
        driver.quit();
   }

    @Entao("nenhuma sugestão deve ser exibida")
    public void nenhuma_sugestao_exibida() {
        //Este passo localiza todos os elementos que representam sugestões de pesquisa usando o seletor CSS "div.s-suggestion
        List<WebElement> suggestions = driver.findElements(By.cssSelector("div.s-suggestion"));
        //e verifica se a lista resultante está vazia. Se a lista não estiver vazia (indicando que sugestões foram encontradas) o teste falhará.
        assert suggestions.size() == 0;
        driver.quit();
    }

    @Quando("clico em uma das sugestões")
    public void clico_em_uma_sugestao() {
        /*
         * Este passo localiza todas as sugestões de pesquisa usando o seletor CSS "div.s-suggestion".
         * Se houver sugestões disponíveis (a lista não estiver vazia), o primeiro elemento da lista
         * (a primeira sugestão) é clicado.  Se não houver sugestões, nenhuma ação é executada.
         */
        List<WebElement> suggestions = driver.findElements(By.cssSelector("div.s-suggestion"));
        if (!suggestions.isEmpty()) {
            suggestions.get(0).click();
        }
    }

    @Entao("devo ser redirecionado corretamente")
    public void redirecionamento_correto() {
        try {
            // Espera até que as sugestões de pesquisa estejam visíveis
            WebDriverWait wait = new WebDriverWait(driver, (10));

            // Clica na sugestão
            WebElement sugestao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sac-suggestion-row-1-cell-1']/div[1]")));
            sugestao.click();

            // Aguarda redirecionamento e valida URL
            wait.until(webDriver -> {
                String url = webDriver.getCurrentUrl().toLowerCase();
                return url.contains("k=notebook") || url.contains("s?k=");
            });
            /*
             * Valida se a URL atual contém os parâmetros esperados após o redirecionamento, indicando que
             * o usuário foi levado para a página de resultados de pesquisa para "notebook".
             *
             * A verificação considera duas possíveis estruturas de URL para a página de resultados:
             * 1.  Contendo "k=notebook":  Parâmetro tradicional para pesquisa.
             * 2.  Contendo "s?k=":  Parâmetro usado em URLs mais recentes ou com filtros/ordenações.
             *
             * @throws AssertionError se a URL atual não contiver nenhuma das strings esperadas, com uma mensagem
             *                           indicando o redirecionamento incorreto e a URL atual.
             */
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue("Redirecionamento incorreto. URL atual: " + currentUrl,
                    currentUrl.contains("k=notebook") || currentUrl.contains("s?k="));

            // Aguarda o título da página de resultados usando o XPath fornecido
            WebElement tituloResultado = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='search']/span/div/h1/div/div[1]/div/h2")));

            Assert.assertTrue("Título da página de resultados não visível",
                    tituloResultado.isDisplayed());
            /*
             * Captura qualquer exceção que ocorra durante a validação do redirecionamento e conteúdo da página.
             * Lança uma AssertionError com uma mensagem detalhada que inclui a mensagem da exceção original,
             * facilitando a identificação da causa raiz do problema.
             *
             * @param e A exceção ocorrida.
             * @throws AssertionError Uma AssertionError contendo a mensagem de erro e a exceção original como causa.
             */
        } catch (Exception e) {
            throw new AssertionError("Erro ao validar redirecionamento e conteúdo da página: " + e.getMessage(), e);
        } finally {
            driver.quit();
        }
    }

    @Dado("que acesso a aplicação com largura de tela maior ou igual a 1024px")
    public void queAcessoAAplicacaoComLarguraDeTelaMaiorOuIgualA1024px() {
        // Limpa todos os cookies do navegador para garantir um estado limpo antes de iniciar o teste
        driver.manage().deleteAllCookies();
        // Abre o site da Amazon
        driver.get("http://amazon.com.br/?_encoding=UTF8&ref_=nav_logo");
        // Força a janela a ser redimensionada para 1280x800
        driver.manage().window().setSize(new Dimension(1280, 800));
    }

    @Entao("o menu deve estar visível na Amazon")
    public void oMenuDeveEstarVisivelNaAmazon() {
        // Verifica se o menu está visível
        WebElement menu = driver.findElement(By.id("nav-main"));
        // Valida se o menu está visível
        Assert.assertTrue("O menu não está visível", menu.isDisplayed());
        driver.quit();
    }

    @Dado("que acesso a aplicação com largura de tela menor ou igual a 768px")
    public void queAcessoAAplicacaoComLarguraDeTelaMenorOuIgualA768px() {
        // Configuração do WebDriverManager para garantir a versão correta do ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configura o mapa de emulação do dispositivo móvel (ex: Pixel 2)
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");

        // Configurações do Chrome para emulação do dispositivo
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Limpa cookies para evitar problemas de sessão
        driver.manage().deleteAllCookies();

        // Abre o site
        driver.get("http://amazon.com.br/?_encoding=UTF8&ref_=nav_logo");

        // Forçar a janela a ser redimensionada para emular o tamanho do dispositivo
        driver.manage().window().setSize(new Dimension(411, 731)); // Tamanho típico do Pixel 2 em modo retrato
    }

    @Entao("^o ícone de menu hamburger deve ser exibido$")
    public void o_ícone_de_menu_hamburger_deve_ser_exibido() {
        // Verifica se o ícone de menu hamburger está visível
        WebElement hamburger = driver.findElement(By.id("nav-hamburger-menu"));
        // Valida se o ícone de menu hamburger está visível
        Assert.assertTrue("O ícone de menu hamburger não está visível", hamburger.isDisplayed());
        driver.quit();
    }

    @Quando("clico em um item do menu")
    public void clicoEmUmItemDoMenu() {
        // Localiza todos os itens do menu
        List<WebElement> items = driver.findElements(By.cssSelector(".hmenu-item"));
        // Verifica se a lista de itens não está vazia
        if (!items.isEmpty()) {
            // Se houver itens, clica no primeiro
            items.get(0).click();
        }
    }

    @Entao("devo ser redirecionado para a página correspondente")
    public void devoSerRedirecionadoParaAPaginaCorrespondente() {
        // Obtém a URL atual da página no navegador.
        String url = driver.getCurrentUrl();
        // Valida se a URL contém o texto esperado
        Assert.assertTrue("A URL não contém o esperado", url.contains("https://www.amazon.com.br/?_encoding=UTF8&ref_=nav_logo"));
        driver.quit();
    }
}
