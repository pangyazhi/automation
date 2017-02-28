import { Component, Input, ElementRef } from '@angular/core';

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'simple-card',
    templateUrl: 'simpleCard.component.html'
})
export class SimpleCardComponent {
    private _data: any;
    @Input() public set data(value: any) {
        this._data = value;
    }
}
