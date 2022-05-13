package uk.co.library.testsuite;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;
import uk.co.library.pages.HomePage;
import uk.co.library.pages.ResultPage;
import uk.co.library.testbase.TestBase;
public class JobSearchTest extends TestBase {

    HomePage homePage;
    ResultPage resultPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        resultPage = new ResultPage();
        PageFactory.initElements(driver, this);
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class, groups = {"sanity", "smoke", "regression"})
    public void verifyJobSearchResultUsingDifferentDataSet(String jobTitle, String location,
                                                           String distance, String salaryMin, String salaryMax,
                                                           String salaryType, String jobType, String result) throws InterruptedException {

        homePage.acceptCookies();
        homePage.enterJobTitle(jobTitle);
        homePage.enterLocation(location);
        homePage.selectDistance(distance);
        homePage.clickOnMoreSearchOptionLink();
        homePage.enterMinSalary(salaryMin);
        homePage.enterMaxSalary(salaryMax);
        homePage.selectSalaryType(salaryType);
        homePage.selectJobType(jobType);
        homePage.clickOnFindJobsButton();
        String actual=resultPage.getText();
        String expected=result;
        Thread.sleep(2000);
        Assert.assertEquals(actual,expected);

    }
}