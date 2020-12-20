package eu.ideell.api.jersey;

import java.nio.file.attribute.UserPrincipal;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.factory.BigQueryFactory;
import eu.ideell.api.factory.SettingsFactory;
import eu.ideell.api.factory.SheetsFactory;
import eu.ideell.api.factory.UserPrincipalFactory;
import eu.ideell.api.service.Chat;
import eu.ideell.api.service.ProductExcel;
import eu.ideell.api.service.ProductPdf;
import eu.ideell.api.service.Products;
import eu.ideell.api.service.StoreSearch;
import eu.ideell.api.service.StoreSheets;
import se.cewebab.stockholm.appengine.Image;
import se.cewebab.stockholm.appengine.ImageImpl;
import se.cewebab.stockholm.appengine.Search;
import se.cewebab.stockholm.appengine.SearchImpl;
import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.email.Email;
import se.cewebab.stockholm.excel.Excel;
import se.cewebab.stockholm.excel.ExcelImpl;
import se.cewebab.stockholm.messaging.Messaging;
import se.cewebab.stockholm.pdf.PdfDocument;
import se.cewebab.stockholm.pdf.PdfDocumentImpl;
import se.cewebab.stockholm.sheets.Sheets;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;
import se.cewebab.stockholm.vision.Vision;
import se.cewebab.stockholm.vision.VisionImpl;

@Provider
public class InjectionBinder extends AbstractBinder {

  @Override
  protected void configure() {
    // Log
    bindAsContract(Log.class);
    // Settings
    bindFactory(SettingsFactory.class).to(Settings.class).in(Singleton.class);
    // Other
    bindFactory(UserPrincipalFactory.class).to(UserPrincipal.class);
    bindFactory(Auth0Factory.class).to(Auth0.class);
    bindAsContract(SystemProperties.class);
    bindAsContract(SecureDatastoreImpl.class);
    bindFactory(BigQueryFactory.class).to(BigQueryIntegration.class);
    bind(ExcelImpl.class).to(Excel.class);
    bind(PdfDocumentImpl.class).to(PdfDocument.class);
    bind(VisionImpl.class).to(Vision.class);
    bindFactory(SheetsFactory.class).to(Sheets.class);
    bindFactory(MessageFactory.class).to(Messaging.class);
    bindFactory(EmailFactory.class).to(Email.class);

    bind(ImageImpl.class).to(Image.class);
    bind(SearchImpl.class).to(Search.class);
    bindAsContract(Products.class);
    bindAsContract(StoreSearch.class);
    bindAsContract(StoreSheets.class);
    bindAsContract(ProductExcel.class);
    bindAsContract(ProductPdf.class);
    bindAsContract(Chat.class);
  }

}
