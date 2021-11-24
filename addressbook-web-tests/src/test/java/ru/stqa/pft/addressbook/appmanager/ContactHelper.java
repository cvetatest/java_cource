package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getPhone());
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact(int i) {
    click(By.name("selected[]"));
  }

  public void submitDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void submitContactModification() {
    click(By.xpath("(//img[@alt='Edit'])[1]"));
  }

  public void submitEditContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void createContact(ContactData contact) {
    gotoNewContact();
    fillContactForm(contact);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
   // List<WebElement> cells = elements.findElements(By.tagName("td"));


    for(WebElement element:elements){
      int id= Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
      List<WebElement> cells = element.findElements(By.tagName("td"));

      for(WebElement cell:cells){
        String firstname=cell.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[3]")).getText();
        String lastname=cell.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[2]")).getText();
        ContactData contact=new ContactData(id, firstname, lastname,null,null);
        contacts.add(contact);
        }
      }
    return contacts;
    }

  }

