import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITeacher, NewTeacher } from '../teacher.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITeacher for edit and NewTeacherFormGroupInput for create.
 */
type TeacherFormGroupInput = ITeacher | PartialWithRequiredKeyOf<NewTeacher>;

type TeacherFormDefaults = Pick<NewTeacher, 'id' | 'isActive'>;

type TeacherFormGroupContent = {
  id: FormControl<ITeacher['id'] | NewTeacher['id']>;
  facultyId: FormControl<ITeacher['facultyId']>;
  name: FormControl<ITeacher['name']>;
  phoneNumber: FormControl<ITeacher['phoneNumber']>;
  designation: FormControl<ITeacher['designation']>;
  personalEmailId: FormControl<ITeacher['personalEmailId']>;
  universityEmailId: FormControl<ITeacher['universityEmailId']>;
  isActive: FormControl<ITeacher['isActive']>;
  departmentId: FormControl<ITeacher['departmentId']>;
  dateOfJoining: FormControl<ITeacher['dateOfJoining']>;
  dateOfLeaving: FormControl<ITeacher['dateOfLeaving']>;
};

export type TeacherFormGroup = FormGroup<TeacherFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TeacherFormService {
  createTeacherFormGroup(teacher: TeacherFormGroupInput = { id: null }): TeacherFormGroup {
    const teacherRawValue = {
      ...this.getFormDefaults(),
      ...teacher,
    };
    return new FormGroup<TeacherFormGroupContent>({
      id: new FormControl(
        { value: teacherRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      facultyId: new FormControl(teacherRawValue.facultyId, {
        validators: [Validators.required],
      }),
      name: new FormControl(teacherRawValue.name, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(teacherRawValue.phoneNumber, {
        validators: [Validators.required, Validators.pattern('^\\\\+1 [1-9]\\\\d{2}-\\\\d{3}-\\\\d{4}$')],
      }),
      designation: new FormControl(teacherRawValue.designation),
      personalEmailId: new FormControl(teacherRawValue.personalEmailId),
      universityEmailId: new FormControl(teacherRawValue.universityEmailId),
      isActive: new FormControl(teacherRawValue.isActive),
      departmentId: new FormControl(teacherRawValue.departmentId),
      dateOfJoining: new FormControl(teacherRawValue.dateOfJoining, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
      dateOfLeaving: new FormControl(teacherRawValue.dateOfLeaving, {
        validators: [Validators.pattern('^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$')],
      }),
    });
  }

  getTeacher(form: TeacherFormGroup): ITeacher | NewTeacher {
    return form.getRawValue() as ITeacher | NewTeacher;
  }

  resetForm(form: TeacherFormGroup, teacher: TeacherFormGroupInput): void {
    const teacherRawValue = { ...this.getFormDefaults(), ...teacher };
    form.reset(
      {
        ...teacherRawValue,
        id: { value: teacherRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TeacherFormDefaults {
    return {
      id: null,
      isActive: false,
    };
  }
}
