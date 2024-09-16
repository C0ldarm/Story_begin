interface Course {
    title: string;
    duration: number;
    students: string[];
  
    registerStudent(student: string): void;
    isStudentRegistered(student: string): boolean;
  }
  
  class OnlineCourse implements Course {
    title: string;
    duration: number;
    students: string[] = [];
  
    constructor(title: string, duration: number) {
      this.title = title;
      this.duration = duration;
    }
  
    registerStudent(student: string): void {
      if (!this.isStudentRegistered(student)) {
        this.students.push(student);
      }
    }
  
    isStudentRegistered(student: string): boolean {
      return this.students.includes(student);
    }
  }
  
  class CourseManager {
    private courses: Course[] = [];
  
    addCourse(course: Course): void {
      this.courses.push(course);
    }
  
    removeCourse(courseName: string): void {
      this.courses = this.courses.filter(course => course.title !== courseName);
    }
  
    findCourse(courseName: string): Course | undefined {
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
  