package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerUtil {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        // Verifica se o driver já foi criado
        if (driver == null) {
            // Configuração do WebDriverManager para garantir a versão correta do ChromeDriver
            WebDriverManager.chromedriver().setup();
            // Cria uma instância do ChromeDriver
            driver = new ChromeDriver();
        }
        return driver;
    }
}
