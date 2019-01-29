import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {HttpClient} from "@angular/common/http";
import {Meal} from "../dto/meal";
import {MealDetailsDialogComponent} from "./meal-details-dialog.component";
import {MatDialog, MatIconRegistry} from "@angular/material";
import {CommunicationService} from "../service/communication.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-voter',
  templateUrl: './voter.component.html',
  styleUrls: ['./voter.component.scss']
})
export class VoterComponent implements OnInit {
  mealName = "";
  suggestionSubmitted: boolean = false;

  votingHistoryExpanded = false;
  mealsExpanded = false;

  constructor(public adminService: CommunicationService, public authenticationService: AuthenticationService, private http: HttpClient, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer, public dialog: MatDialog) {
    iconRegistry.addSvgIcon('expand-more', sanitizer.bypassSecurityTrustResourceUrl('/assets/expand-more.svg'));
    iconRegistry.addSvgIcon('expand-less', sanitizer.bypassSecurityTrustResourceUrl('/assets/expand-less.svg'));

    this.adminService.getMeals();
    this.adminService.getVotingHistory();
    this.adminService.getCurrentVoting();
    this.adminService.getVote();
  }

  ngOnInit() {
  }

  openMealDetailsDialog(meal: Meal) {
    this.dialog.open(MealDetailsDialogComponent, {
      width: '900px',
      panelClass: 'dialog-without-padding',
      data: {meal: meal}
    });
  }

  getColor(rating) {
    if (rating === this.adminService.selectedRating) {
      return "accent";
    } else {
      return "warn";
    }
  }

  selectRating(rating) {
    this.adminService.selectedRating = rating;
    this.adminService.vote(rating);
  }

  createSuggestion() {
    this.adminService.createSuggestion(this.mealName).subscribe(() => {
      this.suggestionSubmitted = true;
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
}
