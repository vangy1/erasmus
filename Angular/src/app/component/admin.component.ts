import {Component} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {MatDialog, MatIconRegistry, MatSnackBar} from "@angular/material";
import {MealCreationDialogComponent} from "./meal-creation-dialog.component";
import {DomSanitizer} from "@angular/platform-browser";
import {Meal} from "../dto/meal";
import {HttpClient} from "@angular/common/http";
import {MealDetailsDialogComponent} from "./meal-details-dialog.component";
import {CommunicationService} from "../service/communication.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent {
  votingHistoryExpanded = false;
  mealsExpanded = false;
  suggestionsExpanded = false;

  constructor(public authenticationService: AuthenticationService, public dialog: MatDialog, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer, private http: HttpClient, public snackBar: MatSnackBar, public adminService: CommunicationService) {
    iconRegistry.addSvgIcon('delete-forever', sanitizer.bypassSecurityTrustResourceUrl('/assets/delete-forever.svg'));
    iconRegistry.addSvgIcon('expand-more', sanitizer.bypassSecurityTrustResourceUrl('/assets/expand-more.svg'));
    iconRegistry.addSvgIcon('expand-less', sanitizer.bypassSecurityTrustResourceUrl('/assets/expand-less.svg'));

    this.adminService.getMeals();
    this.adminService.getSuggestions();
    this.adminService.getVotingHistory();
    this.adminService.getCurrentVoting();
  }

  deleteVoting(id: string) {
    this.adminService.deleteVoting(id).subscribe(() => {
      this.snackBar.open("Voting was deleted", null, {
        duration: 2000,
        panelClass: 'snackbar-without-action'
      });
    })
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(MealCreationDialogComponent);
  }


  openMealDetailsDialog(meal: Meal) {
    const dialogRef = this.dialog.open(MealDetailsDialogComponent, {
      width: '900px',
      panelClass: 'dialog-without-padding',
      data: {meal: meal}
    });
  }


  deleteMeal(meal: Meal) {
    if (this.checkIfVotingForMealExists(meal)) {
      this.snackBar.open("Deleting this meal will also delete all the votings associated with it", "Continue", {
        duration: 2000,
      }).onAction().subscribe(() => {
        this.adminService.deleteMeal(meal.id);
      });
    } else {
      this.adminService.deleteMeal(meal.id);
    }
  }


  checkIfVotingForMealExists(meal: Meal) {
    let votingForThisMealExists = false;
    for (let voting of this.adminService.votingHistory) {
      if (meal.name == voting.mealName) {
        votingForThisMealExists = true
      }
    }
    if (this.adminService.currentVoting && meal.name == this.adminService.currentVoting.mealName) {
      votingForThisMealExists = true
    }
    return votingForThisMealExists;
  }

  deleteSuggestion(id) {
    this.adminService.deleteSuggestion(id).subscribe(() => {
      this.snackBar.open("Suggestion was deleted", null, {
        duration: 2000,
        panelClass: 'snackbar-without-action'
      });
    });
  }

  getVotingHistoryExpanded() {
    if (this.votingHistoryExpanded) {
      return this.adminService.votingHistory.length;
    } else {
      return 5;
    }
  }

  getMealsExpanded() {
    if (this.mealsExpanded) {
      return this.adminService.mealsLayout.length;
    } else {
      return 5;
    }
  }

  getSuggestionsExpanded() {
    if (this.suggestionsExpanded) {
      return this.adminService.suggestions.length;
    } else {
      return 5;
    }
  }
}
