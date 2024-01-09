package hiber.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "series")
private int series;
@Column(name = "model")
private String model;

@OneToOne //(mappedBy = "car")
private User user;

public Car() {
}

public Car(String model, int series) {
    this.model = model;
    this.series = series;
}

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public int getSeries() {
    return series;
}

public void setSeries(int series) {
    this.series = series;
}

public String getModel() {
    return model;
}

public void setModel(String model) {
    this.model = model;
}

public User getUser() {
    return user;
}

public User setUser(User user) {
    this.user = user;
    return user;
}

@Override
public String toString() {
    return "Car{" +
            "id=" + id +
            ", series='" + series + '\'' +
            ", model=" + model +
            ", user=" + user +
            '}';
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return id == car.id && model == car.model && Objects.equals(series, car.series) && Objects.equals(user, car.user);
}

@Override
public int hashCode() {
    return Objects.hash(id, series, model, user);
}
}
