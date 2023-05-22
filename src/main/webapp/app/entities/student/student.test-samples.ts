import { Specialization } from 'app/entities/enumerations/specialization.model';
import { DegreeType } from 'app/entities/enumerations/degree-type.model';
import { CurrentSem } from 'app/entities/enumerations/current-sem.model';

import { IStudent, NewStudent } from './student.model';

export const sampleWithRequiredData: IStudent = {
  id: 23105,
  studentId: 'da4cf75e-65a1-4e59-9051-cda5b83a83c5',
  name: 'Path e-markets',
};

export const sampleWithPartialData: IStudent = {
  id: 50545,
  studentId: 'a342d24f-e2a9-4404-a6c7-d7aa7e1e0bfb',
  name: 'state Awesome',
  dateOfBirth: '10-30-dd',
  personalEmailId: 'seamless',
  degreeType: DegreeType['BACHELORS'],
};

export const sampleWithFullData: IStudent = {
  id: 80480,
  studentId: 'b9c5d1c1-eb7c-43b3-93c2-3ae1fcad23f9',
  name: 'Investor calculating',
  dateOfBirth: '07-1d-dd',
  phoneNumber: '\\\1 3dd-ddd-dddd',
  personalEmailId: 'Home leading-edge',
  universityEmailId: 'Beauty',
  departmentId: 51805,
  specialization: Specialization['DATASCIENCE'],
  degreeType: DegreeType['PHD'],
  isActive: false,
  isDegreeCompleted: true,
  currentSem: CurrentSem['SEVENTHSEMSTER'],
};

export const sampleWithNewData: NewStudent = {
  studentId: '0fa7ffaa-1317-4cf9-b34f-20c34368c5bc',
  name: 'New',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
