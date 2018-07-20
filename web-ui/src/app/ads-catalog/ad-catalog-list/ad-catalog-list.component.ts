import { Component, OnDestroy, OnInit } from '@angular/core';
import { Ad } from '../../ads/ad.model';
import { AdService } from '../../ads/ad.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-ad-catalog-list',
  templateUrl: './ad-catalog-list.component.html',
  styleUrls: ['./ad-catalog-list.component.css']
})
export class AdCatalogListComponent implements OnInit, OnDestroy {

  ads: Ad[] = [];
  subscribtion: Subscription;

  constructor(private adService: AdService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    /*
     gets all items (ad[])
      */
    this.adService.fetchAllAds();

    /*
    listens to changes for subjectObject (adsChangedSbj - list of ad)
     */
    this.subscribtion = this.adService.adsChangedSbj.subscribe((ads: Ad[]) => {
      this.ads = ads;
    });
  }

  isAuthenticatedAsMaster() {
    return this.adService.isAuthenticatedAsMaster();
  }

  onAddNewProduct() {
    if (this.adService.isAuthenticatedAsMaster) {
      this.router.navigate(['new'], { relativeTo: this.route });      
    }
    else {
      this.router.navigate(['sign-in']);
    }
  }

  ngOnDestroy() {
    this.subscribtion.unsubscribe();
  }
}
