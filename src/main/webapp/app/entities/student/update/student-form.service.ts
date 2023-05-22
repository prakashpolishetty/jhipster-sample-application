import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStudent, NewStudent } from '../student.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStudent for edit and NewStudentFormGroupInput for create.
 */
type StudentFormGroupInput = IStudent | PartialWithRequiredKeyOf<NewStudent>;

type StudentFormDefaults = Pick<NewStudent, 'id' | 'isActive' | 'isDegreeCompleted'>;

type StudentFormGroupContent = {
  id: FormControl<IStudent['id'] | NewStudent['id']>;
  studentId: FormControl<IStudent['studentId']>;
  name: FormControl<IStudent['name']>;
  dateOfBirth: FormControl<IStudent['dateOfBirth']>;
  phoneNumber: FormControl<IStudent['phoneNumber']>;
  personalEmailId: FormControl<IStudent['personalEmailId']>;
  universityEmailId: FormControl<IStudent['universityEmailId']>;
  departmentId: FormControl<IStudent['departmentId']>;
  specialization: FormControl<IStudent['specialization']>;
  degreeType: FormControl<IStudent['degreeType']>;
  isActive: FormControl<IStudent['isActive']>;
  isDegreeCompleted: FormControl<IStudent['isDegreeCompleted']>;
  currentSem: FormControl<IStudent['currentSem']>;
};

export type StudentFormGroup = FormGroup<StudentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StudentFormService {
  createStudentFormGroup(student: StudentFormGroupInput = { id: null }): StudentFormGroup {
    const studentRawValue = {
      ...this.getFormDefaults(),
      ...student,
    };
    return new FormGroup<StudentFormGroupContent>({
      id: new FormControl(
        { value: studentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      studentId: new FormControl(studentRawValue.studentId, {
        validators: [Validators.required],
      }),
      name: new FormControl(studentRawValue.name, {
        validators: [Validators.required],
      }),
      dateOfBirth: new FormControl(studentRawValue.dateOfBirth, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      phoneNumber: new FormControl(studentRawValue.phoneNumber, {
        validators: [Validators.pattern('^\\\\+1 [1-9]\\\\d{2}-\\\\d{3}-\\\\d{4}$')],
      }),
      personalEmailId: new FormControl(studentRawValue.personalEmailId),
      universityEmailId: new FormControl(studentRawValue.universityEmailId),
      departmentId: new FormControl(studentRawValue.departmentId),
      specialization: new FormControl(studentRawValue.specialization),
      degreeType: new FormControl(studentRawValue.degreeType),
      isActive: new FormControl(studentRawValue.isActive),
      isDegreeCompleted: new FormControl(studentRawValue.isDegreeCompleted),
      currentSem: new FormControl(studentRawValue.currentSem),
    });
  }

  getStudent(form: StudentFormGroup): IStudent | NewStudent {
    return form.getRawValue() as IStudent | NewStudent;
  }

  resetForm(form: StudentFormGroup, student: StudentFormGroupInput): void {
    const studentRawValue = { ...this.getFormDefaults(), ...student };
    form.reset(
      {
        ...studentRawValue,
        id: { value: studentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StudentFormDefaults {
    return {
      id: null,
      isActive: false,
      isDegreeCompleted: false,
    };
  }
}
