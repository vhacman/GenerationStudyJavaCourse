import { Component, computed, inject, signal } from '@angular/core';
import { PeopleService } from '../people-service';
import { FormsModule } from '@angular/forms';
import { Person } from '../model/Person';

@Component({
  selector: 'app-people-list',
  imports: [FormsModule],
  templateUrl: './people-list.html',
  styleUrl: './people-list.css',
})
export class PeopleList {
  peopleService = inject(PeopleService);

  filterName = signal<string>("");
  filterSurname = signal<string>("");
  filterCitta = signal<string>("");

  people = this.peopleService.people;

  filteredPeople = computed<Person[]>(() => {
    let res = this.people();

    if (this.filterName() !== "") {
      res = res.filter(x => x.nome.includes(this.filterName()));
    }
    if (this.filterSurname() !== "") {
      res = res.filter(x => x.cognome.includes(this.filterSurname()));
    }

    if (this.filterCitta() !== "") {
      res = res.filter(x => x.citta.includes(this.filterCitta()));
    }

    return res;
  });

  /**
   // Questo computed dipende da people, filterName, filterSurname, filterCity
        filteredPeople = computed<Person[]>(() => {
        return this.people()
        .filter(x => this.filterName() == "" || x.nome.includes(this.filterName()))
        .filter(x => this.filterSurname() == "" || x.cognome.includes(this.filterSurname()))
        .filter(x => this.filterCity() == "" || x.citta.includes(this.filterCity()));
    });
   */
}
