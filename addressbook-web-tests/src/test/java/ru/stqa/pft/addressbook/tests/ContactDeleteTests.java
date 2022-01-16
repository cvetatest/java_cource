package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.AssertJUnit.assertEquals;

public class ContactDeleteTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoHomePage();
    if(app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("Походная 7").withMobilePhone("89096604532"));
    }
  }

  @Test
  public void testContactDelete(){
    app.goTo().gotoHomePage();
    Contacts before =app.db().contacts();
    ContactData deleteContact=before.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().gotoHomePage();
    app.wait_sec();
    Contacts after =app.db().contacts();
    assertEquals(after.size(),before.size()-1);

    assertThat(after, equalTo(before.without(deleteContact)));
  }


}
