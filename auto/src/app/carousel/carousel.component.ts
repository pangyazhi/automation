import { Component, OnDestroy, Input } from '@angular/core';
import { NgFor } from '@angular/common';
import { Slide } from './slide.component';

export enum Direction { UNKNOWN, NEXT, PREV }

@Component({
    selector: 'carousel',
    providers: [],
    template: `
    <div (mouseenter)="pause()" (mouseleave)="play()" class="w3-content w3-display-container">
      
         <li *ngFor="let slidez of slides" [class.active]="slidez.active === true" (click)="select(slidez)"></li>

      <div class="w3-center w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
        <div class="w3-left w3-padding-left w3-hover-text-khaki" onclick="prev()">&#10094;</div>
        <div class="w3-right w3-padding-right w3-hover-text-khaki" onclick="next()">&#10095;</div>
    </div>
  `
})
export class CarouselComponent implements OnDestroy {
    private slides: Array<Slide> = [];
    private currentInterval: any;
    private isPlaying: boolean;
    private destroyed: boolean;
    private currentSlide: Slide;
    private _interval: number;

    @Input() public noWrap: boolean;
    @Input() public noPause: boolean;
    @Input() public noTransition: boolean;

    @Input() public get interval(): number {
        return this._interval;
    }

    public set interval(value: number) {
        this._interval = value;
        this.restartTimer();
    }

@Input() public get Slides(): Array<any>{
    return this.slides;
}

public set Slides(value: Array<any>){
    this.slides = value;
}


    public ngOnDestroy() {
        this.destroyed = true;
    }

    public select(nextSlide: Slide) {
        let nextIndex = nextSlide.index;

        // Prevent this user-triggered transition from occurring if there is already one in progress
        if (nextSlide && nextSlide !== this.currentSlide) {
            this.goNext(nextSlide);
        }
    }

    private goNext(slide: Slide) {
        if (this.destroyed) {
            return;
        }

        
        slide.active = true;

        if (this.currentSlide) {
            
            this.currentSlide.active = false;
        }

        this.currentSlide = slide;

        // every time you change slides, reset the timer
        this.restartTimer();
    }

    private getSlideByIndex(index: number) {
        let len = this.slides.length;
        for (let i = 0; i < len; ++i) {
            if (this.slides[i].index === index) {
                return this.slides[i];
            }
        }
    }

    private getCurrentIndex() {
        return !this.currentSlide ? 0 : this.currentSlide.index;
    }

    public next() {
        let newIndex = (this.getCurrentIndex() + 1) % this.slides.length;

        if (newIndex === 0 && this.noWrap) {
            this.pause();
            return;
        }

        return this.select(this.getSlideByIndex(newIndex));
    }

    public prev() {
        let newIndex = this.getCurrentIndex() - 1 < 0 ? this.slides.length - 1 : this.getCurrentIndex() - 1;

        if (this.noWrap && newIndex === this.slides.length - 1) {
            this.pause();
            return;
        }

        return this.select(this.getSlideByIndex(newIndex));
    }

    private restartTimer() {
        this.resetTimer();
        let interval = +this.interval;
        if (!isNaN(interval) && interval > 0) {
            this.currentInterval = setInterval(() => {
                let nInterval = +this.interval;
                if (this.isPlaying && !isNaN(this.interval) && nInterval > 0 && this.slides.length) {
                    this.next();
                } else {
                    this.pause();
                }
            }, interval);
        }
    }

    private resetTimer() {
        if (this.currentInterval) {
            clearInterval(this.currentInterval);
            this.currentInterval = null;
        }
    }

    public play() {
        if (!this.isPlaying) {
            this.isPlaying = true;
            this.restartTimer();
        }
    }

    public pause() {
        if (!this.noPause) {
            this.isPlaying = false;
            this.resetTimer();
        }
    }

    public addSlide(slide: Slide) {
        slide.index = this.slides.length;
        this.slides.push(slide);
        if (this.slides.length === 1 || slide.active) {
            this.select(this.slides[this.slides.length - 1]);
            if (this.slides.length === 1) {
                this.play();
            }
        } else {
            slide.active = false;
        }
    }

    public removeSlide(slide: Slide) {
        this.slides.splice(slide.index, 1);

        if (this.slides.length === 0) {
            this.currentSlide = null;
            return;
        }

        for (let i = 0; i < this.slides.length; i++) {
            this.slides[i].index = i;
        }
    }
}

