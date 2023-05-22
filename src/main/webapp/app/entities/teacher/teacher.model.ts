import { Desigination } from 'app/entities/enumerations/desigination.model';

export interface ITeacher {
  id: number;
  facultyId?: string | null;
  name?: string | null;
  phoneNumber?: string | null;
  designation?: Desigination | null;
  personalEmailId?: string | null;
  universityEmailId?: string | null;
  isActive?: boolean | null;
  departmentId?: number | null;
  dateOfJoining?: string | null;
  dateOfLeaving?: string | null;
}

export type NewTeacher = Omit<ITeacher, 'id'> & { id: null };
