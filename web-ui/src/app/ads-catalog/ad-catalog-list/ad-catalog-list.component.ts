import {Component, OnDestroy, OnInit} from '@angular/core';
import {Ad} from '../../ads/ad.model';
import {AdService} from '../../ads/ad.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';

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
    this.adService.fetchAdInfo();

    this.subscribtion = this.adService.adsChangedSbj.subscribe((ads: Ad[]) => {
      this.ads = ads;
    });
  }

  onAddNewProduct() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy() {
    this.subscribtion.unsubscribe();
  }
}
