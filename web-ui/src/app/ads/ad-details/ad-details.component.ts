import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';

import {Ad} from '../ad.model';
import {AdService} from '../ad.service';
import {Subscription} from 'rxjs/Subscription';

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

  constructor(private adService: AdService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.index = +params['index'];
        this.adService.fetchAdById(this.id, this.index);
      }
    );

    this.subscribtion = this.adService.adSelectedSbj.subscribe(
      (ad: Ad) => {
        this.ad = ad;
      }
    );
  }

  onAddToShopingList(index: number) {
    this.adService.addAdToShoppingList(this.ad.products[index]);
  }

  onProductEdit() {
    this.router.navigate(['edit'], {relativeTo: this.route});
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
