import { Injectable, signal } from '@angular/core';
import { Pet } from './model/Pet';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PetService {

    private _pets = signal<Pet[]>([]);
    public pets = this._pets.asReadonly();
  
    constructor(private http: HttpClient) {
        this.http.get<Pet[]>("pet.json").subscribe(json => this._pets.set(json));
  }
}
