import { Component } from '@angular/core';
import { take } from 'rxjs';
//import { take } from 'rxjs';
import { VoterService } from 'src/app/voter.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent {
  allParty: any[] = [];
  constructor(
    private service: VoterService
  ) {
    this.service.getAllParty().pipe(take(1)).subscribe((res: any) => {
      if (!!res) {
        
        console.log('>>>>##>>', res);
        this.allParty = res;
      }
    });
  }
}
