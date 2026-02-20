import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Person } from './model/Person';

@Injectable({
  providedIn: 'root',
})
export class PeopleService {
  
  private _people = signal<Person[]>([]);
  public people = this._people.asReadonly();
  
  constructor(private http: HttpClient) {
    this.http.get<Person[]>("people.json").subscribe(json => this._people.set(json));
  }
}

