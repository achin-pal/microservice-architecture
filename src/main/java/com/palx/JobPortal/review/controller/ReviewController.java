package com.palx.JobPortal.review.controller;

import com.palx.JobPortal.company.pojo.Company;
import com.palx.JobPortal.review.pojo.Review;
import com.palx.JobPortal.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllreview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
       boolean resturnVal= reviewService.addReview(companyId,review);
       if(resturnVal){
           return new ResponseEntity<>("company review saved", HttpStatus.OK);
       }
        return new ResponseEntity<>("company review notsaved", HttpStatus.OK);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId , @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
         boolean isReviewUpdate=reviewService.UpdateReview(companyId,reviewId,review);
         if(isReviewUpdate){
              return new ResponseEntity<>("review updated", HttpStatus.OK);
         }
              return new ResponseEntity<>("review not updated", HttpStatus.NOT_FOUND);
         }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){

        boolean isReviewDeleted=reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("review not deleted", HttpStatus.NOT_FOUND);
    }
    }

