import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  template: '', // No need for HTML template if it's just a redirect
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    // Perform logout logic here if needed

    // Redirect to login page
    this.router.navigate(['/login']);
  }

}
