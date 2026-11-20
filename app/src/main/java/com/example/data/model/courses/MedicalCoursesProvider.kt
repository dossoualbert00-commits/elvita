package com.example.data.model.courses

object MedicalCoursesProvider {
  val courses: List<MedicalCourse> by lazy {
    PleuroPulmonaryCoursesPart1A.courses +
      PleuroPulmonaryCoursesPart1B.courses +
      PleuroPulmonaryCoursesPart2A.courses +
      PleuroPulmonaryCoursesPart2B.courses +
      OtherSpecialtiesCourses1.courses +
      OtherSpecialtiesCourses2.courses
  }
}
