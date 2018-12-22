
import {ICategory} from "./category";

export interface Item{
  name: string;
  price:number;
  category:ICategory;
  imageUrl:string;
  date:Date;

}
