import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-fitness-program',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './fitness-program.component.html',
  styleUrl: './fitness-program.component.css'
})
export class FitnessProgramComponent {

    exercises:string[] = [];
    newExercise:string='';

    addExercise():void{
        if(this.newExercise=='')
            return;
        this.exercises = [this.newExercise, ...this.exercises];
        this.newExercise = '';
    }

}
