import { Output, EventEmitter, Input, HostListener, Directive, HostBinding } from '@angular/core';

export interface DropTargetOptions {
  zone?: string;
  data?: any;
}

@Directive({
  selector: '[appDropTarget]'
})
export class DropTargetDirective {
  @Input()
  set myDropTarget(options: DropTargetOptions) {
    if (options) {
      this.options = options;
    }
  }

  @Output('myDrop') drop = new EventEmitter();

  private options: DropTargetOptions = {};

  @HostListener('dragover', ['$event'])
  onDragOver(event) {
    const { zone = 'zone', data = {} } = this.options;

    if (event.dataTransfer.types.indexOf(`application/x-${zone}`) >= 0) {
      event.preventDefault();
    }
  }

  @HostListener('drop', ['$event'])
  onDrop(event) {
    const { zone = 'zone' } = this.options;

    const data = JSON.parse(event.dataTransfer.getData(`application/x-${zone}`));

    this.drop.next(data);
  }
}