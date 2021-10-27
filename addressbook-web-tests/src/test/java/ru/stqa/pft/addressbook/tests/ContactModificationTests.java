package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("firstname","lastname","Походная 7","89096604532"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().submitContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstname_update","lastname_update","Походная 9","89096604539"));
    app.getContactHelper().submitEditContact();

  }

}
