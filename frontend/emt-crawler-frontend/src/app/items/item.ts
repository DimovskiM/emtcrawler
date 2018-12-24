
import {ICategory} from "./category";
import {IPriceData} from "./IPriceData";

export interface Item{
  name: string;
  price:number;
  category:ICategory;
  date:Date;
  photoLink:string;
  priceList:IPriceData[];

}
