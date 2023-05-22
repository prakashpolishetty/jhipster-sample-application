import { CourseName } from 'app/entities/enumerations/course-name.model';
import { DegreeType } from 'app/entities/enumerations/degree-type.model';
import { Specialization } from 'app/entities/enumerations/specialization.model';

export interface ICourse {
  id: number;
  courseId?: string | null;
  courseName?: CourseName | null;
  description?: string | null;
  isActive?: boolean | null;
  department?: number | null;
  degreeType?: DegreeType | null;
  isCore?: boolean | null;
  specialization?: Specialization | null;
  startDateOfEnrollment?: string | null;
  lastDateOfEnrollment?: string | null;
  startDateOfCourse?: string | null;
  lastDateOfCourse?: string | null;
  lastDateOfCourseDrop?: string | null;
  waitingList?: number | null;
  numberOfEnrollments?: number | null;
  isEnrollmentRestricted?: boolean | null;
  teacherID?: string | null;
  graderID?: string | null;
  classRoomNumber?: string | null;
}

export type NewCourse = Omit<ICourse, 'id'> & { id: null };
