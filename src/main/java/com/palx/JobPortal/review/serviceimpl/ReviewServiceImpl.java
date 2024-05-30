package com.palx.JobPortal.review.serviceimpl;

import com.palx.JobPortal.company.pojo.Company;
import com.palx.JobPortal.company.service.CompanyService;
import com.palx.JobPortal.review.pojo.Review;
import com.palx.JobPortal.review.repository.ReviewRepository;
import com.palx.JobPortal.review.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {


    private CompanyService companyService;
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllreview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company !=null){
               review.setCompany(company);
                reviewRepository.save(review);
                return true;
        }
    return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean UpdateReview(Long companyId, Long reviewId, Review updatedReview) {

        if(companyService.getCompanyById(companyId) !=null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
  return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
