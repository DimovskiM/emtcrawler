import {Injectable} from "@angular/core";
import {Item} from "./item";
@Injectable({
  providedIn:"root"
})
export class ItemService{
  items : Item[];


  getItems(): Item[]{
    return null
  }
}
