import { Component } from '@angular/core';
import { VoterService } from '../voter.service';

@Component({
  selector: 'app-appheader',
  templateUrl: './appheader.component.html',
  styleUrls: ['./appheader.component.css']
})
export class AppheaderComponent {
  isMenuActive: boolean = false;

  constructor(private voterService: VoterService) {}

  logout(): void {
    this.voterService.userLogout(); // Call the userLogout method from VoterService
  }

  toggleMenu(): void {
    this.isMenuActive = !this.isMenuActive;
  }
}
