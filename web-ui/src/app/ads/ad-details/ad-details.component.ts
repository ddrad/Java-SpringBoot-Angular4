import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Params, Router } from '@angular/router';

import { Ad } from '../ad.model';
import { AdService } from '../ad.service';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit, OnDestroy {
  ad: Ad;
  id: number;
  index: number;
  subscribtion: Subscription;
  isShowDropdownMenu: boolean;

  constructor(private adService: AdService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.subscribtion = this.route.params.subscribe(
      (params: Params) => {
        // by default must be false, then if customer is owner mange button will be available
        this.isShowDropdownMenu = false;
        this.id = +params['id'];
        this.index = +params['index'];
        this.ad = this.adService.fetchAdByIndex(this.index);

        if (this.ad) {
          this.manageAdIsAvailable()
            .subscribe(r => {
              this.isShowDropdownMenu = r
            });
        }
      }
    );
  }

  onAddToShopingList(index: number) {
    if (!this.adService.isAuthenticated()) {
      this.router.navigate(['confirm-order']);
    }
    this.adService.addAdToShoppingList(this.ad.products[index]);
  }

  private manageAdIsAvailable() {
    if (this.adService.isAuthenticatedAsMaster()) {
      return this.adService.isCustomerIsOwnerAd(this.id);
    }
    else {
      return Observable.of(false);
    }
  }

  onProductEdit() {
    this.router.navigate(['edit'], { relativeTo: this.route });
  }

  onDeleteAd() {
    const id = this.adService.getAdsInfo[this.index];
    this.adService.deleteAd(id);
    this.router.navigate(['ads']);
  }

  ngOnDestroy() {
    this.subscribtion.unsubscribe();
  }

}