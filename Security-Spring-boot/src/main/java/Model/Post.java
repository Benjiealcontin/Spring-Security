package Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String author;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(Long id, String title, String slug, String content, String author, LocalDateTime publishedOn,
			LocalDateTime updatedOn) {
		super();
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.content = content;
		this.author = author;
		this.publishedOn = publishedOn;
		this.updatedOn = updatedOn;
	}
	public Post(String title, String slug, String content, String author, LocalDateTime publishedOn,
			LocalDateTime updatedOn) {
		super();
		this.title = title;
		this.slug = slug;
		this.content = content;
		this.author = author;
		this.publishedOn = publishedOn;
		this.updatedOn = updatedOn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDateTime getPublishedOn() {
		return publishedOn;
	}
	public void setPublishedOn(LocalDateTime publishedOn) {
		this.publishedOn = publishedOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
    
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", publishedOn=" + publishedOn +
                ", updatedOn=" + updatedOn +
                '}';
    }}