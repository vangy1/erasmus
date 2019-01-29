import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './component/app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatCardModule, MatIconModule, MatInputModule} from "@angular/material";
import {LoginComponent} from './component/login.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {HttpClientModule} from "@angular/common/http";
import {VoterComponent} from './component/voter.component';
import {AdminComponent} from './component/admin.component';
import {FormsModule} from "@angular/forms";
import {MatSelectModule} from '@angular/material/select';
import {MealCreationDialogComponent} from './component/meal-creation-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MealDetailsDialogComponent} from './component/meal-details-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    VoterComponent,
    AdminComponent,
    MealCreationDialogComponent,
    MealDetailsDialogComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule,
    MatDialogModule,
    MatIconModule,
    MatProgressBarModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [MealCreationDialogComponent, MealDetailsDialogComponent]

})
export class AppModule {
}
