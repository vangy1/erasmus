import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Meal} from "../dto/meal";
import {Voting} from "../dto/voting";
import {Suggestion} from "../dto/suggestion";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {
  meals: Meal[] = [];
  suggestions: Suggestion[] = [];
  votingHistory: Voting[] = [];
  currentVoting: Voting;

  votingSelectedMealId: string;
  mealsLayout: Meal[][] = [];

  selectedRating = "";

  constructor(private http: HttpClient) {
  }

  getVote() {
    this.http.get(environment.apiUrl + '/vote', {
      withCredentials: true,
      responseType: 'text'
    }).subscribe((response) => {
      this.selectedRating = response;
    });
  }

  vote(rating) {
    const formData = new FormData();
    formData.append('rating', rating);

    this.http.post(environment.apiUrl + '/vote', formData, {
      withCredentials: true,
    }).subscribe((currentVotingJsonObject) => {
      this.currentVotingFromJson(currentVotingJsonObject);
    });
  }

  createSuggestion(mealName) {
    return new Observable(observable => {
      const formData = new FormData();
      formData.append('mealName', mealName);

      this.http.post(environment.apiUrl + '/suggestion', formData, {
        withCredentials: true,
      }).subscribe(() => {
        observable.next();
      });
    })
  }

  getCurrentVoting() {
    this.http.get(environment.apiUrl + '/voting', {
      withCredentials: true
    }).subscribe((currentVotingJsonObject) => {
      this.currentVotingFromJson(currentVotingJsonObject);
    });
  }

  getVotingHistory() {
    this.http.get(environment.apiUrl + '/voting/history', {
      withCredentials: true
    }).subscribe((votingJsonObjects: Array<string>) => {
      this.votingHistoryFromJson(votingJsonObjects);
    });
  }

  startVoting() {
    const formData = new FormData();
    formData.append('mealId', this.votingSelectedMealId);

    this.http.post(environment.apiUrl + '/voting/start', formData, {
      withCredentials: true,
    }).subscribe((votingJsonObject) => {
      this.currentVoting = new Voting(votingJsonObject);
    });
  }

  endVoting() {
    this.http.post(environment.apiUrl + '/voting/end', {
      withCredentials: true
    }).subscribe(((votingJsonObjects: Array<string>) => {
      this.currentVoting = null;

      this.votingHistoryFromJson(votingJsonObjects);
    }));
  }

  deleteVoting(id: string) {
    return new Observable(observable => {
      let params = new HttpParams().append("id", id);
      this.http.delete(environment.apiUrl + '/voting', {
        params: params,
        withCredentials: true
      }).subscribe((votingJsonObjects: Array<string>) => {
        this.votingHistoryFromJson(votingJsonObjects);

        observable.next();
      });
    })
  }

  getMeals() {
    let params = new HttpParams().append("amount", "1");
    this.http.get(environment.apiUrl + '/meal', {
      params: params,
      withCredentials: true
    }).subscribe((mealJsonObjects: Array<string>) => {
      this.mealsFromJson(mealJsonObjects);

      this.setupMealLayout();
    });
  }

  createMeal(meal: Meal) {
    const formData = new FormData();
    formData.append('name', meal.name);
    formData.append('pictureUrl', meal.pictureUrl);
    formData.append('description', meal.description);

    this.http.post(environment.apiUrl + '/meal', formData, {
      responseType: 'text',
      withCredentials: true,
    }).subscribe((response: any) => {
      this.getMeals();
    });
  }

  deleteMeal(id: string) {
    let params = new HttpParams().append("id", id);
    this.http.delete(environment.apiUrl + '/meal', {
      params: params,
      withCredentials: true
    }).subscribe((mealDeletionObject: Array<string>) => {
      this.currentVotingFromJson(mealDeletionObject['currentVoting']);
      this.votingHistoryFromJson(mealDeletionObject['votingHistory']);
      this.mealsFromJson(mealDeletionObject['meals']);

      this.setupMealLayout();
    })
  }

  getSuggestions() {
    this.http.get(environment.apiUrl + '/suggestion', {
      withCredentials: true
    }).subscribe((suggestionJsonObjects: Array<string>) => {
      this.suggestionsFromJson(suggestionJsonObjects);
    });
  }

  deleteSuggestion(id: string) {
    return new Observable(observable => {
      let params = new HttpParams().append("id", id);
      this.http.delete(environment.apiUrl + '/suggestion', {
        params: params,
        withCredentials: true
      }).subscribe((suggestionJsonObjects: Array<string>) => {
        this.suggestionsFromJson(suggestionJsonObjects);
        observable.next();
      });
    });
  }

  private setupMealLayout() {
    this.mealsLayout = [];

    let mealGroups = (this.meals.length + 1) / 2;
    for (let i = 0; i <= mealGroups; i++) {
      this.mealsLayout[i] = [];
    }

    let processedMeal = 0;
    let mealGroup = 0;
    for (let meal of this.meals) {
      this.mealsLayout[mealGroup][processedMeal] = meal;

      processedMeal += 1;
      if (processedMeal === 2) {
        mealGroup += 1;
        processedMeal = 0;
      }
    }

    if (processedMeal === 1) {
      this.mealsLayout[mealGroup][processedMeal] = new Meal(null);
    }
  }

  private currentVotingFromJson(currentVotingJsonObject) {
    console.log(currentVotingJsonObject)
    if (currentVotingJsonObject) {
      this.currentVoting = new Voting(currentVotingJsonObject);
    } else {
      this.currentVoting = null;
    }
  }

  private votingHistoryFromJson(votingJsonObjects) {
    this.votingHistory = [];
    for (let votingJsonObject of votingJsonObjects) {
      this.votingHistory.push(new Voting(votingJsonObject));
    }
  }

  private mealsFromJson(mealJsonObjects) {
    this.meals = [];
    for (let mealJsonObject of mealJsonObjects) {
      this.meals.push(new Meal(mealJsonObject));
    }
  }

  private suggestionsFromJson(suggestionJsonObjects) {
    this.suggestions = [];
    for (let suggestionJsonObject of suggestionJsonObjects) {
      this.suggestions.push(new Suggestion(suggestionJsonObject));
    }
  }
}
