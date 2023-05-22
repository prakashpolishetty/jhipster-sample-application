package com.universty.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universty.IntegrationTest;
import com.universty.domain.Teacher;
import com.universty.domain.enumeration.Desigination;
import com.universty.repository.TeacherRepository;
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
 * Integration tests for the {@link TeacherResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TeacherResourceIT {

    private static final UUID DEFAULT_FACULTY_ID = UUID.randomUUID();
    private static final UUID UPDATED_FACULTY_ID = UUID.randomUUID();

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "\\\\\\\\\\1 1\\dd-\\ddd-\\dddd";
    private static final String UPDATED_PHONE_NUMBER = "\\\\\\\\\\1 8\\dd-\\ddd-\\dddd";

    private static final Desigination DEFAULT_DESIGNATION = Desigination.ASSISTENTPROFESSOR;
    private static final Desigination UPDATED_DESIGNATION = Desigination.PROFESSOR;

    private static final String DEFAULT_PERSONAL_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSITY_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSITY_EMAIL_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Integer DEFAULT_DEPARTMENT_ID = 1;
    private static final Integer UPDATED_DEPARTMENT_ID = 2;

    private static final String DEFAULT_DATE_OF_JOINING = "11-2\\d-\\dd";
    private static final String UPDATED_DATE_OF_JOINING = "08-30-\\dd";

    private static final String DEFAULT_DATE_OF_LEAVING = "06-31-\\dd";
    private static final String UPDATED_DATE_OF_LEAVING = "10-06-\\dd";

    private static final String ENTITY_API_URL = "/api/teachers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTeacherMockMvc;

    private Teacher teacher;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Teacher createEntity(EntityManager em) {
        Teacher teacher = new Teacher()
            .facultyId(DEFAULT_FACULTY_ID)
            .name(DEFAULT_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .designation(DEFAULT_DESIGNATION)
            .personalEmailId(DEFAULT_PERSONAL_EMAIL_ID)
            .universityEmailId(DEFAULT_UNIVERSITY_EMAIL_ID)
            .isActive(DEFAULT_IS_ACTIVE)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .dateOfJoining(DEFAULT_DATE_OF_JOINING)
            .dateOfLeaving(DEFAULT_DATE_OF_LEAVING);
        return teacher;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Teacher createUpdatedEntity(EntityManager em) {
        Teacher teacher = new Teacher()
            .facultyId(UPDATED_FACULTY_ID)
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .designation(UPDATED_DESIGNATION)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfLeaving(UPDATED_DATE_OF_LEAVING);
        return teacher;
    }

    @BeforeEach
    public void initTest() {
        teacher = createEntity(em);
    }

    @Test
    @Transactional
    void createTeacher() throws Exception {
        int databaseSizeBeforeCreate = teacherRepository.findAll().size();
        // Create the Teacher
        restTeacherMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isCreated());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate + 1);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getFacultyId()).isEqualTo(DEFAULT_FACULTY_ID);
        assertThat(testTeacher.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTeacher.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testTeacher.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testTeacher.getPersonalEmailId()).isEqualTo(DEFAULT_PERSONAL_EMAIL_ID);
        assertThat(testTeacher.getUniversityEmailId()).isEqualTo(DEFAULT_UNIVERSITY_EMAIL_ID);
        assertThat(testTeacher.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testTeacher.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testTeacher.getDateOfJoining()).isEqualTo(DEFAULT_DATE_OF_JOINING);
        assertThat(testTeacher.getDateOfLeaving()).isEqualTo(DEFAULT_DATE_OF_LEAVING);
    }

    @Test
    @Transactional
    void createTeacherWithExistingId() throws Exception {
        // Create the Teacher with an existing ID
        teacher.setId(1L);

        int databaseSizeBeforeCreate = teacherRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeacherMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFacultyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setFacultyId(null);

        // Create the Teacher, which fails.

        restTeacherMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setName(null);

        // Create the Teacher, which fails.

        restTeacherMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setPhoneNumber(null);

        // Create the Teacher, which fails.

        restTeacherMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTeachers() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get all the teacherList
        restTeacherMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teacher.getId().intValue())))
            .andExpect(jsonPath("$.[*].facultyId").value(hasItem(DEFAULT_FACULTY_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].personalEmailId").value(hasItem(DEFAULT_PERSONAL_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].universityEmailId").value(hasItem(DEFAULT_UNIVERSITY_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].dateOfJoining").value(hasItem(DEFAULT_DATE_OF_JOINING)))
            .andExpect(jsonPath("$.[*].dateOfLeaving").value(hasItem(DEFAULT_DATE_OF_LEAVING)));
    }

    @Test
    @Transactional
    void getTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get the teacher
        restTeacherMockMvc
            .perform(get(ENTITY_API_URL_ID, teacher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(teacher.getId().intValue()))
            .andExpect(jsonPath("$.facultyId").value(DEFAULT_FACULTY_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.personalEmailId").value(DEFAULT_PERSONAL_EMAIL_ID))
            .andExpect(jsonPath("$.universityEmailId").value(DEFAULT_UNIVERSITY_EMAIL_ID))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.dateOfJoining").value(DEFAULT_DATE_OF_JOINING))
            .andExpect(jsonPath("$.dateOfLeaving").value(DEFAULT_DATE_OF_LEAVING));
    }

    @Test
    @Transactional
    void getNonExistingTeacher() throws Exception {
        // Get the teacher
        restTeacherMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Update the teacher
        Teacher updatedTeacher = teacherRepository.findById(teacher.getId()).get();
        // Disconnect from session so that the updates on updatedTeacher are not directly saved in db
        em.detach(updatedTeacher);
        updatedTeacher
            .facultyId(UPDATED_FACULTY_ID)
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .designation(UPDATED_DESIGNATION)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfLeaving(UPDATED_DATE_OF_LEAVING);

        restTeacherMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTeacher.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTeacher))
            )
            .andExpect(status().isOk());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getFacultyId()).isEqualTo(UPDATED_FACULTY_ID);
        assertThat(testTeacher.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTeacher.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTeacher.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTeacher.getPersonalEmailId()).isEqualTo(UPDATED_PERSONAL_EMAIL_ID);
        assertThat(testTeacher.getUniversityEmailId()).isEqualTo(UPDATED_UNIVERSITY_EMAIL_ID);
        assertThat(testTeacher.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testTeacher.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testTeacher.getDateOfJoining()).isEqualTo(UPDATED_DATE_OF_JOINING);
        assertThat(testTeacher.getDateOfLeaving()).isEqualTo(UPDATED_DATE_OF_LEAVING);
    }

    @Test
    @Transactional
    void putNonExistingTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(
                put(ENTITY_API_URL_ID, teacher.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(teacher))
            )
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(teacher))
            )
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTeacherWithPatch() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Update the teacher using partial update
        Teacher partialUpdatedTeacher = new Teacher();
        partialUpdatedTeacher.setId(teacher.getId());

        partialUpdatedTeacher.personalEmailId(UPDATED_PERSONAL_EMAIL_ID);

        restTeacherMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeacher.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTeacher))
            )
            .andExpect(status().isOk());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getFacultyId()).isEqualTo(DEFAULT_FACULTY_ID);
        assertThat(testTeacher.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTeacher.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testTeacher.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testTeacher.getPersonalEmailId()).isEqualTo(UPDATED_PERSONAL_EMAIL_ID);
        assertThat(testTeacher.getUniversityEmailId()).isEqualTo(DEFAULT_UNIVERSITY_EMAIL_ID);
        assertThat(testTeacher.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testTeacher.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testTeacher.getDateOfJoining()).isEqualTo(DEFAULT_DATE_OF_JOINING);
        assertThat(testTeacher.getDateOfLeaving()).isEqualTo(DEFAULT_DATE_OF_LEAVING);
    }

    @Test
    @Transactional
    void fullUpdateTeacherWithPatch() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Update the teacher using partial update
        Teacher partialUpdatedTeacher = new Teacher();
        partialUpdatedTeacher.setId(teacher.getId());

        partialUpdatedTeacher
            .facultyId(UPDATED_FACULTY_ID)
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .designation(UPDATED_DESIGNATION)
            .personalEmailId(UPDATED_PERSONAL_EMAIL_ID)
            .universityEmailId(UPDATED_UNIVERSITY_EMAIL_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfLeaving(UPDATED_DATE_OF_LEAVING);

        restTeacherMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTeacher.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTeacher))
            )
            .andExpect(status().isOk());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getFacultyId()).isEqualTo(UPDATED_FACULTY_ID);
        assertThat(testTeacher.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTeacher.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTeacher.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTeacher.getPersonalEmailId()).isEqualTo(UPDATED_PERSONAL_EMAIL_ID);
        assertThat(testTeacher.getUniversityEmailId()).isEqualTo(UPDATED_UNIVERSITY_EMAIL_ID);
        assertThat(testTeacher.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testTeacher.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testTeacher.getDateOfJoining()).isEqualTo(UPDATED_DATE_OF_JOINING);
        assertThat(testTeacher.getDateOfLeaving()).isEqualTo(UPDATED_DATE_OF_LEAVING);
    }

    @Test
    @Transactional
    void patchNonExistingTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, teacher.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(teacher))
            )
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(teacher))
            )
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();
        teacher.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTeacherMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeDelete = teacherRepository.findAll().size();

        // Delete the teacher
        restTeacherMockMvc
            .perform(delete(ENTITY_API_URL_ID, teacher.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
