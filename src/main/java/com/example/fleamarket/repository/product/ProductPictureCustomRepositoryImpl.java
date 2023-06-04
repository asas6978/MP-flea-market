//package com.example.fleemarket.repository.product;
//
//import com.example.fleemarket.entity.post.PostPicture;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class ProductPictureCustomRepositoryImpl implements ProductPictureCustomRepository {
//    @PersistenceContext
//    EntityManager entityManager;
//
////    @Override
////    public String findPathByPictureIdx(Long pictureIdx) {
////        String query = "select path from ProductPicture where pictureIdx = :pictureIdx";
////        return (String) entityManager.createQuery(query)
////                .setParameter("pictureIdx", pictureIdx)
////                .getSingleResult();
////    }
////
////    @Override
////    public List<PostPicture> findPostPicturesByUserIdx(Long userIdx) {
////        String query = "select path from ProductPicture join User using "
////        return null;
////    }
//}
