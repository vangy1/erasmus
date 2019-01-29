export class Meal {
  public id: string;
  public name: string;
  public pictureUrl: string;
  public description: string;
  public rating: string;

  constructor(jsonObject) {
    if (jsonObject) {
      this.id = jsonObject['id'];
      this.name = jsonObject['name'];
      this.pictureUrl = jsonObject['pictureUrl'];
      this.description = jsonObject['description'];
      this.rating = jsonObject['rating'];
    } else {
      this.id = "";
      this.name = "";
      this.pictureUrl = "";
      this.description = "";
      this.rating = "";
    }
  }

}
