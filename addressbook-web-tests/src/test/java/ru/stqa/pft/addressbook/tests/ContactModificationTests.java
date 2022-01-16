package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.AssertJUnit.assertEquals;

public class ContactModificationTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.goTo().gotoHomePage();
      app.contact().create(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("Походная 7").withMobilePhone("89096604532"));
    }
  }

  @Test
  public void testContactModification(){
    app.goTo().gotoHomePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact=before.iterator().next();
    ContactData contact= new ContactData().withId(modifiedContact.getId()).withFirstname("firstname_update").withLastname("lastname_update").withAddress("Походная 7").withMobilePhone("89096604539");
    app.goTo().gotoHomePage();
    app.contact().modify(contact);
    app.goTo().gotoHomePage();
    app.wait_sec();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }



}
