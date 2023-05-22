package com.universty.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universty.IntegrationTest;
import com.universty.domain.Course;
import com.universty.domain.enumeration.CourseName;
import com.universty.domain.enumeration.DegreeType;
import com.universty.domain.enumeration.Specialization;
import com.universty.repository.CourseRepository;
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
 * Integration tests for the {@link CourseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CourseResourceIT {

    private static final String DEFAULT_COURSE_ID = "C\\ddd";
    private static final String UPDATED_COURSE_ID = "e\\ddd";

    private static final CourseName DEFAULT_COURSE_NAME = CourseName.DATASTRUCTURES;
    private static final CourseName UPDATED_COURSE_NAME = CourseName.ADVANCEOPERATINGSYSTEMS;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Integer DEFAULT_DEPARTMENT = 1;
    private static final Integer UPDATED_DEPARTMENT = 2;

    private static final DegreeType DEFAULT_DEGREE_TYPE = DegreeType.BACHELORS;
    private static final DegreeType UPDATED_DEGREE_TYPE = DegreeType.MASTERS;

    private static final Boolean DEFAULT_IS_CORE = false;
    private static final Boolean UPDATED_IS_CORE = true;

    private static final Specialization DEFAULT_SPECIALIZATION = Specialization.COMPUTERSCIENCE;
    private static final Specialization UPDATED_SPECIALIZATION = Specialization.DATASCIENCE;

    private static final String DEFAULT_START_DATE_OF_ENROLLMENT = "12-1\\d-\\dd";
    private static final String UPDATED_START_DATE_OF_ENROLLMENT = "01-2\\d-\\dd";

    private static final String DEFAULT_LAST_DATE_OF_ENROLLMENT = "09-05-\\dd";
    private static final String UPDATED_LAST_DATE_OF_ENROLLMENT = "12-2\\d-\\dd";

    private static final String DEFAULT_START_DATE_OF_COURSE = "10-31-\\dd";
    private static final String UPDATED_START_DATE_OF_COURSE = "02-05-\\dd";

    private static final String DEFAULT_LAST_DATE_OF_COURSE = "07-2\\d-\\dd";
    private static final String UPDATED_LAST_DATE_OF_COURSE = "10-01-\\dd";

    private static final String DEFAULT_LAST_DATE_OF_COURSE_DROP = "12-30-\\dd";
    private static final String UPDATED_LAST_DATE_OF_COURSE_DROP = "12-08-\\dd";

    private static final Integer DEFAULT_WAITING_LIST = 1;
    private static final Integer UPDATED_WAITING_LIST = 2;

    private static final Integer DEFAULT_NUMBER_OF_ENROLLMENTS = 1;
    private static final Integer UPDATED_NUMBER_OF_ENROLLMENTS = 2;

    private static final Boolean DEFAULT_IS_ENROLLMENT_RESTRICTED = false;
    private static final Boolean UPDATED_IS_ENROLLMENT_RESTRICTED = true;

    private static final UUID DEFAULT_TEACHER_ID = UUID.randomUUID();
    private static final UUID UPDATED_TEACHER_ID = UUID.randomUUID();

    private static final UUID DEFAULT_GRADER_ID = UUID.randomUUID();
    private static final UUID UPDATED_GRADER_ID = UUID.randomUUID();

    private static final String DEFAULT_CLASS_ROOM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_ROOM_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/courses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourseMockMvc;

    private Course course;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createEntity(EntityManager em) {
        Course course = new Course()
            .courseId(DEFAULT_COURSE_ID)
            .courseName(DEFAULT_COURSE_NAME)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .department(DEFAULT_DEPARTMENT)
            .degreeType(DEFAULT_DEGREE_TYPE)
            .isCore(DEFAULT_IS_CORE)
            .specialization(DEFAULT_SPECIALIZATION)
            .startDateOfEnrollment(DEFAULT_START_DATE_OF_ENROLLMENT)
            .lastDateOfEnrollment(DEFAULT_LAST_DATE_OF_ENROLLMENT)
            .startDateOfCourse(DEFAULT_START_DATE_OF_COURSE)
            .lastDateOfCourse(DEFAULT_LAST_DATE_OF_COURSE)
            .lastDateOfCourseDrop(DEFAULT_LAST_DATE_OF_COURSE_DROP)
            .waitingList(DEFAULT_WAITING_LIST)
            .numberOfEnrollments(DEFAULT_NUMBER_OF_ENROLLMENTS)
            .isEnrollmentRestricted(DEFAULT_IS_ENROLLMENT_RESTRICTED)
            .teacherID(DEFAULT_TEACHER_ID)
            .graderID(DEFAULT_GRADER_ID)
            .classRoomNumber(DEFAULT_CLASS_ROOM_NUMBER);
        return course;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createUpdatedEntity(EntityManager em) {
        Course course = new Course()
            .courseId(UPDATED_COURSE_ID)
            .courseName(UPDATED_COURSE_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .department(UPDATED_DEPARTMENT)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isCore(UPDATED_IS_CORE)
            .specialization(UPDATED_SPECIALIZATION)
            .startDateOfEnrollment(UPDATED_START_DATE_OF_ENROLLMENT)
            .lastDateOfEnrollment(UPDATED_LAST_DATE_OF_ENROLLMENT)
            .startDateOfCourse(UPDATED_START_DATE_OF_COURSE)
            .lastDateOfCourse(UPDATED_LAST_DATE_OF_COURSE)
            .lastDateOfCourseDrop(UPDATED_LAST_DATE_OF_COURSE_DROP)
            .waitingList(UPDATED_WAITING_LIST)
            .numberOfEnrollments(UPDATED_NUMBER_OF_ENROLLMENTS)
            .isEnrollmentRestricted(UPDATED_IS_ENROLLMENT_RESTRICTED)
            .teacherID(UPDATED_TEACHER_ID)
            .graderID(UPDATED_GRADER_ID)
            .classRoomNumber(UPDATED_CLASS_ROOM_NUMBER);
        return course;
    }

    @BeforeEach
    public void initTest() {
        course = createEntity(em);
    }

    @Test
    @Transactional
    void createCourse() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();
        // Create the Course
        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate + 1);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCourse.getCourseName()).isEqualTo(DEFAULT_COURSE_NAME);
        assertThat(testCourse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourse.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCourse.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testCourse.getDegreeType()).isEqualTo(DEFAULT_DEGREE_TYPE);
        assertThat(testCourse.getIsCore()).isEqualTo(DEFAULT_IS_CORE);
        assertThat(testCourse.getSpecialization()).isEqualTo(DEFAULT_SPECIALIZATION);
        assertThat(testCourse.getStartDateOfEnrollment()).isEqualTo(DEFAULT_START_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getLastDateOfEnrollment()).isEqualTo(DEFAULT_LAST_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getStartDateOfCourse()).isEqualTo(DEFAULT_START_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourse()).isEqualTo(DEFAULT_LAST_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourseDrop()).isEqualTo(DEFAULT_LAST_DATE_OF_COURSE_DROP);
        assertThat(testCourse.getWaitingList()).isEqualTo(DEFAULT_WAITING_LIST);
        assertThat(testCourse.getNumberOfEnrollments()).isEqualTo(DEFAULT_NUMBER_OF_ENROLLMENTS);
        assertThat(testCourse.getIsEnrollmentRestricted()).isEqualTo(DEFAULT_IS_ENROLLMENT_RESTRICTED);
        assertThat(testCourse.getTeacherID()).isEqualTo(DEFAULT_TEACHER_ID);
        assertThat(testCourse.getGraderID()).isEqualTo(DEFAULT_GRADER_ID);
        assertThat(testCourse.getClassRoomNumber()).isEqualTo(DEFAULT_CLASS_ROOM_NUMBER);
    }

    @Test
    @Transactional
    void createCourseWithExistingId() throws Exception {
        // Create the Course with an existing ID
        course.setId(1L);

        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCourseIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseRepository.findAll().size();
        // set the field null
        course.setCourseId(null);

        // Create the Course, which fails.

        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isBadRequest());

        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCourseNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseRepository.findAll().size();
        // set the field null
        course.setCourseName(null);

        // Create the Course, which fails.

        restCourseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isBadRequest());

        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCourses() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get all the courseList
        restCourseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID)))
            .andExpect(jsonPath("$.[*].courseName").value(hasItem(DEFAULT_COURSE_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].degreeType").value(hasItem(DEFAULT_DEGREE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isCore").value(hasItem(DEFAULT_IS_CORE.booleanValue())))
            .andExpect(jsonPath("$.[*].specialization").value(hasItem(DEFAULT_SPECIALIZATION.toString())))
            .andExpect(jsonPath("$.[*].startDateOfEnrollment").value(hasItem(DEFAULT_START_DATE_OF_ENROLLMENT)))
            .andExpect(jsonPath("$.[*].lastDateOfEnrollment").value(hasItem(DEFAULT_LAST_DATE_OF_ENROLLMENT)))
            .andExpect(jsonPath("$.[*].startDateOfCourse").value(hasItem(DEFAULT_START_DATE_OF_COURSE)))
            .andExpect(jsonPath("$.[*].lastDateOfCourse").value(hasItem(DEFAULT_LAST_DATE_OF_COURSE)))
            .andExpect(jsonPath("$.[*].lastDateOfCourseDrop").value(hasItem(DEFAULT_LAST_DATE_OF_COURSE_DROP)))
            .andExpect(jsonPath("$.[*].waitingList").value(hasItem(DEFAULT_WAITING_LIST)))
            .andExpect(jsonPath("$.[*].numberOfEnrollments").value(hasItem(DEFAULT_NUMBER_OF_ENROLLMENTS)))
            .andExpect(jsonPath("$.[*].isEnrollmentRestricted").value(hasItem(DEFAULT_IS_ENROLLMENT_RESTRICTED.booleanValue())))
            .andExpect(jsonPath("$.[*].teacherID").value(hasItem(DEFAULT_TEACHER_ID.toString())))
            .andExpect(jsonPath("$.[*].graderID").value(hasItem(DEFAULT_GRADER_ID.toString())))
            .andExpect(jsonPath("$.[*].classRoomNumber").value(hasItem(DEFAULT_CLASS_ROOM_NUMBER)));
    }

    @Test
    @Transactional
    void getCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get the course
        restCourseMockMvc
            .perform(get(ENTITY_API_URL_ID, course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(course.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID))
            .andExpect(jsonPath("$.courseName").value(DEFAULT_COURSE_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.degreeType").value(DEFAULT_DEGREE_TYPE.toString()))
            .andExpect(jsonPath("$.isCore").value(DEFAULT_IS_CORE.booleanValue()))
            .andExpect(jsonPath("$.specialization").value(DEFAULT_SPECIALIZATION.toString()))
            .andExpect(jsonPath("$.startDateOfEnrollment").value(DEFAULT_START_DATE_OF_ENROLLMENT))
            .andExpect(jsonPath("$.lastDateOfEnrollment").value(DEFAULT_LAST_DATE_OF_ENROLLMENT))
            .andExpect(jsonPath("$.startDateOfCourse").value(DEFAULT_START_DATE_OF_COURSE))
            .andExpect(jsonPath("$.lastDateOfCourse").value(DEFAULT_LAST_DATE_OF_COURSE))
            .andExpect(jsonPath("$.lastDateOfCourseDrop").value(DEFAULT_LAST_DATE_OF_COURSE_DROP))
            .andExpect(jsonPath("$.waitingList").value(DEFAULT_WAITING_LIST))
            .andExpect(jsonPath("$.numberOfEnrollments").value(DEFAULT_NUMBER_OF_ENROLLMENTS))
            .andExpect(jsonPath("$.isEnrollmentRestricted").value(DEFAULT_IS_ENROLLMENT_RESTRICTED.booleanValue()))
            .andExpect(jsonPath("$.teacherID").value(DEFAULT_TEACHER_ID.toString()))
            .andExpect(jsonPath("$.graderID").value(DEFAULT_GRADER_ID.toString()))
            .andExpect(jsonPath("$.classRoomNumber").value(DEFAULT_CLASS_ROOM_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingCourse() throws Exception {
        // Get the course
        restCourseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course
        Course updatedCourse = courseRepository.findById(course.getId()).get();
        // Disconnect from session so that the updates on updatedCourse are not directly saved in db
        em.detach(updatedCourse);
        updatedCourse
            .courseId(UPDATED_COURSE_ID)
            .courseName(UPDATED_COURSE_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .department(UPDATED_DEPARTMENT)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isCore(UPDATED_IS_CORE)
            .specialization(UPDATED_SPECIALIZATION)
            .startDateOfEnrollment(UPDATED_START_DATE_OF_ENROLLMENT)
            .lastDateOfEnrollment(UPDATED_LAST_DATE_OF_ENROLLMENT)
            .startDateOfCourse(UPDATED_START_DATE_OF_COURSE)
            .lastDateOfCourse(UPDATED_LAST_DATE_OF_COURSE)
            .lastDateOfCourseDrop(UPDATED_LAST_DATE_OF_COURSE_DROP)
            .waitingList(UPDATED_WAITING_LIST)
            .numberOfEnrollments(UPDATED_NUMBER_OF_ENROLLMENTS)
            .isEnrollmentRestricted(UPDATED_IS_ENROLLMENT_RESTRICTED)
            .teacherID(UPDATED_TEACHER_ID)
            .graderID(UPDATED_GRADER_ID)
            .classRoomNumber(UPDATED_CLASS_ROOM_NUMBER);

        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCourse.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCourse))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourse.getCourseName()).isEqualTo(UPDATED_COURSE_NAME);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCourse.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testCourse.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testCourse.getIsCore()).isEqualTo(UPDATED_IS_CORE);
        assertThat(testCourse.getSpecialization()).isEqualTo(UPDATED_SPECIALIZATION);
        assertThat(testCourse.getStartDateOfEnrollment()).isEqualTo(UPDATED_START_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getLastDateOfEnrollment()).isEqualTo(UPDATED_LAST_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getStartDateOfCourse()).isEqualTo(UPDATED_START_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourse()).isEqualTo(UPDATED_LAST_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourseDrop()).isEqualTo(UPDATED_LAST_DATE_OF_COURSE_DROP);
        assertThat(testCourse.getWaitingList()).isEqualTo(UPDATED_WAITING_LIST);
        assertThat(testCourse.getNumberOfEnrollments()).isEqualTo(UPDATED_NUMBER_OF_ENROLLMENTS);
        assertThat(testCourse.getIsEnrollmentRestricted()).isEqualTo(UPDATED_IS_ENROLLMENT_RESTRICTED);
        assertThat(testCourse.getTeacherID()).isEqualTo(UPDATED_TEACHER_ID);
        assertThat(testCourse.getGraderID()).isEqualTo(UPDATED_GRADER_ID);
        assertThat(testCourse.getClassRoomNumber()).isEqualTo(UPDATED_CLASS_ROOM_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, course.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(course))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(course))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCourseWithPatch() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course using partial update
        Course partialUpdatedCourse = new Course();
        partialUpdatedCourse.setId(course.getId());

        partialUpdatedCourse
            .courseId(UPDATED_COURSE_ID)
            .department(UPDATED_DEPARTMENT)
            .isCore(UPDATED_IS_CORE)
            .startDateOfEnrollment(UPDATED_START_DATE_OF_ENROLLMENT)
            .lastDateOfEnrollment(UPDATED_LAST_DATE_OF_ENROLLMENT)
            .startDateOfCourse(UPDATED_START_DATE_OF_COURSE)
            .waitingList(UPDATED_WAITING_LIST)
            .teacherID(UPDATED_TEACHER_ID)
            .graderID(UPDATED_GRADER_ID)
            .classRoomNumber(UPDATED_CLASS_ROOM_NUMBER);

        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourse))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourse.getCourseName()).isEqualTo(DEFAULT_COURSE_NAME);
        assertThat(testCourse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourse.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCourse.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testCourse.getDegreeType()).isEqualTo(DEFAULT_DEGREE_TYPE);
        assertThat(testCourse.getIsCore()).isEqualTo(UPDATED_IS_CORE);
        assertThat(testCourse.getSpecialization()).isEqualTo(DEFAULT_SPECIALIZATION);
        assertThat(testCourse.getStartDateOfEnrollment()).isEqualTo(UPDATED_START_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getLastDateOfEnrollment()).isEqualTo(UPDATED_LAST_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getStartDateOfCourse()).isEqualTo(UPDATED_START_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourse()).isEqualTo(DEFAULT_LAST_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourseDrop()).isEqualTo(DEFAULT_LAST_DATE_OF_COURSE_DROP);
        assertThat(testCourse.getWaitingList()).isEqualTo(UPDATED_WAITING_LIST);
        assertThat(testCourse.getNumberOfEnrollments()).isEqualTo(DEFAULT_NUMBER_OF_ENROLLMENTS);
        assertThat(testCourse.getIsEnrollmentRestricted()).isEqualTo(DEFAULT_IS_ENROLLMENT_RESTRICTED);
        assertThat(testCourse.getTeacherID()).isEqualTo(UPDATED_TEACHER_ID);
        assertThat(testCourse.getGraderID()).isEqualTo(UPDATED_GRADER_ID);
        assertThat(testCourse.getClassRoomNumber()).isEqualTo(UPDATED_CLASS_ROOM_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateCourseWithPatch() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course using partial update
        Course partialUpdatedCourse = new Course();
        partialUpdatedCourse.setId(course.getId());

        partialUpdatedCourse
            .courseId(UPDATED_COURSE_ID)
            .courseName(UPDATED_COURSE_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .department(UPDATED_DEPARTMENT)
            .degreeType(UPDATED_DEGREE_TYPE)
            .isCore(UPDATED_IS_CORE)
            .specialization(UPDATED_SPECIALIZATION)
            .startDateOfEnrollment(UPDATED_START_DATE_OF_ENROLLMENT)
            .lastDateOfEnrollment(UPDATED_LAST_DATE_OF_ENROLLMENT)
            .startDateOfCourse(UPDATED_START_DATE_OF_COURSE)
            .lastDateOfCourse(UPDATED_LAST_DATE_OF_COURSE)
            .lastDateOfCourseDrop(UPDATED_LAST_DATE_OF_COURSE_DROP)
            .waitingList(UPDATED_WAITING_LIST)
            .numberOfEnrollments(UPDATED_NUMBER_OF_ENROLLMENTS)
            .isEnrollmentRestricted(UPDATED_IS_ENROLLMENT_RESTRICTED)
            .teacherID(UPDATED_TEACHER_ID)
            .graderID(UPDATED_GRADER_ID)
            .classRoomNumber(UPDATED_CLASS_ROOM_NUMBER);

        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourse.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourse))
            )
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourse.getCourseName()).isEqualTo(UPDATED_COURSE_NAME);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCourse.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testCourse.getDegreeType()).isEqualTo(UPDATED_DEGREE_TYPE);
        assertThat(testCourse.getIsCore()).isEqualTo(UPDATED_IS_CORE);
        assertThat(testCourse.getSpecialization()).isEqualTo(UPDATED_SPECIALIZATION);
        assertThat(testCourse.getStartDateOfEnrollment()).isEqualTo(UPDATED_START_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getLastDateOfEnrollment()).isEqualTo(UPDATED_LAST_DATE_OF_ENROLLMENT);
        assertThat(testCourse.getStartDateOfCourse()).isEqualTo(UPDATED_START_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourse()).isEqualTo(UPDATED_LAST_DATE_OF_COURSE);
        assertThat(testCourse.getLastDateOfCourseDrop()).isEqualTo(UPDATED_LAST_DATE_OF_COURSE_DROP);
        assertThat(testCourse.getWaitingList()).isEqualTo(UPDATED_WAITING_LIST);
        assertThat(testCourse.getNumberOfEnrollments()).isEqualTo(UPDATED_NUMBER_OF_ENROLLMENTS);
        assertThat(testCourse.getIsEnrollmentRestricted()).isEqualTo(UPDATED_IS_ENROLLMENT_RESTRICTED);
        assertThat(testCourse.getTeacherID()).isEqualTo(UPDATED_TEACHER_ID);
        assertThat(testCourse.getGraderID()).isEqualTo(UPDATED_GRADER_ID);
        assertThat(testCourse.getClassRoomNumber()).isEqualTo(UPDATED_CLASS_ROOM_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, course.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(course))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(course))
            )
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();
        course.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeDelete = courseRepository.findAll().size();

        // Delete the course
        restCourseMockMvc
            .perform(delete(ENTITY_API_URL_ID, course.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
