import { Component, computed, signal } from '@angular/core';
import { Dish } from '../model/Dish';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-dish-form-signal',
  imports: [FormsModule],
  templateUrl: './dish-form-signal.html',
  styleUrl: './dish-form-signal.css',
})
export class DishFormSignal {

    dish:Dish = {
        name:"Pollo alle mandorle",
        description:"Pollo, mandorle, tofu",
        type:"O",
        price:signal<number>(0),
        cost:signal<number>(0),
        carbs:signal<number>(0),
        proteins:signal<number>(0),
        fats:signal<number>(0)
    };

    // HORROR VACUI. L'orrore che provate guardando l'infinito e capendo quanto siete piccoli rispetto all'universo
    //getMargin():number{
    //    return this.dish.price() - this.dish.cost();
    //}

    margin = computed<number>(
        () =>
        {
            console.log("Ricalcolo il margine");
            return this.dish.price() - this.dish.cost();
        }
    );

    calories = computed<number>(
        ()=>
        {
            console.log("Ricalcolo le calorie");
            return this.dish.carbs() * 4 + this.dish.proteins() * 4 + this.dish.fats() * 9;
        }
    );

}
