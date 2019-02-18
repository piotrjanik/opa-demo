import {Component, OnInit} from '@angular/core';
import {AuthConfig, JwksValidationHandler, OAuthService} from 'angular-oauth2-oidc';
import {HttpClient} from '@angular/common/http';
import {Profile} from './profile';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ui';
  claims: any;
  me: Profile;

  private authConfig: AuthConfig = {
    issuer: 'https://opademo.auth0.com/',
    redirectUri: window.location.origin + '/',
    clientId: 'P-6tGMH-slLpR-3y7k2z-LdOrv2TbvGc',
    scope: 'openid  profile email voucher',
  };

  constructor(private oauthService: OAuthService, private http: HttpClient) {
    this.configureWithNewConfigApi();

  }

  private configureWithNewConfigApi() {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndLogin();
    this.claims = this.oauthService.getIdentityClaims();
  }

  private getMyProfile() {
    this.http
      .get<Profile>('http://localhost:8080/v1/user-profiles/me', {headers: {'Authorization': 'Bearer ' + this.oauthService.getIdToken()}})
      .subscribe(obj => {
        this.me = obj;
      });
  }

  ngOnInit(): void {
    this.getMyProfile();
  }

}
