package eu.ideell.api.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import eu.ideell.api.service.Chat;
import eu.ideell.api.service.model.ChatMessage;

@Path("public/chat")
public class ChatApi {

  @Inject
  private Chat chat;

  @POST
  public void post(final ChatMessage message) {
    chat.push(message);
  }
}
