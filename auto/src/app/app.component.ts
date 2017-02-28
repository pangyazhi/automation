import { Component, Optional, ViewContainerRef } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar, MdCheckbox } from '@angular/material';
import { Http, Response } from '@angular/http';
import { DialogsService } from './dialog/dialog.service';
import { DraggableDirective } from './dnd/draggable.directive';
import { DropTargetDirective } from './dnd/drop-target.directive';
import { CarouselComponent  } from './carousel/carousel.component';
import { SimpleCardComponent } from './ui/simpleCard.component';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})

export class AppComponent {
  isDarkTheme = false;
  lastDialogResult = false;
simplecarddata: any;
  commands: any[];
  objects: any[];
  search = '';
  check = false;
  indeterminate = false;
  disabled = false;
  messages: Array<string> = [''];
// info: any[];

  // The time to show the next photo
  private NextPhotoInterval = 5000;
  // Looping or not
  private noLoopSlides = true;
  // Photos
  private slides: Array<any> = [];



  private addNewSlide() {
    this.slides.push(
      { description: 'THis is a nonsense description.', text: 'BMW 1' },
      { image: 'http://www.angulartypescript.com/wp-content/uploads/2016/03/car2.jpg', text: 'BMW 2' },
      { image: 'http://www.angulartypescript.com/wp-content/uploads/2016/03/car3.jpg', text: 'BMW 3' },
      { image: 'http://www.angulartypescript.com/wp-content/uploads/2016/03/car4.jpg', text: 'BMW 4' },
      { image: 'http://www.angulartypescript.com/wp-content/uploads/2016/03/car5.jpg', text: 'BMW 5' },
      { image: 'http://www.angulartypescript.com/wp-content/uploads/2016/03/car6.jpg', text: 'BMW 6' }
    );
  }

  private removeLastSlide() {
    this.slides.pop();
  }
  constructor(private http: Http,
    private dialogsService: DialogsService, private viewContainerRef: ViewContainerRef,
    private _snackbar: MdSnackBar) {

    this.http.request('../data/Commands.json')
      .subscribe((response: Response) => this.commands = response.json());
    this.http.request('../data/findAll.json')
      .subscribe((response: Response) => this.objects = response.json());
      //   this.http.request('https://data.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=4744')
      // .subscribe((response: Response ) => this.info = response.json());
      // this.renewBusInfo();
    setInterval(() => {
      let msg = this.messages.pop();
      this.showMessage(msg);
      //this.renewBusInfo();
    }, 5000);
    // Update the value for the progress-bar on an interval.
    // setInterval(() => {
    //   this.progress = (this.progress + Math.floor(Math.random() * 4) + 1) % 100;
    // }, 200);
    this.addNewSlide();
    
  }

  // renewBusInfo(){
  //   var msg: any[];
  //       this.http.request('https://data.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=4744')
  //     .subscribe((response: Response ) => msg = response.json().main);
  //     this.info = msg;
  //     console.log("raw: "+msg);
  //     console.log("info: "+this.info);
  // }

  onDrop(data: any) {
    console.log('drop data:' + data);
    this.pushMessage(data);
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

  clearCommandBox() {
    
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
}



