package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if( app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before =app.db().groups();
    GroupData deletedGroup=before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), CoreMatchers.equalTo(before.size()-1));
    Groups after =app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }




}
