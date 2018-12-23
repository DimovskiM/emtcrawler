
import {ICategory} from "./category";

export interface Item{
  name: string;
  price:number;
  category:ICategory;
  date:Date;

  photoLink:string;

}
