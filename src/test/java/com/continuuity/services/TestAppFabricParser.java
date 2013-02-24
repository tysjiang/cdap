package com.continuuity.services;

import com.continuuity.app.services.AppFabricNGClient;
import com.continuuity.common.conf.CConfiguration;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test app-fabric command parsing
 */
public class TestAppFabricParser {

  @Test
  public void testOptionsParsing() throws ParseException {
    String[] args = {"deploy", "-jar", "jar"};
    AppFabricNGClient client = new AppFabricNGClient();
    client.configure(CConfiguration.create(), args);
    assert (client!=null);
    assertTrue("deploy".equals(client.getCommand()));
  }


  @Test
  public void testUnknownCommands() throws ParseException {
    boolean CatchUnknownCommandException = false;
    try {
      AppFabricNGClient client = new AppFabricNGClient();
      String command = client.configure(CConfiguration.create(),new String[]{"Foobaz", "-jar", "jar"});
    } catch (RuntimeException e) {
      CatchUnknownCommandException = true;
    }
    assertTrue(CatchUnknownCommandException);
  }

  @Test
  public void testValidInvalidDeployArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"deploy"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
  }

  @Test
  public void testValidInvalidVerifyArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"verify", "--application", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
  }

  @Test
  public void testValidInvalidStartArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"start", "--application", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
    try {
      command = client.configure(CConfiguration.create(),new String[]{"start", "--processor", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
  }

  @Test
  public void testValidInvalidStopArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"stop", "--application", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
    try {
      command = client.configure(CConfiguration.create(),new String[]{"stop", "--processor", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
  }


  @Test
  public void testValidInvalidStatusArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"status", "--application", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
    try {
      command = client.configure(CConfiguration.create(),new String[]{"status", "--processor", "args"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
  }


  @Test
  public void testValidInvalidPromoteArgs() throws ParseException {
    AppFabricNGClient client = new AppFabricNGClient();
    String command = null;
    try {
      command = client.configure(CConfiguration.create(),new String[]{"promote", "--vpc", "vpc_name",
        "--application", "application"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }
    try {
      command = client.configure(CConfiguration.create(),new String[]{"promote", "--vpc", "vpc_name",
        "--application", "application"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }

    try {
      command = client.configure(CConfiguration.create(),new String[]{"promote",
        "--authtoken", "Auth token",
        "--application", "application"});
      assertTrue(false);//This condition should not occur
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(command == null);
    }

  }

  @Test
  public void testValidArguments() throws ParseException {

    AppFabricNGClient client = new AppFabricNGClient();
    try {
      String command = null;
      command = client.configure(CConfiguration.create(),new String[]{"deploy", "--resource", "jar"});
      assertTrue("deploy".equals(command));
      command = client.configure(CConfiguration.create(),new String[]{"verify", "--resource", "jar"});
      assertTrue("verify".equals(command));
      command = client.configure(CConfiguration.create(),new String[]{"start", "--application", "appId",
        "--processor", "processor"});
      assertTrue("start".equals(command));
      command = client.configure(CConfiguration.create(),new String[]{"stop", "--application", "appId",
        "--processor", "processor"});
      assertTrue("stop".equals(command));
      command = client.configure(CConfiguration.create(),new String[]{"status", "--application", "appId",
        "--processor", "processor"});
      assertTrue("status".equals(command));
      command = client.configure(CConfiguration.create(),new String[]{"promote", "--vpc", "vpc_name",
        "--authtoken", "Auth token",
        "--application", "application"});
      assertTrue("promote".equals(command));
    } catch (Exception e) {
      //This case should not occur in this test
      assertTrue(false);
    }
  }
}
