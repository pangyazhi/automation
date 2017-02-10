import { MdDialogRef } from '@angular/material';
import { Component } from '@angular/core';

@Component ({
    selector: 'app-confirm-dialog',
    template: `
    <p md-dialog-title >{{title}}</p>
    <md-dialog-content>{{message}}</md-dialog-content>
    <md-dialog-actions>
    <button type="button" md-raised-button (click)="dialogRef.close(true)">OK</button>
    <span>&nbsp;</span>
    <button type="button" md-button (click)="dialogRef.close()">Cancel</button>
    </md-dialog-actions>
    `
})
export class ConfirmDialogComponent {
    public title: string;
    public message: string;

    constructor(public dialogRef: MdDialogRef<ConfirmDialogComponent>) {

    }
}
