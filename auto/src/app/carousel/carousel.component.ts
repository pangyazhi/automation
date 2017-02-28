import { Component, OnDestroy, Input, ElementRef } from '@angular/core';

@Component({
    selector: 'carousel',
    templateUrl: 'carousel.component.html'
})
export class CarouselComponent {
    private slides: Array<any> = [];
    slideIndex = 0;

    @Input() public set Slides(value: Array<any>) {
        this.slides = value;
    }

    constructor(public el: ElementRef) {
    }

    ngAfterViewInit() {
        let lis = this.el.nativeElement.querySelectorAll('.carousel');
        this.slideIndex = 0;
        lis[this.slideIndex].style.display = 'block';
    }

    public next() {
        let lis = this.el.nativeElement.querySelectorAll('.carousel');
        lis[this.slideIndex].style.display = 'none';
        this.slideIndex += 1;
        if (this.slideIndex >= lis.length) {
            this.slideIndex = 0;
        }
        lis[this.slideIndex].style.display = 'block';
    }

    public prev() {
        let lis = this.el.nativeElement.querySelectorAll('.carousel');
        lis[this.slideIndex].style.display = 'none';
        this.slideIndex -= 1;
        if (this.slideIndex < 0) {
            this.slideIndex = lis.length - 1;
        }
        lis[this.slideIndex].style.display = 'block';
    }
}

