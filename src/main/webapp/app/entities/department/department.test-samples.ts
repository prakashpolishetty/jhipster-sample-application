import { DepartmentName } from 'app/entities/enumerations/department-name.model';

import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: 39095,
  departmentId: 53161,
  departmentName: DepartmentName['ELECTRONICSANDCOMMUNICATIONALENGINEERING'],
};

export const sampleWithPartialData: IDepartment = {
  id: 55948,
  departmentId: 42882,
  departmentName: DepartmentName['COMPUTERSCIENCEENGINEERING'],
  isActive: true,
};

export const sampleWithFullData: IDepartment = {
  id: 15133,
  departmentId: 76430,
  departmentName: DepartmentName['COMPUTERSCIENCEENGINEERING'],
  isActive: false,
};

export const sampleWithNewData: NewDepartment = {
  departmentId: 30654,
  departmentName: DepartmentName['COMPUTERSCIENCEENGINEERING'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
