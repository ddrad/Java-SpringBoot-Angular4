import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {AdService} from '../ad.service';
import {Ad} from '../ad.model';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-ad-edit',
  templateUrl: './ad-edit.component.html',
  styleUrls: ['./ad-edit.component.css']
})
export class AdEditComponent implements OnInit, OnDestroy {

  id: number;
  ad: Ad;
  editMode = false;
  adForm: FormGroup;
  subscription: Subscription;

  constructor(private route: ActivatedRoute,
              private adService: AdService,
              private router: Router) {
  }

  ngOnInit() {
    this.subscription = this.route.params.subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.editMode = params['id'] != null;
        this.ad = this.adService.getSelectedAd();

        if (this.editMode) {
          if (this.ad && this.ad.id === +this.id) {
            this.initForm();
          } else {
            this.onCancel();
          }
        } else {
          this.initForm();
        }
      }
    );

    // this.subscription = this.adService.adSelectedSbj.subscribe(
    //   (ad: Ad) => {
    //     this.ad = ad;
    //     this.initForm();
    //   });
  }

  onSubmit() {
    console.log('1');
    if (this.editMode) {
      console.log('2');
      this.ad.title = this.adForm.value['title'];
      this.ad.imagePath = this.adForm.value['imagePath'];
      this.ad.description = this.adForm.value['description'];
      this.ad.products = this.adForm.value['products'];
      this.adService.updateAd(this.id, this.ad);
    }
    else {
      console.log('3');
      if (!this.adForm.value['imagePath']) {
        this.adForm.value['imagePath'] = this.adService.getDefaulImage();
      }
      this.adService.addNewAd(this.adForm.value)
        .subscribe(
          (ad: Ad) => {
            this.router.navigate(['/ads-own', ad.id, ad.index], {relativeTo: this.route});
          }
        );
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onAddProductToAd() {
    (<FormArray>this.adForm.get('products')).push(
      new FormGroup({
        'productName': new FormControl(null, Validators.required),
        'cost': new FormControl(null, [
          Validators.required,
          Validators.pattern(/^[1-9]+[0-9]*$/)
        ])
      })
    );
  }

  onDeleteProduct(index: number) {
    (<FormArray>this.adForm.get('products')).removeAt(index);
  }

  initForm() {
    let title = '';
    let imagePath = '';
    let description = '';
    const products = new FormArray([]);

    if (this.editMode) {
      title = this.ad.title;
      imagePath = this.ad.imagePath;
      description = this.ad.description;

      if (this.ad['products']) {
        for (const product of  this.ad.products) {
          products.push(
            new FormGroup({
              'productName': new FormControl(product.productName, Validators.required),
              'cost': new FormControl(product.cost,
                [
                  Validators.required,
                  Validators.pattern(/^[1-9]+[0-9]*$/)
                ])
            })
          );
        }
      }
    }

    this.adForm = new FormGroup({
      'title': new FormControl(title, Validators.required),
      'imagePath': new FormControl(imagePath),
      'description': new FormControl(description, Validators.required),
      'products': products
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
