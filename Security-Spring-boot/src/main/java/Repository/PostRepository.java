package Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import Model.Post;



@Repository
public interface PostRepository extends CrudRepository<Post,Long>{

}
