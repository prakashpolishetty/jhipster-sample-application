import { DepartmentName } from 'app/entities/enumerations/department-name.model';

export interface IDepartment {
  id: number;
  departmentId?: number | null;
  departmentName?: DepartmentName | null;
  isActive?: boolean | null;
}

export type NewDepartment = Omit<IDepartment, 'id'> & { id: null };
