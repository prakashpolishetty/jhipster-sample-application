package com.universty.domain;

import com.universty.domain.enumeration.Desigination;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "faculty_id", length = 36, nullable = false)
    private UUID facultyId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Pattern(regexp = "^\\\\+1 [1-9]\\\\d{2}-\\\\d{3}-\\\\d{4}$")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "designation")
    private Desigination designation;

    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "university_email_id")
    private String universityEmailId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "department_id")
    private Integer departmentId;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\\\d|2\\\\d|3[01])-(\\\\d{2})$")
    @Column(name = "date_of_leaving")
    private String dateOfLeaving;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Teacher id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getFacultyId() {
        return this.facultyId;
    }

    public Teacher facultyId(UUID facultyId) {
        this.setFacultyId(facultyId);
        return this;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return this.name;
    }

    public Teacher name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Teacher phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Desigination getDesignation() {
        return this.designation;
    }

    public Teacher designation(Desigination designation) {
        this.setDesignation(designation);
        return this;
    }

    public void setDesignation(Desigination designation) {
        this.designation = designation;
    }

    public String getPersonalEmailId() {
        return this.personalEmailId;
    }

    public Teacher personalEmailId(String personalEmailId) {
        this.setPersonalEmailId(personalEmailId);
        return this;
    }

    public void setPersonalEmailId(String personalEmailId) {
        this.personalEmailId = personalEmailId;
    }

    public String getUniversityEmailId() {
        return this.universityEmailId;
    }

    public Teacher universityEmailId(String universityEmailId) {
        this.setUniversityEmailId(universityEmailId);
        return this;
    }

    public void setUniversityEmailId(String universityEmailId) {
        this.universityEmailId = universityEmailId;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Teacher isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Teacher departmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDateOfJoining() {
        return this.dateOfJoining;
    }

    public Teacher dateOfJoining(String dateOfJoining) {
        this.setDateOfJoining(dateOfJoining);
        return this;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateOfLeaving() {
        return this.dateOfLeaving;
    }

    public Teacher dateOfLeaving(String dateOfLeaving) {
        this.setDateOfLeaving(dateOfLeaving);
        return this;
    }

    public void setDateOfLeaving(String dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
        return id != null && id.equals(((Teacher) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", facultyId='" + getFacultyId() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", personalEmailId='" + getPersonalEmailId() + "'" +
            ", universityEmailId='" + getUniversityEmailId() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", dateOfJoining='" + getDateOfJoining() + "'" +
            ", dateOfLeaving='" + getDateOfLeaving() + "'" +
            "}";
    }
}
