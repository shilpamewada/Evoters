import { Component } from '@angular/core';
import { VoterService } from '../voter.service';
import { take } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  errorMessage: string = '';
  userMobileNumber: string = '';
  userAddress: string = '';
  userName: string = '';
  userVotingCardNumber = '';
  userEmail = '';
  userPassword = '';
  userDateOfBirth = '';
  userGender = 'male';

  constructor(
    private route:Router,
    private service: VoterService
  ) { }
  registerUser(): any {
    if (this.userName === '') {
      this.errorMessage = 'First name should not be blank';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return;
    }
    if (this.userName.length < 4) {
      this.errorMessage = 'First name should be more than 3 chracter';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return;
    }

    if (this.userVotingCardNumber.length !== 10) {
      this.errorMessage = 'Voting card Number should be exactly 10 characters';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return;
    }
    if (this.userAddress.length < 3) {
      this.errorMessage = 'Address name should be more than 3 chracter';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return;
    }

    const mobilePattern = /^[6-9]\d{9}$/;
    if (!mobilePattern.test(this.userMobileNumber)) {
      this.errorMessage = 'Mobile number should start between 6 to 9';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return
    }
    const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    if (!passwordPattern.test(this.userPassword)) {
      //alert('Mobile numbner should start between 6 to 9. And it should be valid mobile number.');
      this.errorMessage = 'Password should be atleast 8 character and one upperltter and one character';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return
    }
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(this.userEmail)) {
      this.errorMessage = 'Please enter a valid email address';
      document.getElementById('errordiv')?.scrollIntoView(true);
      return;
    }
    const body: any = {
      userName: this.userName,
      userVotingCardNumber: this.userVotingCardNumber,
      userAddress: this.userAddress,
      userMobileNumber: this.userMobileNumber,
      userEmail: this.userEmail,
      userPassword: this.userPassword,
      userDateOfBirth: this.userDateOfBirth,
      userGender: this.userGender,
     // userRole: 'admin'
     userRole: 'voter'
    };

    console.log('>>>>>', body);
    this.service.signUp(body).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>>>>>>>>>>>>res', res);
      if (!!res && res === 'User Registered Successfully') {
        alert('You successfully register');
        this.service.navigateToLink('login');
      } else {
        alert('Please add valid information');
      }
    });
  }
  // routeToLink(link: string): void {

  //   this.service.navigateToLink(link);
  // }

  routeToLogin(): void {
    this.route.navigate(["/login"]); //user-login
  }
}