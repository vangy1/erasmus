import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {HttpClient} from "@angular/common/http";
import {Meal} from "../dto/meal";

@Component({
  selector: 'app-meal-details-dialog',
  templateUrl: './meal-details-dialog.component.html',
  styleUrls: ['./meal-details-dialog.component.scss']
})
export class MealDetailsDialogComponent implements OnInit {
  meal: Meal;

  constructor(
    public dialogRef: MatDialogRef<MealDetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient) {
    this.meal = data.meal;
  }

  ngOnInit() {
  }

}
