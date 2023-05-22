import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../teacher.test-samples';

import { TeacherFormService } from './teacher-form.service';

describe('Teacher Form Service', () => {
  let service: TeacherFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeacherFormService);
  });

  describe('Service methods', () => {
    describe('createTeacherFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTeacherFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            facultyId: expect.any(Object),
            name: expect.any(Object),
            phoneNumber: expect.any(Object),
            designation: expect.any(Object),
            personalEmailId: expect.any(Object),
            universityEmailId: expect.any(Object),
            isActive: expect.any(Object),
            departmentId: expect.any(Object),
            dateOfJoining: expect.any(Object),
            dateOfLeaving: expect.any(Object),
          })
        );
      });

      it('passing ITeacher should create a new form with FormGroup', () => {
        const formGroup = service.createTeacherFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            facultyId: expect.any(Object),
            name: expect.any(Object),
            phoneNumber: expect.any(Object),
            designation: expect.any(Object),
            personalEmailId: expect.any(Object),
            universityEmailId: expect.any(Object),
            isActive: expect.any(Object),
            departmentId: expect.any(Object),
            dateOfJoining: expect.any(Object),
            dateOfLeaving: expect.any(Object),
          })
        );
      });
    });

    describe('getTeacher', () => {
      it('should return NewTeacher for default Teacher initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTeacherFormGroup(sampleWithNewData);

        const teacher = service.getTeacher(formGroup) as any;

        expect(teacher).toMatchObject(sampleWithNewData);
      });

      it('should return NewTeacher for empty Teacher initial value', () => {
        const formGroup = service.createTeacherFormGroup();

        const teacher = service.getTeacher(formGroup) as any;

        expect(teacher).toMatchObject({});
      });

      it('should return ITeacher', () => {
        const formGroup = service.createTeacherFormGroup(sampleWithRequiredData);

        const teacher = service.getTeacher(formGroup) as any;

        expect(teacher).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITeacher should not enable id FormControl', () => {
        const formGroup = service.createTeacherFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTeacher should disable id FormControl', () => {
        const formGroup = service.createTeacherFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
