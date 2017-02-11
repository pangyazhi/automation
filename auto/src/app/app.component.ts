import { Component, Optional, ViewContainerRef } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar, MdCheckbox } from '@angular/material';
import { Http, Response } from '@angular/http';
import { DialogsService } from './dialog/dialog.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})

export class AppComponent {
  isDarkTheme: boolean = false;
  lastDialogResult: boolean = false;

  commands: any[];
  search = '';
  check = false;
  indeterminate = false;
  disabled = false;
  messages: Array<string> = [''];


  constructor(private http: Http,
    private dialogsService: DialogsService, private viewContainerRef: ViewContainerRef,
    private _snackbar: MdSnackBar) {

    this.http.request('../data/Commands.json')
      .subscribe((response: Response) => this.commands = response.json());
    setInterval(() => {
      let msg = this.messages.pop();
      this.showMessage(msg); }, 5000);
    // Update the value for the progress-bar on an interval.
    // setInterval(() => {
    //   this.progress = (this.progress + Math.floor(Math.random() * 4) + 1) % 100;
    // }, 200);
  }

  pushMessage(message: string) {
    this.messages.push(message);
  }

  openDialog() {
    let dialogRef = this.dialogsService.confirm('Confirm Please',
      'Are you sure you want to do this?', this.viewContainerRef).subscribe(result => {
        this.lastDialogResult = result;
      });
  }

  openDialogValue(value: string) {
    let dialogRef = this.dialogsService.confirm('Confirm Please',
      value, this.viewContainerRef).subscribe(result => {
        this.showMessage(value);
        this.lastDialogResult = result;
      });
  }

  showMessage(message: string) {
    if (message) {
      if (message.length > 0) {
        this._snackbar.open(message, 'x', {
          duration: 4000,
          announcementMessage: message,
        });
      }
    }


  }
  showSnackbar() {
    this._snackbar.open('YUM SNACKS', 'CHEW');
  }
}



