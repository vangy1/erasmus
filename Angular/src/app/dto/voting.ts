export class Voting {
  public id: string;
  public dateOfTheMeal: Date;
  public mealName: string;
  public rating: string;
  public voteCount: string;

  constructor(jsonObject) {
    this.id = jsonObject['id'];
    this.dateOfTheMeal = new Date(jsonObject['dateOfTheMeal']);
    this.mealName = jsonObject['mealName'];
    this.rating = jsonObject['rating'];
    this.voteCount = jsonObject['voteCount'];
  }
}
