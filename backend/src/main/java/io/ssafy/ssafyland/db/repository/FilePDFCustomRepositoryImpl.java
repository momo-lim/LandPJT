package io.ssafy.ssafyland.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FilePDFCustomRepositoryImpl implements FilePDFCustomRepository {
    @Autowired
    JPAQueryFactory jpaQueryFactory;


}
