package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneEmailAddressTest extends TestBase{
  @Test
  public void testContactPhoneEmailAddress(){
    app.goTo().gotoHomePage();
    ContactData contact=app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s)->!s.equals(""))
            .map(ContactPhoneEmailAddressTest::cleaned)
            .collect(Collectors.joining("\n"));
  }
  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining(""));
  }
  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)->!s.equals(""))
            .map(ContactPhoneEmailAddressTest::cleaned)
            .collect(Collectors.joining("\n"));
  }
  public static String cleaned(String data){
    return data.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
