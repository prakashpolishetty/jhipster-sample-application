import { IGrade, NewGrade } from './grade.model';

export const sampleWithRequiredData: IGrade = {
  id: 2061,
  studentId: '92b8d81a-1a6c-41f0-aa4c-ed53d18d34b2',
  courseId: 'navigate District Branding',
};

export const sampleWithPartialData: IGrade = {
  id: 55882,
  studentId: 'aa08f4d3-f8ab-4c50-8101-16b7173f7848',
  courseId: 'client-server',
};

export const sampleWithFullData: IGrade = {
  id: 57805,
  studentId: 'be22baff-7118-4468-a5df-1ca987a6d329',
  courseId: 'Rubber PNG Fresh',
  departmentId: 24167,
  grade: 85770,
};

export const sampleWithNewData: NewGrade = {
  studentId: '7ef2e685-596e-4d4d-a3fa-521fd94f0ea6',
  courseId: 'Sweden HTTP',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
