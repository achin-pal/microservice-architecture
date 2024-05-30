package com.palx.JobPortal.review.service;

import com.palx.JobPortal.review.pojo.Review;

import java.util.List;

public interface ReviewService {

   public List<Review> getAllreview(Long companyId);
   public boolean addReview(Long companyId, Review review);
   public Review getReview(Long companyId,Long reviewId);
   public boolean UpdateReview(Long companyId,Long reviewId,Review review);
   boolean deleteReview(Long companyId, Long reviewId);
}
