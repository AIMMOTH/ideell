package eu.ideell.api.factory;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.NoArgsConstructor;
import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

@NoArgsConstructor
public class SettingsFactory implements Factory<Settings> {

  @Autowired
  private Log log;
  @Autowired
  private SystemProperties system;

  public SettingsFactory(final Log log, final SystemProperties system) {
    this.log = log;
    this.system = system;
  }

  @Override
  public Settings provide() {
    final String filePath = "/" + system.getEnvironment() + ".json";
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
