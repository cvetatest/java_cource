package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {

    app.gotoNewContact();
    app.fillContactForm(new ContactData("firstname","lastname","Походная 7","89096604532"));
    app.submitContactCreation();
    app.gotoHomePage();
  }

}