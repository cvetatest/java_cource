package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {

    app.getContactHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("firstname","lastname","Походная 7","89096604532"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }

}