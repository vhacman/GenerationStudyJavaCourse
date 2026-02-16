import { Component, input } from '@angular/core';
import { FoodModel } from '../model/FoodModel';

// surprise, suprise
// io sono la logica di un componente
@Component({
  selector: 'app-food',
  standalone: true,
  imports: [],
  templateUrl: './food.component.html',
  styleUrl: './food.component.css'
})
export class FoodComponent {

    food:FoodModel = {name:"Lasagna", "carbs":100, "proteins":100, "fats":20, "vegan":false, "price":2};

    changeNutrients(field:string, delta:number):void{
        let newValue:number = 0;
        switch(field)
        {
            case "carbs":
                newValue = this.food.carbs+delta;
            break;
            case "proteins":
                newValue = this.food.proteins+delta;
            break;
            case "fats":
                newValue = this.food.fats+delta;
            break;
            
        }
        this.food = {...this.food, [field]:newValue};
    }

    calories():number{
        return this.food.carbs * 4 + this.food.proteins * 4 + this.food.fats * 9;
    }

}
