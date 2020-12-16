package eu.euphoros.api.servlet;

import javax.servlet.annotation.WebFilter;

import se.cewebab.stockholm.servlet.AbstractAHFilter;

/**
 *
 * @author Carl
 *
 */
@WebFilter("/_ah/*")
public class AHFilter extends AbstractAHFilter {

  @Override
  protected boolean implementCsp() {
    return false;
  }

  @Override
  protected String getCspDomains() {
    return ServletConfig.getSettings().getSettingText("CSP");
  }

  @Override
  protected boolean checkOrigin(final String origin) {
    return ServletConfig.getSettings().getSettingList("CORS").contains(origin);
  }

}
