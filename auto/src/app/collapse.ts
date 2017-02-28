import { Directive, Input, HostBinding, HostListener, ElementRef } from '@angular/core';
@Directive({ selector: '[collapse]' })
export class CollapseDirective {

    private toShow = false;

    @HostListener('dblclick', ['$event'])
    onclick() {
        let div = this.el.nativeElement.querySelector('.collapse');
        if (div) {
            if (this.toShow) {
                this.toShow = false;
                div.style.display = 'none';
            } else {
                this.toShow = true;
                div.style.display = 'block';
            }
        }

    }

    ngAfterViewInit() {
        let div = this.el.nativeElement.querySelector('.collapse');
        div.style.display = 'none';
    }

    constructor(public el: ElementRef) {

    }

}
