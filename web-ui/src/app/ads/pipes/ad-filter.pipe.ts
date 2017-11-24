import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'adFilter'
})
export class AdFilterPipe implements PipeTransform {

  transform(value: any, filterString: string, propName:string): any {
    if(value.length === 0 || filterString === '' || filterString == null) {
      return value;
    }
    const result = [];
    for(const item of value) {
      if(item[propName].startsWith(filterString)) {
        result.push(item);
      }
    }
    return result;
  }

}
