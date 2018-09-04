package com.example.blog.service.persistence;

import com.example.blog.model.Post;
import com.example.blog.service.api.PostService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyBatisPostMapper extends PostService {
    @Override
    @Select("select * from t_post")
    List<Post> findAll();

    @Override
    @Select("select * from t_post where title = #{title}")
    List findByTitle(String title);

    @Override
    @Select("select * from t_post where id = #{id}")
    Post findOne(Long id);

    @Override
    @Select("select * from t_post where author_id = #{id}")
    List<Post> findByAuthorId(Long id);

    @Override
    @Insert("insert into t_post (title, author_id, category, text) values " +
            "(#{title}, #{authorId}, #{category}, #{text})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Post post);

    @Override
    @Delete("delete from t_post where id = #{id}")
    void delete(Long id);

    @Override
    @Update("update t_post set title = #{title}, author_id = #{authorId}, " +
            "category = #{category}, text = #{text}, where id = #{id}")
    Post updatePost(Post post, Long id);
}
