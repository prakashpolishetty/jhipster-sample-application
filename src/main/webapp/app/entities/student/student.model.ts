import { Specialization } from 'app/entities/enumerations/specialization.model';
import { DegreeType } from 'app/entities/enumerations/degree-type.model';
import { CurrentSem } from 'app/entities/enumerations/current-sem.model';

export interface IStudent {
  id: number;
  studentId?: string | null;
  name?: string | null;
  dateOfBirth?: string | null;
  phoneNumber?: string | null;
  personalEmailId?: string | null;
  universityEmailId?: string | null;
  departmentId?: number | null;
  specialization?: Specialization | null;
  degreeType?: DegreeType | null;
  isActive?: boolean | null;
  isDegreeCompleted?: boolean | null;
  currentSem?: CurrentSem | null;
}

export type NewStudent = Omit<IStudent, 'id'> & { id: null };
