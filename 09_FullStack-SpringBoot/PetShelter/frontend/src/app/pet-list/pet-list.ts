import { Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PetService } from '../pet-service';
import { Pet } from '../model/Pet';

@Component({
  selector: 'app-pet-list',
  imports: [FormsModule],
  templateUrl: './pet-list.html',
  styleUrl: './pet-list.css',
})
export class PetList {
  petService = inject(PetService);

  filterName = signal<string>("");
  filterSpecies = signal<string>("");
  filterNotes = signal<string>("");  // Nuovo: filtro per notes
  filterYoungerThan = signal<number>(0);
  filterOlderThan = signal<number>(0);

  pets = this.petService.pets;

  filteredPets = computed<Pet[]>(() => {
    let res = this.pets();

    if (this.filterName() !== "")  
      res = res.filter(x => x.name.includes(this.filterName())); 
    if (this.filterSpecies() !== "")  
      res = res.filter(x => x.species.includes(this.filterSpecies())); 
    if (this.filterNotes() !== "") 
      res = res.filter(x => x.notes?.includes(this.filterNotes()) ?? false);     
    if (this.filterOlderThan() > 0) 
      res = res.filter(x => x.age >= this.filterOlderThan());  
    if (this.filterYoungerThan() > 0) 
      res = res.filter(x => x.age <= this.filterYoungerThan());     
    return res;
  });
}
