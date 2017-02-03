import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  template: `<h1>Search Address</h1>
  <input ng2-auto-complete [(ngModel)]="searchStr" [source]="googleGeoCode" display-property-name="formatted_address"
  placeholder="Enter Address(min. 2 chars)" min-chars="2" path-to-data="results" />
            `
})
// <ng2-completer [(ngModel)]="searchStr" [dataService]="dataService" 
//             [minSearchLength]="0"  ></ng2-completer>
export class AppComponent {
  searchStr: string;
  searchData = [
    'red', 'blue', 'black'
  ];
  googleGeoCode: string = 'https://maps.googleapis.com/maps/api/geocode/json?address=:keyword';

}
