package com.universty.domain;

import com.universty.domain.enumeration.CurrentSem;
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
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "student_id", length = 36, nullable = false)
    private UUID studentId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Pattern(regexp = "^\\\\+1 [1-9]\\\\d{2}-\\\\d{3}-\\\\d{4}$")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "university_email_id")
    private String universityEmailId;

    @Column(name = "department_id")
    private Integer departmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DegreeType degreeType;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_degree_completed")
    private Boolean isDegreeCompleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_sem")
    private CurrentSem currentSem;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Student id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return this.studentId;
    }

    public Student studentId(UUID studentId) {
        this.setStudentId(studentId);
        return this;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return this.name;
    }

    public Student name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Student dateOfBirth(String dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Student phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonalEmailId() {
        return this.personalEmailId;
    }

    public Student personalEmailId(String personalEmailId) {
        this.setPersonalEmailId(personalEmailId);
        return this;
    }

    public void setPersonalEmailId(String personalEmailId) {
        this.personalEmailId = personalEmailId;
    }

    public String getUniversityEmailId() {
        return this.universityEmailId;
    }

    public Student universityEmailId(String universityEmailId) {
        this.setUniversityEmailId(universityEmailId);
        return this;
    }

    public void setUniversityEmailId(String universityEmailId) {
        this.universityEmailId = universityEmailId;
    }

    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Student departmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Specialization getSpecialization() {
        return this.specialization;
    }

    public Student specialization(Specialization specialization) {
        this.setSpecialization(specialization);
        return this;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public DegreeType getDegreeType() {
        return this.degreeType;
    }

    public Student degreeType(DegreeType degreeType) {
        this.setDegreeType(degreeType);
        return this;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Student isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDegreeCompleted() {
        return this.isDegreeCompleted;
    }

    public Student isDegreeCompleted(Boolean isDegreeCompleted) {
        this.setIsDegreeCompleted(isDegreeCompleted);
        return this;
    }

    public void setIsDegreeCompleted(Boolean isDegreeCompleted) {
        this.isDegreeCompleted = isDegreeCompleted;
    }

    public CurrentSem getCurrentSem() {
        return this.currentSem;
    }

    public Student currentSem(CurrentSem currentSem) {
        this.setCurrentSem(currentSem);
        return this;
    }

    public void setCurrentSem(CurrentSem currentSem) {
        this.currentSem = currentSem;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentId='" + getStudentId() + "'" +
            ", name='" + getName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", personalEmailId='" + getPersonalEmailId() + "'" +
            ", universityEmailId='" + getUniversityEmailId() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", specialization='" + getSpecialization() + "'" +
            ", degreeType='" + getDegreeType() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", isDegreeCompleted='" + getIsDegreeCompleted() + "'" +
            ", currentSem='" + getCurrentSem() + "'" +
            "}";
    }
}
