package com.universty.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A Grade.
 */
@Entity
@Table(name = "grade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Grade implements Serializable {

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
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "grade")
    private Double grade;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Grade id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return this.studentId;
    }

    public Grade studentId(UUID studentId) {
        this.setStudentId(studentId);
        return this;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public Grade courseId(String courseId) {
        this.setCourseId(courseId);
        return this;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Grade departmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Double getGrade() {
        return this.grade;
    }

    public Grade grade(Double grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grade)) {
            return false;
        }
        return id != null && id.equals(((Grade) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Grade{" +
            "id=" + getId() +
            ", studentId='" + getStudentId() + "'" +
            ", courseId='" + getCourseId() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", grade=" + getGrade() +
            "}";
    }
}
