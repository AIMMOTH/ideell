package eu.ideell.api.jaxrs;

import static com.google.cloud.vision.v1.Likelihood.VERY_UNLIKELY;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.datastore.entity.VisionResult;
import eu.ideell.api.service.model.ImageServeUrl;
import eu.ideell.api.service.model.ImageUploadResource;
import se.cewebab.stockholm.appengine.Image;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.vision.Vision;
import se.cewebab.stockholm.vision.model.Result;

@Path("image")
public class ImageApi {

  @Context
  private HttpServletRequest request;
  @Inject
  private Log log;
  @Inject
  private Image image;
  @Inject
  private Vision vision;
  @Inject
  private SecureDatastoreImpl datastore;

  private Predicate<Result> explicitImage = text ->
    text.getAdult() != VERY_UNLIKELY || text.getMedical() != VERY_UNLIKELY
    ;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed({"admin", "user"})
  public ImageUploadResource get() {
    return new ImageUploadResource(image.createUploadUrl("/api/v1/image/"));
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public ImageServeUrl post() {
    final String url = image.getServingUrl(request, "myFile");
    final ImageServeUrl result = new ImageServeUrl(url);
    try {
      final List<Result> texts = vision.getTexts(url);
      datastore.saveEntityAsSystem(new VisionResult(texts));
      if (texts.stream().filter(explicitImage).findAny().isPresent()) {
        return new ImageServeUrl("https://stockholm-framework-template.appspot.com/assets/logo.png");
      }
    }
    catch (final IOException e) {
      log.warning(e);
    }
    return result;
  }
}
