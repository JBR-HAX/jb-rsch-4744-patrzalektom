package org.jetbrains.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private Direction direction;

    private int steps;

    public Movement() {
    }

    public Movement(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public Movement(Long id, Direction direction, int steps) {
        this.id = id;
        this.direction = direction;
        this.steps = steps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
