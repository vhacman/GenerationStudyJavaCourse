import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

export class Room
{
    type:string;
    side1:number;
    side2:number;

    constructor(type:string,side1:number, side2:number)
    {
        this.type = type;
        this.side1 = side1;
        this.side2 = side2;
    }

    area():number
    {
        return this.side1 * this.side2;
    }
}

export class HouseModel
{
    city:string;
    address:string;
    smp:number;
    rooms:Room[] = [];

    constructor(city:string, address:string, smp:number)
    {
        this.city = city;
        this.address = address;
        this.smp = smp;
    }

    area():number
    {
        let res:number = 0;
        for(let r of this.rooms)
            res+=r.area();
        return res;
    }

    price():number
    {
        return this.area() * this.smp;

    }


}

@Component({
  selector: 'app-house',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './house.component.html',
  styleUrl: './house.component.css'
})
export class HouseComponent 
{
    house:HouseModel = new HouseModel("Milano", "Via dei fatti miei", 1000);
    newRoom:Room = new Room('',0,0);

    addRoom():void{
        this.house.rooms = [...this.house.rooms, this.newRoom];
        this.newRoom = new Room('',0,0);
    
    }
    
}
