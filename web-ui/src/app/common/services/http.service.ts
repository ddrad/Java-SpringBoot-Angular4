import {Headers, RequestOptions} from '@angular/http';

export class HttpService {

  getRequestOptions(): RequestOptions {
    return new RequestOptions({
      headers: new Headers({
        'Accept': 'application/json'
      })
    });
  }
}
