"use strict";
class OnlineCourse {
    constructor(title, duration) {
        this.students = [];
        this.title = title;
        this.duration = duration;
    }
    registerStudent(student) {
        if (!this.isStudentRegistered(student)) {
            this.students.push(student);
        }
    }
    isStudentRegistered(student) {
        return this.students.includes(student);
    }
}
class CourseManager {
    constructor() {
        this.courses = [];
    }
    addCourse(course) {
        this.courses.push(course);
    }
    removeCourse(courseName) {
        this.courses = this.courses.filter(course => course.title !== courseName);
    }
    findCourse(courseName) {
        return this.courses.find(course => course.title === courseName);
    }
}
// Приклад роботи з курсами
const jsCourse = new OnlineCourse('JavaScript Basics', 10);
const tsCourse = new OnlineCourse('TypeScript Advanced', 15);
jsCourse.registerStudent('John Doe');
tsCourse.registerStudent('Jane Smith');
const courseManager = new CourseManager();
courseManager.addCourse(jsCourse);
courseManager.addCourse(tsCourse);
console.log(courseManager.findCourse('JavaScript Basics'));
