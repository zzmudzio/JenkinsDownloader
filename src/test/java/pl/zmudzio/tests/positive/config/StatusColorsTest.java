package pl.zmudzio.tests.positive.config;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.zzmudzio.config.StatusColors;

public class StatusColorsTest {
    @Test(priority=0)
    public void verifyAnsiCodes() {
        StatusColors stateColor;
        System.out.println("Weryfikacja koloru w przypadku błędu.");
        Assert.assertEquals((stateColor = StatusColors.ERROR).getAnsiCode(), "\u001B[31m");
        System.out.println("Weryfikacja koloru w przypadku sukcesu.");
        Assert.assertEquals((stateColor = StatusColors.SUCCESS).getAnsiCode(), "\u001B[32m");
        System.out.println("Weryfikacja kodu resetowania kolorów.");
        Assert.assertEquals((stateColor = StatusColors.RESET).getAnsiCode(), "\u001B[0m");
    }
}
