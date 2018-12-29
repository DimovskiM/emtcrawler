
import {ICategory} from "./category";
import {IPriceData} from "./IPriceData";

export class Item{
  name: string;
  price:number;
  category:ICategory;
  date:Date;
  photoLink:string;
  priceList:Array<IPriceData>;
  showGraph:boolean = false;

}
