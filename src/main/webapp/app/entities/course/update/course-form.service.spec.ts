import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../course.test-samples';

import { CourseFormService } from './course-form.service';

describe('Course Form Service', () => {
  let service: CourseFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CourseFormService);
  });

  describe('Service methods', () => {
    describe('createCourseFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCourseFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            courseId: expect.any(Object),
            courseName: expect.any(Object),
            description: expect.any(Object),
            isActive: expect.any(Object),
            department: expect.any(Object),
            degreeType: expect.any(Object),
            isCore: expect.any(Object),
            specialization: expect.any(Object),
            startDateOfEnrollment: expect.any(Object),
            lastDateOfEnrollment: expect.any(Object),
            startDateOfCourse: expect.any(Object),
            lastDateOfCourse: expect.any(Object),
            lastDateOfCourseDrop: expect.any(Object),
            waitingList: expect.any(Object),
            numberOfEnrollments: expect.any(Object),
            isEnrollmentRestricted: expect.any(Object),
            teacherID: expect.any(Object),
            graderID: expect.any(Object),
            classRoomNumber: expect.any(Object),
          })
        );
      });

      it('passing ICourse should create a new form with FormGroup', () => {
        const formGroup = service.createCourseFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            courseId: expect.any(Object),
            courseName: expect.any(Object),
            description: expect.any(Object),
            isActive: expect.any(Object),
            department: expect.any(Object),
            degreeType: expect.any(Object),
            isCore: expect.any(Object),
            specialization: expect.any(Object),
            startDateOfEnrollment: expect.any(Object),
            lastDateOfEnrollment: expect.any(Object),
            startDateOfCourse: expect.any(Object),
            lastDateOfCourse: expect.any(Object),
            lastDateOfCourseDrop: expect.any(Object),
            waitingList: expect.any(Object),
            numberOfEnrollments: expect.any(Object),
            isEnrollmentRestricted: expect.any(Object),
            teacherID: expect.any(Object),
            graderID: expect.any(Object),
            classRoomNumber: expect.any(Object),
          })
        );
      });
    });

    describe('getCourse', () => {
      it('should return NewCourse for default Course initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCourseFormGroup(sampleWithNewData);

        const course = service.getCourse(formGroup) as any;

        expect(course).toMatchObject(sampleWithNewData);
      });

      it('should return NewCourse for empty Course initial value', () => {
        const formGroup = service.createCourseFormGroup();

        const course = service.getCourse(formGroup) as any;

        expect(course).toMatchObject({});
      });

      it('should return ICourse', () => {
        const formGroup = service.createCourseFormGroup(sampleWithRequiredData);

        const course = service.getCourse(formGroup) as any;

        expect(course).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICourse should not enable id FormControl', () => {
        const formGroup = service.createCourseFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCourse should disable id FormControl', () => {
        const formGroup = service.createCourseFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
