package coursenotes.backend.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "coursemodel")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String courseUID;
    String courseName;
    String courseDescription;
    String courseLocation;

    public CourseModel() {
    }

    public CourseModel(String courseUID, String courseName, String courseDescription, String courseLocation) {
        this.courseUID = courseUID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLocation = courseLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseModel that = (CourseModel) o;
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
