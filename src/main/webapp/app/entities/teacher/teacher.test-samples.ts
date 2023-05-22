import { Desigination } from 'app/entities/enumerations/desigination.model';

import { ITeacher, NewTeacher } from './teacher.model';

export const sampleWithRequiredData: ITeacher = {
  id: 42798,
  facultyId: '5878979b-c038-46d6-8041-7e7c90cf31c0',
  name: 'Borders RAM Associate',
  phoneNumber: '\\\\\1 7dd-ddd-dddd',
};

export const sampleWithPartialData: ITeacher = {
  id: 38174,
  facultyId: 'fa164754-f727-4f75-b49a-55d5f5df69c1',
  name: 'set SMS',
  phoneNumber: '\\\1 7dd-ddd-dddd',
  designation: Desigination['RESERACHASSISTENT'],
  personalEmailId: 'Riel Pakistan',
  dateOfJoining: '12-2d-dd',
  dateOfLeaving: '04-1d-dd',
};

export const sampleWithFullData: ITeacher = {
  id: 89880,
  facultyId: 'cf092461-9e8d-427a-acd1-c1d4a660f814',
  name: 'platforms Kenyan',
  phoneNumber: '\\1 8dd-ddd-dddd',
  designation: Desigination['PROFESSOR'],
  personalEmailId: 'EXE',
  universityEmailId: 'bluetooth',
  isActive: true,
  departmentId: 16483,
  dateOfJoining: '02-2d-dd',
  dateOfLeaving: '10-01-dd',
};

export const sampleWithNewData: NewTeacher = {
  facultyId: '4dc0e47a-9dfe-4671-af0b-50416c6e9249',
  name: 'Representative hack Walks',
  phoneNumber: '\\\\\1 2dd-ddd-dddd',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
