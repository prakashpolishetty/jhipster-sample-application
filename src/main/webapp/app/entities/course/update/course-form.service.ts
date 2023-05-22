import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICourse, NewCourse } from '../course.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICourse for edit and NewCourseFormGroupInput for create.
 */
type CourseFormGroupInput = ICourse | PartialWithRequiredKeyOf<NewCourse>;

type CourseFormDefaults = Pick<NewCourse, 'id' | 'isActive' | 'isCore' | 'isEnrollmentRestricted'>;

type CourseFormGroupContent = {
  id: FormControl<ICourse['id'] | NewCourse['id']>;
  courseId: FormControl<ICourse['courseId']>;
  courseName: FormControl<ICourse['courseName']>;
  description: FormControl<ICourse['description']>;
  isActive: FormControl<ICourse['isActive']>;
  department: FormControl<ICourse['department']>;
  degreeType: FormControl<ICourse['degreeType']>;
  isCore: FormControl<ICourse['isCore']>;
  specialization: FormControl<ICourse['specialization']>;
  startDateOfEnrollment: FormControl<ICourse['startDateOfEnrollment']>;
  lastDateOfEnrollment: FormControl<ICourse['lastDateOfEnrollment']>;
  startDateOfCourse: FormControl<ICourse['startDateOfCourse']>;
  lastDateOfCourse: FormControl<ICourse['lastDateOfCourse']>;
  lastDateOfCourseDrop: FormControl<ICourse['lastDateOfCourseDrop']>;
  waitingList: FormControl<ICourse['waitingList']>;
  numberOfEnrollments: FormControl<ICourse['numberOfEnrollments']>;
  isEnrollmentRestricted: FormControl<ICourse['isEnrollmentRestricted']>;
  teacherID: FormControl<ICourse['teacherID']>;
  graderID: FormControl<ICourse['graderID']>;
  classRoomNumber: FormControl<ICourse['classRoomNumber']>;
};

export type CourseFormGroup = FormGroup<CourseFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CourseFormService {
  createCourseFormGroup(course: CourseFormGroupInput = { id: null }): CourseFormGroup {
    const courseRawValue = {
      ...this.getFormDefaults(),
      ...course,
    };
    return new FormGroup<CourseFormGroupContent>({
      id: new FormControl(
        { value: courseRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      courseId: new FormControl(courseRawValue.courseId, {
        validators: [Validators.required, Validators.pattern('^[A-Za-z]\\\\d{3}$')],
      }),
      courseName: new FormControl(courseRawValue.courseName, {
        validators: [Validators.required],
      }),
      description: new FormControl(courseRawValue.description),
      isActive: new FormControl(courseRawValue.isActive),
      department: new FormControl(courseRawValue.department),
      degreeType: new FormControl(courseRawValue.degreeType),
      isCore: new FormControl(courseRawValue.isCore),
      specialization: new FormControl(courseRawValue.specialization),
      startDateOfEnrollment: new FormControl(courseRawValue.startDateOfEnrollment, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      lastDateOfEnrollment: new FormControl(courseRawValue.lastDateOfEnrollment, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      startDateOfCourse: new FormControl(courseRawValue.startDateOfCourse, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      lastDateOfCourse: new FormControl(courseRawValue.lastDateOfCourse, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      lastDateOfCourseDrop: new FormControl(courseRawValue.lastDateOfCourseDrop, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      waitingList: new FormControl(courseRawValue.waitingList),
      numberOfEnrollments: new FormControl(courseRawValue.numberOfEnrollments),
      isEnrollmentRestricted: new FormControl(courseRawValue.isEnrollmentRestricted),
      teacherID: new FormControl(courseRawValue.teacherID),
      graderID: new FormControl(courseRawValue.graderID),
      classRoomNumber: new FormControl(courseRawValue.classRoomNumber),
    });
  }

  getCourse(form: CourseFormGroup): ICourse | NewCourse {
    return form.getRawValue() as ICourse | NewCourse;
  }

  resetForm(form: CourseFormGroup, course: CourseFormGroupInput): void {
    const courseRawValue = { ...this.getFormDefaults(), ...course };
    form.reset(
      {
        ...courseRawValue,
        id: { value: courseRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CourseFormDefaults {
    return {
      id: null,
      isActive: false,
      isCore: false,
      isEnrollmentRestricted: false,
    };
  }
}
