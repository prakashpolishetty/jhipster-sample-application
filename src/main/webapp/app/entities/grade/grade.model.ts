export interface IGrade {
  id: number;
  studentId?: string | null;
  courseId?: string | null;
  departmentId?: number | null;
  grade?: number | null;
}

export type NewGrade = Omit<IGrade, 'id'> & { id: null };
