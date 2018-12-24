import {Component, Input} from "@angular/core";
import {IPriceData} from "../IPriceData";
import {Import} from "@angular/compiler-cli/src/ngtsc/host";
import {Item} from "../item";


@Component({
  selector: 'pm-graph',
  templateUrl: './graph.component.html'

})
export class GraphComponent{
  get item(): Item {
    return this._item;
  }
  @Input() _item:Item;
   graphType: string = 'LineChart';

   @Input() graphData: any[];

    description: string = this._item.name;


}
