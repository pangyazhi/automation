import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { MaterialModule } from '@angular/material';
import {MdCardModule} from '@angular/material/card';
import {MdButtonModule} from '@angular/material/button';
import {MdIconModule} from '@angular/material/icon';
import {MdIconRegistry} from '@angular/material/icon';
import {DialogsModule} from './dialog/dialog.module';
import { DraggableDirective } from './dnd/draggable.directive';
import { DropTargetDirective } from './dnd/drop-target.directive';
import { CollapseDirective } from './collapse';
import {Slide} from './carousel/slide.component';
import {CarouselComponent, Direction} from  './carousel/carousel.component';

@NgModule({
  declarations: [
    AppComponent, DraggableDirective, DropTargetDirective, CollapseDirective, Slide, CarouselComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule.forRoot(),
    DialogsModule,
    HttpModule
  ],
  providers: [],
  entryComponents: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
