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
  errorMessage:string='';

  constructor(
    private service: VoterService
  ) {}

  login(): void {
    const body: any = {
      "userPassword": this.password
    }
    let requstType: any = null;
    let flag = 0;
    if (this.loginType === 'voter') {
      if (this.voterCardNumber === '') {
        this.errorMessage = 'Voting Number should Not Blank';
        document.getElementById('errordiv')?.scrollIntoView(true);
        return;
      }

      if (this.password === '') {
        this.errorMessage = 'Password should Not Blank';
        document.getElementById('errordiv')?.scrollIntoView(true);
        return;
      }
    
    } else if (this.loginType === 'admin'){
      if (this.email === '') {
        this.errorMessage = 'Email Id should Not Blank';
        document.getElementById('errordiv')?.scrollIntoView(true);
        return;
      }
      if (this.password === '') {
        this.errorMessage = 'Password should Not Blank';
        document.getElementById('errordiv')?.scrollIntoView(true);
        return;
      }
    }
    if (flag === 1) {
      return;
    }
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
