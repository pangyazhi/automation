import { Component, OnDestroy, Input, ElementRef } from '@angular/core';
import { NgFor } from '@angular/common';

@Component({
    selector: 'carousel',
    template: `
    <md-card style="width: auto">        
            <a class="w3-btn-floating w3-hover-dark-grey w3-display-left" (click)="prev()">&#10094;</a>
            <a class="w3-btn-floating w3-hover-dark-grey w3-display-right" (click)="next()">&#10095;</a>        
         <div class="w3-center carousel" *ngFor="let slidez of slides" style="display: none">
            <img src={{slidez.image}} />
            <div class="w3-display-bottomright w3-large w3-container w3-padding-16 w3-black">
                {{slidez.text}}
            </div>
         </div>      
    </md-card>
  `
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

