import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VoterService {
  private loginUrl = 'http://localhost:8080';
  private loggedInUser: any = null;
  public electionDate: Date = new Date();

  constructor(
    private router: Router,
    private httpClient: HttpClient
  ) { }

  navigateToLink(link: string): void {
    if (link === 'logout') {
      this.userLogout();
      return;
    }
    this.router.navigate(['/'+ link]);
  }

  signInByCard(body: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/user/loginByCardNumber`, body).pipe(
      map((response: any) => {
        this.storeLoggedInUser(response.user);
        return response; // Assuming you want to return the response
      })
    );
  }

  signInByEmail(body: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/user/loginByEmail`, body).pipe(
      map((response: any) => {
        this.storeLoggedInUser(response.user);
        return response; // Assuming you want to return the response
      })
    );
  }

  signUp(body: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/user/registeruser`, body, { responseType: 'text' });
  }

  storeLoggedInUser(user: any): void {
    this.loggedInUser = user;
  }

  userLogout(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  getUserById(id: any): Observable<any> {
    return this.httpClient.get(`${this.loginUrl}/user/getUserById/${id}`);
  }

  getAllParty(): Observable<any> {
    return this.httpClient.get(`${this.loginUrl}/party/partylist`);
  }

  addVote(partyId: any, userId: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/user/addvote/${partyId}/${userId}`, {});
  }

  addParty(body: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/party/addparty`, body, { responseType: 'text' });
  }

  getAllVoterList(): Observable<any> {
    return this.httpClient.get(`${this.loginUrl}/user/alluser`);
  }

  activateUser(userId: any): Observable<any> {
    return this.httpClient.post(`${this.loginUrl}/user/activateUser2/${userId}`, {}, { responseType: 'text' });
  }

  deleteUser(userId: any): Observable<any> {
    return this.httpClient.delete(`${this.loginUrl}/user/deleteuser/${userId}`, { responseType: 'text' });
  }

  getvotingEndDate(): Observable<Date> {
    return this.httpClient.get(`${this.loginUrl}/election/electionenddate`, { responseType: 'text' }).pipe(
      map((res: string) => new Date(res))
    );
  }

  deletePartyById(partyId: any): Observable<any> {
    return this.httpClient.delete(`${this.loginUrl}/party/deleteparty/${partyId}`, { responseType: 'text' });
  }

  getUserDetailById(userId: any): Observable<any> {
    return this.httpClient.get(`${this.loginUrl}/user/findByUserId/${userId}`);
  }

  updateUser(user: any): Observable<any> {
    return this.httpClient.put(`${this.loginUrl}/user/updateUser`, user);
  }

  getPartyById(pId: any): Observable<any> {
    return this.httpClient.get(`${this.loginUrl}/party/findByPartyId/${pId}`);
  }

  updateParty(party: any): Observable<any> {
    return this.httpClient.put(`${this.loginUrl}/party/edit/${party?.partyId}`, party);
  }
}
