import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
import {AuthService} from '../auth/auth.service';

@Injectable()
export class AdService {

  private defaultImageValue = 'http://media.zanibonilighting.com/images/fixtures/no-img.png';

  adsChangedSbj = new Subject<Ad[]>();
  adSelectedSbj = new Subject<Ad>();

  constructor(private shoppingListService: ShoppingListService,
              private httpClient: HttpClient, private httpService: HttpService,
              private authService: AuthService) {
  }

  private adsInfo: Ad[] = [];
  private selectedAd: Ad;

  getAdsInfo() {
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

  fetchAllAds() {
    this.httpClient.get<Ad[]>('http://localhost:4200/app-ads/all')
      .map(
        (ads) => {
          for (const ad of ads) {
            if (!ad['products']) {
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

  fetchAdOwnInfo(tokenAlias: string, data) {
    console.log('fetchAdOwnInfo: ', data);
    this.httpClient.post<Ad[]>('http://localhost:4200/app-ads/own', {tokenAlias, data})
      .map(
        (ads) => {
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

  fetchAdByIndex(index: number) {
    let ad = this.adsInfo[index];
    this.setSelectedAd(ad);
    return ad;
  }

  fetchAdById(id: number, index: number) {
    return this.httpClient.get<Ad>('http://localhost:4200/app-ads/ad/' + id)
      .map(
        (ad) => {
          return ad;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('Can not get ad by ID ' + error);
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
    const tokenAlias = this.authService.getTokenAlias();
    return this.httpClient.post<Ad>('http://localhost:4200/app-ads/add', {tokenAlias, ad})
      .map(
        (result) => {
          result.index = this.getLengthAdInfo();
          this.addAdToAdsInfo(result);
          this.adSelectedSbj.next(result);
          return result;
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
    this.httpClient.put('http://localhost:4200/app-ads/update', editedAd)
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

  isAuthenticatedAsMaster() {
    return this.authService.isAuthenticatedAsMaster();
  }
}
