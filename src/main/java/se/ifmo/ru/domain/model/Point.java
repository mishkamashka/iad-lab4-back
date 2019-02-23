package se.ifmo.ru.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "Points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double x;

    @Column
    private Double y;

    @Column
    private Double r;

    @Column
    private boolean inArea;

    private Point() {
    }

    public Point(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.inArea = this.checkIsInArea();
    }

    public boolean checkIsInArea() {
        return ((x >= 0 && x <= r / 2 && y >= 0 && y <= r) ||
                (x <= 0 && y <= 0 && y >= - x - r / 2) ||
                (x <= 0 && y >= 0 && x * x + y * y <= r * r));
    }

    public Long getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setInArea(boolean inArea) {
        this.inArea = inArea;
    }

    public boolean isInArea() {
        return inArea;
    }
}
