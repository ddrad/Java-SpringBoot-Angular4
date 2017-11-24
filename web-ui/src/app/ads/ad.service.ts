import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {ShoppingListService} from '../shopping-list/shopping-list.service';
import {Order} from '../common/model/order.model';
import {Product} from '../common/model/product.model';
import {HttpService} from '../common/services/http.service';
import {Ad} from './ad.model';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';
// import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class AdService {

  private defaultImageValue = 'http://media.zanibonilighting.com/images/fixtures/no-img.png';

  adsChangedSbj = new Subject<Ad[]>();
  adSelectedSbj = new Subject<Ad>();

  constructor(private shoppingListService: ShoppingListService,
              private http: Http, private httpService: HttpService) {
  }

  private adsInfo: Ad[] = [];
  private selectedAd: Ad;

  getAdsInfo() {
    console.log('1 ', this.adsInfo);
    return this.adsInfo.slice();
  }

  setAdsInfo(ads: Ad[]) {
    this.adsInfo = ads;
    this.adsChangedSbj.next(this.adsInfo.slice());
  }

  addAdToAdsInfo(ad: Ad) {
    if (ad.index) {
      this.adsInfo[ad.index] = ad;
    } else {
      this.adsInfo.push(ad);
    }
    this.adsChangedSbj.next(this.adsInfo.slice());
  }

  getLengthAdInfo(): number {
    return this.adsInfo.length;
  }

  getSelectedAd() {
    return this.selectedAd;
  }

  setSelectedAd(ad: Ad) {
    this.selectedAd = ad;
    this.adSelectedSbj.next(this.selectedAd);
  }

  getDefaulImage() {
    return this.defaultImageValue;
  }

  fetchAdInfo() {
    this.http.get('http://localhost:4200/app-ads/all')
      .map(
        (response: Response) => {
          const ads: Ad[] = response.json();
          for (const ad of ads) {
            if (ad['products']) {
              ad['products'] = [];
            }
          }
          return ads;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('error in getAdsInfo()' + error);
        }
      )
      .subscribe(
        (ads: Ad[]) => {
          this.setAdsInfo(ads);
        }
      );
  }

  fetchAdOwnInfo(tokenAlias: string) {
    this.http.post('http://localhost:4200/app-ads/own', {tokenAlias})
      .map(
        (response: Response) => {
          const ads: Ad[] = response.json();
          for (const ad of ads) {
            if (ad['products']) {
              ad['products'] = [];
            }
          }
          return ads;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('error in getAdsInfo()' + error);
        }
      )
      .subscribe(
        (ads: Ad[]) => {
          this.setAdsInfo(ads);
        }
      );
  }


  fetchAdById(id: number, index: number) {
    return this.http.get('http://localhost:4200/app-ads/ad/' + id)
      .map(
        (response: Response) => {
          return response.json();
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('Can not get ad by ID ' + error);
        }
      )
      .subscribe(
        (ad: Ad) => {
          ad.index = index;
          this.setSelectedAd(ad);
        }
      );
  }

  addAdToShoppingList(product: Product) {
    const order = new Order();
    order.product = product;
    this.shoppingListService.addOrderToShoppingList(order);
  }

  addNewAd(ad: Ad) {
    const options = this.httpService.getRequestOptions();

    return this.http.post('http://localhost:4200/app-ads/add', ad, options)
      .map(
        (response: Response) => {
          const ad: Ad = response.json();
          console.log('in http response ', ad);
          ad.index = this.getLengthAdInfo();
          console.log('### ', ad.index);
          this.addAdToAdsInfo(ad);
          this.adSelectedSbj.next(ad);
          return ad;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('Can not update ad ' + error);
        }
      );
  }

  updateAd(id: number, editedAd: Ad) {
    editedAd.id = id;
    const options = this.httpService.getRequestOptions();
    this.http.put('http://localhost:4200/app-ads/update', editedAd, options)
      .catch(
        (error: Response) => {
          return Observable.throw('Can not update ad ' + error);
        }
      )
      .subscribe(
        (response: Response) => {
          this.addAdToAdsInfo(editedAd);
          this.adSelectedSbj.next(editedAd);
        }
      );
  }

  deleteAd(index: number) {
    this.adsInfo.splice(index, 1);
    this.adsChangedSbj.next(this.adsInfo.slice());
  }
}
