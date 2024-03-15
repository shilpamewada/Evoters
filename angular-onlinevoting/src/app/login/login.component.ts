import { Component } from '@angular/core';
import { VoterService } from '../voter.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginType: string = 'voter';
  voterCardNumber: string = '';
  email: string = '';
  password: string = '';
  userVotingCardNumber: string = '';
  errormessage: string = '';
  userEmail: string = '';
  userPassword: string = '';
  constructor(
    private service: VoterService
  ) { }

  login(): void {
    // if (this.email === "" || this.userEmail === undefined) {
    //   this.errormessage = "Email Addresss Can Not Blank";
    //   return;
    // }

    // this.errormessage = "";

    // if (this.password === "" || this.userPassword === undefined) {
    //   this.errormessage = "Password is blank";
    //   return;
    // }

    // if (this.voterCardNumber === "" || this.userVotingCardNumber === undefined) {
    //   this.errormessage = "Voting Number Is Blank";
    //   return;
    // }

    const body: any = {
      "userPassword": this.password
    }
    let requstType: any = null;
    if (this.loginType === 'voter') {
      body.userVotingCardNumber = this.voterCardNumber;
      requstType = this.service.signInByCard(body);
    } else {
      body.userEmail = this.email
      requstType = this.service.signInByEmail(body);
    }
    requstType.pipe(take(1)).subscribe((response: any) => {
      console.log('>>>>', response);
      if (response?.userRole === 'voter' && response?.activateAccount === false) {
        alert('Your account is not active, Please try after some time');
        return;
      }
      this.service.storeLoggedInUser(response);
      localStorage.setItem('uname', response?.userName);
      localStorage.setItem('uId', response?.userId);
      localStorage.setItem('role', response?.userRole);
      if (response?.userRole === 'voter') {
        this.service.navigateToLink('voterhome');
      } else {
        this.service.navigateToLink('adminhome');
      }
    })
  }
}
