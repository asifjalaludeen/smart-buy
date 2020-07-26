import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'enumtoarray'
})
export class EnumtoarrayPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    return null;
  }

}
