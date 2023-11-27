package com.search;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private void validateRequest(ServerRequest serverRequest) {
        for(int[] row : serverRequest.getMatrix()) {
            for (int num : row) {
                if (num != 0 && num != 1) throw new Error("Matrix Error!");
            }
        }
    }



    @PostMapping("/process")
    public ResponseEntity<String> processMatrix(@RequestBody ServerRequest serverRequest) {
        try {
            System.out.println("Received the data!");
            validateRequest(serverRequest);

            System.out.println(serverRequest.algorithm);

            int[][] matrix = serverRequest.getMatrix();
            int[] startRequest = serverRequest.getStart();
            int[] endRequest = serverRequest.getEnd();
            Pair start = new Pair(startRequest[0], startRequest[1]);
            Pair end = new Pair(endRequest[0], endRequest[1]);



            for (int[] row : matrix) {
                for (int num : row ) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }

            ServerResponse serverResponse = new ServerResponse()
            if(serverRequest.getAlgorithm() == AlgorithmEnum.ASTAR) {
                System.out.println("AStar!");
                Astar astar = new Astar();
                astar.aStarSearch(matrix, matrix.length , matrix[0].length, start, end);
            } else if(serverRequest.getAlgorithm() == AlgorithmEnum.WIDTH) {
                System.out.println("Width!");
            }
            return ResponseEntity.ok("Matrix is ok");
        } catch (Error err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Matrix is error");
        }
    }
}
