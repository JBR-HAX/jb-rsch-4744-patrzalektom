package org.jetbrains.assignment.endpoint;

import java.util.List;

import org.jetbrains.assignment.model.Movement;
import org.jetbrains.assignment.model.Point;
import org.jetbrains.assignment.service.RobotMovementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RobotMovementEndpoint {
    private final RobotMovementService robotMovementService;

    public RobotMovementEndpoint(RobotMovementService robotMovementService) {
        this.robotMovementService = robotMovementService;
    }

    @PostMapping("/locations")
    List<Point> getLocations(@RequestBody List<Movement> movements) {
        return robotMovementService.getLocations(movements);
    }

    @PostMapping("/moves")
    List<Movement> getMoves(@RequestBody List<Point> locations) {
        return robotMovementService.getMoves(locations);
    }

}
