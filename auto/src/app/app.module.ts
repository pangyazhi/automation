import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes} from '@angular/router';

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
import {CarouselComponent} from  './carousel/carousel.component';
import { SimpleCardComponent } from './ui/simpleCard.component';
import { PageNotFoundComponent } from './not-found.component';

const appRoutes: Routes = [

  {path: '', component: AppComponent },
  { path: '404',  component: PageNotFoundComponent },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  declarations: [
    AppComponent, DraggableDirective, DropTargetDirective, CollapseDirective, CarouselComponent, SimpleCardComponent, PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    DialogsModule,
    HttpModule
  ],
  providers: [],
  entryComponents: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
