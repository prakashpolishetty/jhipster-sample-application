import { CourseName } from 'app/entities/enumerations/course-name.model';
import { DegreeType } from 'app/entities/enumerations/degree-type.model';
import { Specialization } from 'app/entities/enumerations/specialization.model';

import { ICourse, NewCourse } from './course.model';

export const sampleWithRequiredData: ICourse = {
  id: 59109,
  courseId: 'bddd',
  courseName: CourseName['ADVANCEOPERATINGSYSTEMS'],
};

export const sampleWithPartialData: ICourse = {
  id: 93488,
  courseId: 'rddd',
  courseName: CourseName['ADVANCEOPERATINGSYSTEMS'],
  description: 'calculating',
  isActive: true,
  degreeType: DegreeType['BACHELORS'],
  isCore: false,
  specialization: Specialization['DATASCIENCE'],
  startDateOfEnrollment: '06-05-dd',
  lastDateOfEnrollment: '03-31-dd',
  lastDateOfCourseDrop: '12-1d-dd',
  teacherID: 'd358acb4-f8a0-49b6-bde5-d584b16b949f',
};

export const sampleWithFullData: ICourse = {
  id: 54918,
  courseId: 'pddd',
  courseName: CourseName['ADVANCEOPERATINGSYSTEMS'],
  description: 'mindshare calculating haptic',
  isActive: true,
  department: 80905,
  degreeType: DegreeType['MASTERS'],
  isCore: false,
  specialization: Specialization['COMPUTERSCIENCE'],
  startDateOfEnrollment: '12-2d-dd',
  lastDateOfEnrollment: '04-1d-dd',
  startDateOfCourse: '06-2d-dd',
  lastDateOfCourse: '02-04-dd',
  lastDateOfCourseDrop: '10-09-dd',
  waitingList: 18267,
  numberOfEnrollments: 47592,
  isEnrollmentRestricted: false,
  teacherID: 'eea59d71-292d-4431-9f49-7c59640d006c',
  graderID: 'ab67f388-2efe-43e5-b315-633407bb1a02',
  classRoomNumber: 'International',
};

export const sampleWithNewData: NewCourse = {
  courseId: 'cddd',
  courseName: CourseName['DATASTRUCTURES'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
