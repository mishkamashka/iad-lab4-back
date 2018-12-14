package se.ifmo.ru.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "Points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double x;
    private Double y;
    private Double r;
    private boolean inArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Point() {
    }

    public Point(Double x, Double y, Double r, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.user = user;
        this.inArea = this.checkIsInArea();
    }

    private boolean checkIsInArea() {
        return ((x >= 0 && x <= r / 2 && y >= 0 && y <= r) ||
                (x <= 0 && y <= 0 && y > x - r / 2) ||
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

    public User getUser() {
        return user;
    }

    public boolean isInArea() {
        return inArea;
    }
}
