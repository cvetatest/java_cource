package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase{
  @Test
  public void testContactDelete(){
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("firstname","lastname","Походная 7","89096604532"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().submitDelete();
    app.getContactHelper().closeAlert();
  }
}
