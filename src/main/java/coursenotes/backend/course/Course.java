package coursenotes.backend.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String courseUID;
    String courseName;
    String courseDescription;
    String courseLocation;

    public Course() {
    }

    public Course(UUID id, String courseUID, String courseName, String courseDescription, String courseLocation) {
        this.id = id;
        this.courseUID = courseUID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLocation = courseLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCourseUID(), that.getCourseUID()) && Objects.equals(getCourseName(), that.getCourseName()) && Objects.equals(getCourseDescription(), that.getCourseDescription()) && Objects.equals(getCourseLocation(), that.getCourseLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseUID(), getCourseName(), getCourseDescription(), getCourseLocation());
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", courseUID='" + courseUID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseLocation='" + courseLocation + '\'' +
                '}';
    }
}
