package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationTests() throws Exception {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    File photo =new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("Походная 7").withPhoto(photo);
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    app.wait_sec();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
public void testCurrentDir(){
    File currentDir=new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo =new File("src/test/resources/stru.png");
    System.out.println(currentDir.getAbsolutePath());
    System.out.println(photo.exists());
}
}