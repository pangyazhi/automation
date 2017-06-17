import { Component, OnInit } from '@angular/core';
import {TerminalModule} from 'primeng/primeng';

@Component({
  selector: 'app-terminal',
  templateUrl: './terminal.component.html',
  styleUrls: ['./terminal.component.css']
})
export class TerminalComponent implements OnInit {

  constructor() { }

  response: string;

    onCommand(event) {
        //event.command = entered command
        //in a real application connect to remote service to process the command and update the response field in return
        this.response = 'Unknown command: ' +event.command;
    }

  ngOnInit() {
  }

}
