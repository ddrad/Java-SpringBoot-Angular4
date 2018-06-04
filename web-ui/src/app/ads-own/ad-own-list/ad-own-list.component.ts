import { Component, OnDestroy, OnInit } from '@angular/core';
import { Ad } from '../../ads/ad.model';
import { AdService } from '../../ads/ad.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { AuthService } from "../../auth/auth.service";

@Component({
  selector: 'app-ad-own-list',
  templateUrl: './ad-own-list.component.html',
  styleUrls: ['./ad-own-list.component.css']
})
export class AdOwnListComponent implements OnInit, OnDestroy {

  ads: Ad[] = [];
  subscribtion: Subscription;

  constructor(private adService: AdService,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.isAuthenticated()) {
      /*
      gets all own ads bt token
     */
      this.adService.fetchAdOwnInfo(this.authService.getTokenAlias(), this.authService.getTokenData());

      /*
      listens to subjectObject (adsChangedSbj - list of ad)
      */
      this.subscribtion = this.adService.adsChangedSbj.subscribe((ads: Ad[]) => {
        this.ads = ads;
      });
    }
    else {
      this.router.navigate(['sign-in']);
    }
  }

  onAddNewProduct() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    if (this.subscribtion) {
      this.subscribtion.unsubscribe();
    }
  }
}
