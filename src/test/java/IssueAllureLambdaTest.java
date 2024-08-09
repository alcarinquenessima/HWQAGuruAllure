import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.sun.jdi.PrimitiveValue;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueAllureLambdaTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 89;
    @Test
    public void issueSearchStepsTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step ("Openning main page", () -> {
            open ("https://github.com/");
        });
        step ("Lookin for repository: ё" + REPOSITORY,() -> {
            $("[data-target='qbsearch-input.inputButton']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step ("Openning repository: " + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
        });
        step ("Openning issue tab", () -> {
            $("#issues-tab").click();
        });
        step ("Checking if there is an issue" + ISSUE, () -> {
            $(withText("#89")).should(Condition.exist);
        });
    }


    @Test
    public void annotatedAllureTest () {
        WebStepsForIssueAllure step = new WebStepsForIssueAllure();

        step.openMainPage();
        step.searchForRepository(REPOSITORY);
        step.clickOnRepoLink(REPOSITORY);
        step.openIssueTab();
        step.shouldBeIssueWithNumber(ISSUE);


}
}