package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names="-c", description="Contact count")
  public  int count;
  @Parameter(names="-f", description="Target file")
  public String file;
  @Parameter(names="-d", description="Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator= new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch(ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    }else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    }else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format"+format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer=new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer=new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private  void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer=new FileWriter(file);
    for(ContactData group:contacts){
      writer.write(String.format("%s;%s;%s\n",group.getLastname(),group.getFirstname(),group.getAddress(),group.getEmail()));
    }
    writer.close();
  }


  private  List<ContactData> generateContacts(int count) {
    List<ContactData> contacts=new ArrayList<ContactData>();
    for(int i=0;i<count; i++){
      contacts.add(new ContactData().withLastname(String.format("lastname %s",i))
              .withFirstname(String.format("firstname %s",i)).withAddress(String.format("Походная %s",i)).withEmail(String.format("hello%s@mail.ru",i)).withMobilePhone(String.format("8909660453%s",i)));
    }
    return contacts;
  }
}