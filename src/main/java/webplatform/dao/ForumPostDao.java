package webplatform.dao; 

import java.util.List;

import webplatform.model.entity.ForumPost;

public interface ForumPostDao {

	public List<ForumPost> findByExercise(Long exerciseId);

	public ForumPost saveOrUpdate(ForumPost entity);
}
