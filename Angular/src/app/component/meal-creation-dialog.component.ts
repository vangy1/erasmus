import {Component, Inject, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {environment} from "../../environments/environment";
import {FileUploader} from "ng2-file-upload";
import {Meal} from "../dto/meal";
import {HttpClient} from "@angular/common/http";
import {CommunicationService} from "../service/communication.service";

@Component({
  selector: 'app-meal-creation-dialog',
  templateUrl: './meal-creation-dialog.component.html',
  styleUrls: ['./meal-creation-dialog.component.scss']
})
export class MealCreationDialogComponent {
  uploader: FileUploader = new FileUploader({url: environment.apiUrl + '/meal/picture', itemAlias: 'picture'});

  @ViewChild('picture') fileInput;

  uploadProgress: number = 0;
  pictureUpload = false;
  meal = new Meal(null);


  constructor(
    public dialogRef: MatDialogRef<MealCreationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient, public adminService: CommunicationService) {
  }


  uploadPicture() {
    try {
      this.uploader.addToQueue(this.fileInput.nativeElement.files);

      this.uploader.queue[0].onProgress = (progress) => {
        this.uploadProgress = progress;
        this.pictureUpload = true;
      };
      this.uploader.queue[0].onComplete = (response) => {
        this.uploadProgress = 100;
        this.meal.pictureUrl = response;
      };

      this.uploader.uploadAll();
    } catch (error) {
      // Error
    } finally {
      this.fileInput.nativeElement.value = '';
    }
  }

  createMeal() {
    this.adminService.createMeal(this.meal);
    this.dialogRef.close()
  }
}
