package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.service.Chat;
import eu.ideell.api.service.model.ChatMessage;

@Controller("public/chat")
public class ChatApi {

  @Autowired
  private Chat chat;

  @PostMapping
  public void post(final ChatMessage message) {
    chat.push(message);
  }
}
