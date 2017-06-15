import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.css']
})
export class FieldComponent implements OnInit {

  static fromJson(json: string): FieldComponent {
    return JSON.parse(json);
  }

  // in this component, we will create a field according the input; 
  // we need to consider a lot later: e.g.: drag and drop, validation, complex component, component event etc.
  constructor(
    private type: string,
    private name: string,
    private isDisable: boolean,
    private isVisible: boolean,
    private label: string,
    private data: string,
    private options: string[],
    private accept: string[], // use for drag and drop
    private validation: string[] // user for validation
  ) {

  }



  toJson(): string {
    return JSON.stringify(this);
  }

  ngOnInit() {
    // load metadata here
  }

}


