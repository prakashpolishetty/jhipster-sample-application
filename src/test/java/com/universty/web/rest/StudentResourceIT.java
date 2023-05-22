package com.universty.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universty.IntegrationTest;
import com.universty.domain.Student;
import com.universty.domain.enumeration.CurrentSem;
import com.universty.domain.enumeration.DegreeType;
import com.universty.domain.enumeration.Specialization;
import com.universty.repository.StudentRepository;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentResourceIT {

    private static final UUID DEFAULT_STUDENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_STUDENT_ID = UUID.randomUUID();

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH = "02-31-\\dd";
    private static final String UPDATED_DATE_OF_BIRTH = "04-06-\\dd";

    private static final String DEFAULT_PHONE_NUMBER = "\\1 7\\dd-\\ddd-\\dddd";
    private static final String UPDATED_PHONE_NUMBER = "\\\\\\\\\\\\1 7\\dd-\\ddd-\\dddd";

    private static final String DEFAULT_PERSONAL_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSITY_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSITY_EMAIL_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEPARTMENT_ID = 1;
    private static final Integer UPDATED_DEPARTMENT_ID = 2;

    private static final Specialization DEFAULT_SPECIALIZATION = Specialization.COMPUTERSCIENCE;
    private static final Specialization UPDATED_SPECIALIZATION = Specialization.DATASCIENCE;

    private static final DegreeType DEFAULT_DEGREE_TYPE = DegreeType.BACHELORS;
    private static final DegreeType UPDATED_DEGREE_TYPE = DegreeType.MASTERS;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_DEGREE_COMPLETED = false;
    private static final Boolean UPDATED_IS_DEGREE_COMPLETED = true;

    private static final CurrentSem DEFAULT_CURRENT_SEM = CurrentSem.FIRSTSEMESTER;
    private static final CurrentSem UPDATED_CURRENT_SEM = CurrentSem.SECOUNDSEMESTER;

    private static final String ENTITY_API_URL = "/api/students";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .studentId(DEFAULT_STUDENT_ID)
            .name(DEFAULT_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .personalEmailId(DEFAULT_PERSONAL_EMAIL_ID)
            .universityEmailId(DEFAULT_UNIVERSITY_EMAIL_ID)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .specialization(DEFAULT_SPECIALIZATION)
            .degreeType(DEFAULT_DEGREE_TYPE)
            .isActive(DEFAULT_IS_ACTIVE)
            .isDegreeCompleted(DEFAULT_IS_DEGREE_COMPLETED)
            .currentSem(DEFAULT_CURRENT_SEM);
        return student;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity(EntityManager em) {
        Student student = new Student()
            .studentId(UPDATED_STUDENT_ID)
            .name(UPDATED_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .specialization(UPDATED_SPECIALIZATION)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .isDegreeCompleted(UPDATED_IS_DEGREE_COMPLETED)
            .currentSem(UPDATED_CURRENT_SEM);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        // Create the Student
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentId()).isEqualTo(DEFAULT_STUDENT_ID);
        assertThat(testStudent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testStudent.getPersonalEmailId()).isEqualTo(DEFAULT_PERSONAL_EMAIL_ID);
        assertThat(testStudent.getUniversityEmailId()).isEqualTo(DEFAULT_UNIVERSITY_EMAIL_ID);
        assertThat(testStudent.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testStudent.getSpecialization()).isEqualTo(DEFAULT_SPECIALIZATION);
        assertThat(testStudent.getDegreeType()).isEqualTo(DEFAULT_DEGREE_TYPE);
        assertThat(testStudent.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testStudent.getIsDegreeCompleted()).isEqualTo(DEFAULT_IS_DEGREE_COMPLETED);
        assertThat(testStudent.getCurrentSem()).isEqualTo(DEFAULT_CURRENT_SEM);
    }

    @Test
    @Transactional
    void createStudentWithExistingId() throws Exception {
        // Create the Student with an existing ID
        student.setId(1L);

        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStudentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentId(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setName(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentId").value(hasItem(DEFAULT_STUDENT_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].personalEmailId").value(hasItem(DEFAULT_PERSONAL_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].universityEmailId").value(hasItem(DEFAULT_UNIVERSITY_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].specialization").value(hasItem(DEFAULT_SPECIALIZATION.toString())))
            .andExpect(jsonPath("$.[*].degreeType").value(hasItem(DEFAULT_DEGREE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDegreeCompleted").value(hasItem(DEFAULT_IS_DEGREE_COMPLETED.booleanValue())))
            .andExpect(jsonPath("$.[*].currentSem").value(hasItem(DEFAULT_CURRENT_SEM.toString())));
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_ID, student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.studentId").value(DEFAULT_STUDENT_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.personalEmailId").value(DEFAULT_PERSONAL_EMAIL_ID))
            .andExpect(jsonPath("$.universityEmailId").value(DEFAULT_UNIVERSITY_EMAIL_ID))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.specialization").value(DEFAULT_SPECIALIZATION.toString()))
            .andExpect(jsonPath("$.degreeType").value(DEFAULT_DEGREE_TYPE.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isDegreeCompleted").value(DEFAULT_IS_DEGREE_COMPLETED.booleanValue()))
            .andExpect(jsonPath("$.currentSem").value(DEFAULT_CURRENT_SEM.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .studentId(UPDATED_STUDENT_ID)
            .name(UPDATED_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .specialization(UPDATED_SPECIALIZATION)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .isDegreeCompleted(UPDATED_IS_DEGREE_COMPLETED)
            .currentSem(UPDATED_CURRENT_SEM);

        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStudent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentId()).isEqualTo(UPDATED_STUDENT_ID);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getPersonalEmailId()).isEqualTo(UPDATED_PERSONAL_EMAIL_ID);
        assertThat(testStudent.getUniversityEmailId()).isEqualTo(UPDATED_UNIVERSITY_EMAIL_ID);
        assertThat(testStudent.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStudent.getSpecialization()).isEqualTo(UPDATED_SPECIALIZATION);
        assertThat(testStudent.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testStudent.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testStudent.getIsDegreeCompleted()).isEqualTo(UPDATED_IS_DEGREE_COMPLETED);
        assertThat(testStudent.getCurrentSem()).isEqualTo(UPDATED_CURRENT_SEM);
    }

    @Test
    @Transactional
    void putNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, student.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .studentId(UPDATED_STUDENT_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .isDegreeCompleted(UPDATED_IS_DEGREE_COMPLETED);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentId()).isEqualTo(UPDATED_STUDENT_ID);
        assertThat(testStudent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getPersonalEmailId()).isEqualTo(DEFAULT_PERSONAL_EMAIL_ID);
        assertThat(testStudent.getUniversityEmailId()).isEqualTo(UPDATED_UNIVERSITY_EMAIL_ID);
        assertThat(testStudent.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testStudent.getSpecialization()).isEqualTo(DEFAULT_SPECIALIZATION);
        assertThat(testStudent.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testStudent.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testStudent.getIsDegreeCompleted()).isEqualTo(UPDATED_IS_DEGREE_COMPLETED);
        assertThat(testStudent.getCurrentSem()).isEqualTo(DEFAULT_CURRENT_SEM);
    }

    @Test
    @Transactional
    void fullUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .studentId(UPDATED_STUDENT_ID)
            .name(UPDATED_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .specialization(UPDATED_SPECIALIZATION)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .isDegreeCompleted(UPDATED_IS_DEGREE_COMPLETED)
            .currentSem(UPDATED_CURRENT_SEM);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentId()).isEqualTo(UPDATED_STUDENT_ID);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getPersonalEmailId()).isEqualTo(UPDATED_PERSONAL_EMAIL_ID);
        assertThat(testStudent.getUniversityEmailId()).isEqualTo(UPDATED_UNIVERSITY_EMAIL_ID);
        assertThat(testStudent.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStudent.getSpecialization()).isEqualTo(UPDATED_SPECIALIZATION);
        assertThat(testStudent.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testStudent.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testStudent.getIsDegreeCompleted()).isEqualTo(UPDATED_IS_DEGREE_COMPLETED);
        assertThat(testStudent.getCurrentSem()).isEqualTo(UPDATED_CURRENT_SEM);
    }

    @Test
    @Transactional
    void patchNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, student.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc
            .perform(delete(ENTITY_API_URL_ID, student.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
