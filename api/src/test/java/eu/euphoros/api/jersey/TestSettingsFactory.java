package eu.euphoros.api.jersey;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.NoArgsConstructor;
import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

@NoArgsConstructor
public class TestSettingsFactory implements Factory<Settings> {

  @Inject
  private Log log;
  @Inject
  private SystemProperties system;

  public TestSettingsFactory(final Log log) {
    this.log = log;
  }

  @Override
  public Settings provide() {
    final String filePath = "/Test.json";
    log.log("Reading " + filePath);
    final InputStream in = this.getClass().getResourceAsStream(filePath);
    final InputStreamReader reader = new InputStreamReader(in);
    final JsonObject json = new Gson().fromJson(reader, JsonObject.class);
    return new Settings(json);
  }

  @Override
  public void dispose(final Settings instance) {
    // TODO Auto-generated method stub

  }

}
