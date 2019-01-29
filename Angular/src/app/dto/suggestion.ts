export class Suggestion {
  public id: string;
  public mealName: string;
  public username: string;

  constructor(jsonObject) {
    this.id = jsonObject['id'];
    this.mealName = jsonObject['mealName'];
    this.username = jsonObject['username'];
  }
}
