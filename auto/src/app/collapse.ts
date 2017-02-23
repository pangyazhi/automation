import { Directive, Input, HostBinding, HostListener, ElementRef } from '@angular/core';
@Directive({ selector: '[collapse]' })
export class CollapseDirective {

    private toShow = true;

    @HostListener('click', ['$event'])
    onclick() {
        let div = this.el.nativeElement.querySelector('div');
        if (this.toShow) {
            this.toShow = false;
            div.style.display = 'none';
        } else {
            this.toShow = true;
            div.style.display = 'block';
        }
    }

    constructor(public el: ElementRef) {
        // this.measureHeight();
    }

}
