package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/matrix")
public class MatrixController {

    @PostMapping("/process")
    public ResponseEntity<String> processMatrix(@RequestBody MatrixRequest matrixRequest) {
        System.out.println("Received a matrix!");
        for (int[] row : matrixRequest.getMatrix()) {
            for (int num : row ) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        return ResponseEntity.ok("Matrix is ok");
    }
}
