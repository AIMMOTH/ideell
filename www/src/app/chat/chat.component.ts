import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

declare const Pusher: any;

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  chatName = 'ideell-chat';
  pusher: any;
  channel: any;
  messages = [];
  input: string;

  constructor(private api: ApiService) {
    this.pusher = new Pusher('18692e0e009c67c79baf', {
      cluster: 'eu'
    });
    this.channel = this.pusher.subscribe(this.chatName);
    this.channel.bind('ideell-message-event', data => {
      const message = {
        received: new Date(),
        text: data.message
      };
      this.messages = this.messages.concat(message);
    });
  }

  ngOnInit(): void {
  }

  send(): void {
    const body = {
      toChatChannelName: this.chatName,
      message: this.input
    };
    this.api.postPublic('/api/v1/public/chat', body).subscribe(result => {
      console.dir(result);
    });
  }
}