package com.universty.domain;

import com.universty.domain.enumeration.CourseName;
import com.universty.domain.enumeration.DegreeType;
import com.universty.domain.enumeration.Specialization;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]\\\\d{3}$")
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "course_name", nullable = false)
    private CourseName courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "department")
    private Integer department;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DegreeType degreeType;

    @Column(name = "is_core")
    private Boolean isCore;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization")
    private Specialization specialization;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "start_date_of_enrollment")
    private String startDateOfEnrollment;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "last_date_of_enrollment")
    private String lastDateOfEnrollment;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "start_date_of_course")
    private String startDateOfCourse;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "last_date_of_course")
    private String lastDateOfCourse;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "last_date_of_course_drop")
    private String lastDateOfCourseDrop;

    @Column(name = "waiting_list")
    private Integer waitingList;

    @Column(name = "number_of_enrollments")
    private Integer numberOfEnrollments;

    @Column(name = "is_enrollment_restricted")
    private Boolean isEnrollmentRestricted;

    @Type(type = "uuid-char")
    @Column(name = "teacher_id", length = 36)
    private UUID teacherID;

    @Type(type = "uuid-char")
    @Column(name = "grader_id", length = 36)
    private UUID graderID;

    @Column(name = "class_room_number")
    private String classRoomNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Course id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public Course courseId(String courseId) {
        this.setCourseId(courseId);
        return this;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseName getCourseName() {
        return this.courseName;
    }

    public Course courseName(CourseName courseName) {
        this.setCourseName(courseName);
        return this;
    }

    public void setCourseName(CourseName courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return this.description;
    }

    public Course description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Course isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDepartment() {
        return this.department;
    }

    public Course department(Integer department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public DegreeType getDegreeType() {
        return this.degreeType;
    }

    public Course degreeType(DegreeType degreeType) {
        this.setDegreeType(degreeType);
        return this;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Boolean getIsCore() {
        return this.isCore;
    }

    public Course isCore(Boolean isCore) {
        this.setIsCore(isCore);
        return this;
    }

    public void setIsCore(Boolean isCore) {
        this.isCore = isCore;
    }

    public Specialization getSpecialization() {
        return this.specialization;
    }

    public Course specialization(Specialization specialization) {
        this.setSpecialization(specialization);
        return this;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getStartDateOfEnrollment() {
        return this.startDateOfEnrollment;
    }

    public Course startDateOfEnrollment(String startDateOfEnrollment) {
        this.setStartDateOfEnrollment(startDateOfEnrollment);
        return this;
    }

    public void setStartDateOfEnrollment(String startDateOfEnrollment) {
        this.startDateOfEnrollment = startDateOfEnrollment;
    }

    public String getLastDateOfEnrollment() {
        return this.lastDateOfEnrollment;
    }

    public Course lastDateOfEnrollment(String lastDateOfEnrollment) {
        this.setLastDateOfEnrollment(lastDateOfEnrollment);
        return this;
    }

    public void setLastDateOfEnrollment(String lastDateOfEnrollment) {
        this.lastDateOfEnrollment = lastDateOfEnrollment;
    }

    public String getStartDateOfCourse() {
        return this.startDateOfCourse;
    }

    public Course startDateOfCourse(String startDateOfCourse) {
        this.setStartDateOfCourse(startDateOfCourse);
        return this;
    }

    public void setStartDateOfCourse(String startDateOfCourse) {
        this.startDateOfCourse = startDateOfCourse;
    }

    public String getLastDateOfCourse() {
        return this.lastDateOfCourse;
    }

    public Course lastDateOfCourse(String lastDateOfCourse) {
        this.setLastDateOfCourse(lastDateOfCourse);
        return this;
    }

    public void setLastDateOfCourse(String lastDateOfCourse) {
        this.lastDateOfCourse = lastDateOfCourse;
    }

    public String getLastDateOfCourseDrop() {
        return this.lastDateOfCourseDrop;
    }

    public Course lastDateOfCourseDrop(String lastDateOfCourseDrop) {
        this.setLastDateOfCourseDrop(lastDateOfCourseDrop);
        return this;
    }

    public void setLastDateOfCourseDrop(String lastDateOfCourseDrop) {
        this.lastDateOfCourseDrop = lastDateOfCourseDrop;
    }

    public Integer getWaitingList() {
        return this.waitingList;
    }

    public Course waitingList(Integer waitingList) {
        this.setWaitingList(waitingList);
        return this;
    }

    public void setWaitingList(Integer waitingList) {
        this.waitingList = waitingList;
    }

    public Integer getNumberOfEnrollments() {
        return this.numberOfEnrollments;
    }

    public Course numberOfEnrollments(Integer numberOfEnrollments) {
        this.setNumberOfEnrollments(numberOfEnrollments);
        return this;
    }

    public void setNumberOfEnrollments(Integer numberOfEnrollments) {
        this.numberOfEnrollments = numberOfEnrollments;
    }

    public Boolean getIsEnrollmentRestricted() {
        return this.isEnrollmentRestricted;
    }

    public Course isEnrollmentRestricted(Boolean isEnrollmentRestricted) {
        this.setIsEnrollmentRestricted(isEnrollmentRestricted);
        return this;
    }

    public void setIsEnrollmentRestricted(Boolean isEnrollmentRestricted) {
        this.isEnrollmentRestricted = isEnrollmentRestricted;
    }

    public UUID getTeacherID() {
        return this.teacherID;
    }

    public Course teacherID(UUID teacherID) {
        this.setTeacherID(teacherID);
        return this;
    }

    public void setTeacherID(UUID teacherID) {
        this.teacherID = teacherID;
    }

    public UUID getGraderID() {
        return this.graderID;
    }

    public Course graderID(UUID graderID) {
        this.setGraderID(graderID);
        return this;
    }

    public void setGraderID(UUID graderID) {
        this.graderID = graderID;
    }

    public String getClassRoomNumber() {
        return this.classRoomNumber;
    }

    public Course classRoomNumber(String classRoomNumber) {
        this.setClassRoomNumber(classRoomNumber);
        return this;
    }

    public void setClassRoomNumber(String classRoomNumber) {
        this.classRoomNumber = classRoomNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", department=" + getDepartment() +
            ", degreeType='" + getDegreeType() + "'" +
            ", isCore='" + getIsCore() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", startDateOfEnrollment='" + getStartDateOfEnrollment() + "'" +
            ", lastDateOfEnrollment='" + getLastDateOfEnrollment() + "'" +
            ", startDateOfCourse='" + getStartDateOfCourse() + "'" +
            ", lastDateOfCourse='" + getLastDateOfCourse() + "'" +
            ", lastDateOfCourseDrop='" + getLastDateOfCourseDrop() + "'" +
            ", waitingList=" + getWaitingList() +
            ", numberOfEnrollments=" + getNumberOfEnrollments() +
            ", isEnrollmentRestricted='" + getIsEnrollmentRestricted() + "'" +
            ", teacherID='" + getTeacherID() + "'" +
            ", graderID='" + getGraderID() + "'" +
            ", classRoomNumber='" + getClassRoomNumber() + "'" +
            "}";
    }
}
