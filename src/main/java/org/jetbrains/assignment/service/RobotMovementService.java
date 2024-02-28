package org.jetbrains.assignment.service;

import static org.jetbrains.assignment.model.Movement.Direction.EAST;
import static org.jetbrains.assignment.model.Movement.Direction.NORTH;
import static org.jetbrains.assignment.model.Movement.Direction.SOUTH;
import static org.jetbrains.assignment.model.Movement.Direction.WEST;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.assignment.model.Movement;
import org.jetbrains.assignment.model.Point;
import org.jetbrains.assignment.repository.MovementRepository;
import org.jetbrains.assignment.repository.PointRepository;
import org.springframework.stereotype.Service;


@Service
public class RobotMovementService {

    private final PointRepository pointRepository;

    private final MovementRepository movementRepository;

    public RobotMovementService(PointRepository pointRepository, MovementRepository movementRepository) {
        this.pointRepository = pointRepository;
        this.movementRepository = movementRepository;
    }

    public List<Point> getLocations(List<Movement> movements) {
        movementRepository.saveAll(movements);
        List<Point> locations = new ArrayList<>();
        int x = 0;
        int y = 0;
        Point startingPoint = new Point(x, y);
        locations.add(startingPoint);

        for (Movement movement : movements) {
            switch (movement.getDirection()) {
                case NORTH:
                    x += movement.getSteps();
                    break;
                case SOUTH:
                    x -= movement.getSteps();
                    break;
                case EAST:
                    y += movement.getSteps();
                    break;
                case WEST:
                    y -= movement.getSteps();
                    break;
            }

            Point newPoint = new Point(x, y);
            locations.add(newPoint);
        }
        return pointRepository.saveAll(locations);
    }

    public List<Movement> getMoves(List<Point> locations) {
        List<Movement> movements = new ArrayList<>();

        if (locations == null || locations.isEmpty()) {
            return movements;
        }

        Point prevPoint = locations.get(0);
        for (int i = 1; i < locations.size(); i++) {
            Point currentPoint = locations.get(i);
            int diffX = currentPoint.getX() - prevPoint.getX();
            int diffY = currentPoint.getY() - prevPoint.getY();
            if (diffX != 0) {
                Movement movement = new Movement();
                movement.setSteps(Math.abs(diffX));
                if (diffX > 0) {
                    movement.setDirection(NORTH);
                } else {
                    movement.setDirection(SOUTH);
                }
                movements.add(movement);
            }
            if (diffY != 0) {
                Movement movement = new Movement();
                movement.setSteps(Math.abs(diffY));
                if (diffY > 0) {
                    movement.setDirection(EAST);
                } else {
                    movement.setDirection(WEST);
                }
                movements.add(movement);
            }
            prevPoint = currentPoint;
        }
        return movementRepository.saveAll(movements);
    }
}