package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper  extends HelperBase{

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }
  public void submitContactCreation() {
   click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getPhone());
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
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
}
