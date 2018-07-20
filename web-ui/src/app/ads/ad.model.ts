import {Product} from "../common/model/product.model";

export class Ad {
  public id: number;
  public index: number;
  public title: string;
  public description: string;
  public imagePath: string;
  public imageFile:any;
  public author: string;
  public cost: number;
  public products: Product[];

  constructor(title: string, desc: string, imagePath: string, author: string, cost: number, produts: Product[], imageFile:any) {
    this.title = title;
    this.description = desc;
    this.imagePath = imagePath;
    this.author = author;
    this.cost = cost;
    this.products = produts;
    this.imageFile = imageFile;
  }
}
