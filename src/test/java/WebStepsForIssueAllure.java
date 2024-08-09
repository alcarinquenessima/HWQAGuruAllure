import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebStepsForIssueAllure {
    @Step ("Openning main page")
    public void openMainPage() {
        open ("https://github.com/");

    }


    @Step ("Lookin for repository: {repo}")
    public void searchForRepository(String repo) {
            $("[data-target='qbsearch-input.inputButton']").click();
            $("#query-builder-test").sendKeys(repo);
            $("#query-builder-test").submit();
    }

    @Step ("Openning repository: {repo}")
    public void clickOnRepoLink (String repo){
        $(byLinkText(repo)).click();
    }

    @Step ("Openning issue tab")
    public void openIssueTab () {
        $("#issues-tab").click();
    }

    @Step ("Checking if there is an issue {ISSUE}")
    public void shouldBeIssueWithNumber (int issue){
        $(withText("#" + issue)).should(Condition.exist);

    }
}
