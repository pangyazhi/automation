import {Component, Optional, ViewContainerRef } from '@angular/core';
import {MdDialog, MdDialogRef, MdSnackBar, MdCheckbox} from '@angular/material';
import { Http, Response } from '@angular/http';
import { DialogsService } from './dialog/dialog.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})

export class AppComponent {
  isDarkTheme: boolean = false;
  lastDialogResult: boolean = false;

  commands: any[] ;
  search = '';
  check = false;
  indeterminate = false;
  disabled = false;

  constructor(private http: Http,
  private dialogsService: DialogsService, private viewContainerRef: ViewContainerRef,
  private _snackbar: MdSnackBar) {

    this.http.request('../data/Commands.json')
            .subscribe((response: Response) => this.commands = response.json());
    // Update the value for the progress-bar on an interval.
    // setInterval(() => {
    //   this.progress = (this.progress + Math.floor(Math.random() * 4) + 1) % 100;
    // }, 200);
  }

  openDialog() {
    let dialogRef = this.dialogsService.confirm('Confirm Please',
    'Are you sure you want to do this?', this.viewContainerRef).subscribe(result => {
      this.lastDialogResult = result;
    });
  }

  showSnackbar() {
    this._snackbar.open('YUM SNACKS', 'CHEW');
  }
}



