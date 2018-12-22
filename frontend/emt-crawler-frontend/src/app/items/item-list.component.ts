import {Component} from "@angular/core";
import {Item} from "./item";



@Component({
  selector : 'pm-items',
  templateUrl : './item-list.component.html',
  styleUrls : ['./item-list.component.css']
})
export class ItemListComponent{
pageTitle: string ='EMT Project by Mihajlo Dimovski & Kristijan Gaber';
imageWidth: number = 50;
imageMargin:number = 2;

  listFilter:string;
  items:Item [];
}
