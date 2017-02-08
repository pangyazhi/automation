import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import {Http, Headers, RequestOptions} from '@angular/http';

@Injectable()
export class DataService {

  headers = new Headers({'Content-Type': 'application/json'});
  options = new RequestOptions({headers: this.headers});
  url = environment.serverurl;

  constructor(private http: Http) {
    this.http = http;
  }

  add(obj: Object= {}): DataService {
    this.http.post(this.url, obj, this.options);
    return this;
  }

  put(id: String, obj: Object= {}): DataService {
    this.http.put(this.url + id, obj, this.options );
    return this;
  }

  getAll(): Object[] {
    let data;
    this.http.get(this.url, this.options).subscribe(res => data = res.json());
    return data;
  }

  getRegex(regex: String): Object[] {
    let data;
    this.http.get(this.url + 'regex/' + regex, this.options).subscribe(res => data = res.json());
    return data;
  }
}
