import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

// è per uso locale interno a questo componente
// se volessi importarla da altri dovre fare export
interface Dish{
    name:string;
    cost:number;
    price:number;
    description:string;
    type:"O" | "V" | "VG";
    carbs:number;
    proteins:number;
    fats:number;
}


@Component({
  selector: 'app-dish-form',
  imports: [FormsModule],
  templateUrl: './dish-form.html',
  styleUrl: './dish-form.css',
})
export class DishForm {

    dish:Dish = {
        name:"Cantonese Rice",
        cost:0.5,
        price:4.5,
        type: "O",
        description:'',
        carbs:50,
        proteins:10,
        fats:20
    };

    // io non sono computed, io vengo ricalcolato a OGNI CAMBIAMENTO
    // di tutta la pagina
    margin():number{
        console.log("STO RICALCOLANDO PREZZO");
        return this.dish.price - this.dish.cost;
    }

    calories():number{
        console.log("STO RICALCOLANDO LE CALORIE");
        return this.dish.carbs * 4 + this.dish.proteins * 4 + this.dish.fats * 9;
    }

}
