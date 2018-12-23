import {Component, Input} from "@angular/core";
import {IPriceData} from "../IPriceData";


@Component({
  selector: 'pm-graph',
  templateUrl: './graph.component.html'

})
export class GraphComponent{
    @Input() priceData : IPriceData[];
    @Input() showThisComponent : boolean;
}
