import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICourse } from '../course.model';
import { CourseService } from '../service/course.service';

@Injectable({ providedIn: 'root' })
export class CourseRoutingResolveService implements Resolve<ICourse | null> {
  constructor(protected service: CourseService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICourse | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((course: HttpResponse<ICourse>) => {
          if (course.body) {
            return of(course.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
