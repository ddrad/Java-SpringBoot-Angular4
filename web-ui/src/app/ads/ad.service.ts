import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { ShoppingListService } from '../shopping-list/shopping-list.service';
import { Order } from '../common/model/order.model';
import { Product } from '../common/model/product.model';
import { HttpService } from '../common/services/http.service';
import { Ad } from './ad.model';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';
// import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { AuthService } from '../auth/auth.service';
import { Router } from "@angular/router";
import { RequestOptions } from '@angular/http';

@Injectable()
export class AdService {

  private defaultImageValue = 'http://media.zanibonilighting.com/images/fixtures/no-img.png';

  adsChangedSbj = new Subject<Ad[]>();
  adSelectedSbj = new Subject<Ad>();

  constructor(private shoppingListService: ShoppingListService,
    private httpClient: HttpClient, private httpService: HttpService,
    private authService: AuthService, private router: Router) {
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
  //  const param = new HttpParams({fromObject: {data}});
    let header = new HttpHeaders().set('Content-Type', 'application/json')
      .set('tokenAlias', tokenAlias)
      .set('tokenData', data);

    this.httpClient.get<Ad[]>('http://localhost:4200/app-ads/own', { headers: header})
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
          this.router.navigate(['sign-in']);
          return Observable.throw('error in Get Own Info request' + error);
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
    let header = new HttpHeaders().set('Content-Type', 'application/json')
      .set('tokenAlias', this.getTokenAlias())
      .set('tokenData', this.getTokenData());
    return this.httpClient.post<Ad>('http://localhost:4200/app-ads/add', { ad }, {headers: header})
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
          return Observable.throw('Can not add new Ad: ' + error);
        }
      );
  }
  
  uploadAdImage(imageFile: FormData) {
    // let header = new HttpHeaders().set('Content-Type', 'application/json')
    //   .set('tokenAlias', this.getTokenAlias())
    //   .set('tokenData', this.getTokenData());
  
    console.log(imageFile);
    return this.httpClient.post<any>('http://localhost:4200/app-ads/uploadAdImage', {imageFile})
      .map(
        (result) => {
         console.log(result);
          return result;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('Can not add new image: ' + error);
        }
      )
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

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  isCustomerIsOwnerAd(id: number) {
    let tokenAlias = this.getTokenAlias();
    let data = this.getTokenData();
    return this.httpClient.put('http://localhost:4200/app-ads/checkOwner/' + id, { tokenAlias, data })
      .catch(
        (error: Response) => {
          return Observable.throw('Can not find ad ' + error);
        }
      );
  }

  private getTokenData() {
    return this.authService.getTokenData();
  }

  private getTokenAlias() {
    return this.authService.getTokenAlias();
  }
}
