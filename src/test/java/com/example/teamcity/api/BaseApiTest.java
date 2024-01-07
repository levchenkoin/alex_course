package com.example.teamcity.api;

import com.example.teamcity.api.generators.TestDataGenerator;
import com.example.teamcity.api.generators.TestData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseApiTest extends BaseTest {
    public TestData testData;

    @BeforeMethod
    public void setupTest() {
        testData = new TestDataGenerator().generate();
    }

    @AfterMethod
    public void cleantest() {
        testData.delete();
    }
}