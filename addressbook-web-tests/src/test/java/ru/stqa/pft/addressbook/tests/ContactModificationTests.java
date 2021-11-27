package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("firstname","lastname","Походная 7","89096604532"));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().submitContactModification(before.size()-1);
    ContactData contact= new ContactData(before.get(before.size()-1).getId(),"firstname_update","lastname_update","Походная 9","89096604539");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitEditContact();
    app.getNavigationHelper().gotoHomePage();
    app.wait_sec();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId=(c1, c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
